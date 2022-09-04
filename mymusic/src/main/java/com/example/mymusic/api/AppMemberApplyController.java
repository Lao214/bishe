package com.example.mymusic.api;


import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Member;
import com.example.mymusic.entity.MemberApply;
import com.example.mymusic.entity.param.MemberParam;
import com.example.mymusic.service.IMemberApplyService;
import com.example.mymusic.service.IMemberService;
import com.example.mymusic.utils.BeanUtils;
import com.example.mymusic.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类说明：库存表管理接口
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
@RestController
@Controller
@RequestMapping("/app/memberApply")
public class AppMemberApplyController {

    @Resource
    private IMemberService memberService;

    @Resource
    private IMemberApplyService memberApplyService;

    @Value("${server.port}")
    private String port;

    private  static  final  String ip="http://localhost";



    /**
     * 方法说明：修改库存表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @RequestMapping(value = "/addMemberApply", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMemberApply(@RequestBody MemberApply member){
        WebResponse response =new WebResponse();
        MemberApply memberApply =new MemberApply();
        try {
            memberApplyService.addMember(member);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(member);
            response.setMsg("添加成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }


    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryMemberList")
    public WebPageResponse queryMemberList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        MemberParam memberParam =new MemberParam();
        try {
            Long total= memberService.queryMemberCountByCondition(memberParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            memberParam.setLimit(limit);
            memberParam.setStart(start);
            List<Member> pages=  memberService.queryMemberByCondition(memberParam);
            response.setData(pages);
            response.setOffset(start);
            response.setLimit(limit);
            response.setTotal(total);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("查询成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }




    /**
     * 方法说明：注册会员
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @PostMapping(value = "/register")
    public WebResponse register(@RequestBody Member member){
        WebResponse webResponse =new WebResponse();
        try {
            //不输入完全会报错
            if (StringUtils.isEmpty(member.getMemberName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "会员名称");
            }
            if (StringUtils.isEmpty(member.getMemberNo())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "账号未输入");
            }
            if (StringUtils.isEmpty(member.getPassword())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "密码未填");
            }
            String auth1= this.authMemberName(member.getMemberName());
            String auth2= this.authMemberNo(member.getMemberNo());
            if(auth2.equals("账号已存在")){
                webResponse.setStatus(WebResponseCode.HAVED);
                webResponse.setMsg(auth2);
            }
            else if(auth1.equals("用户名已存在")){
                webResponse.setStatus(WebResponseCode.HAVED);
                webResponse.setMsg(auth1);
            }
            else {
                memberService.addMember(member);
                webResponse.setStatus(WebResponseCode.SUCCESS);
                webResponse.setData(member);
                webResponse.setMsg("添加成功");
            }
        } catch (FdServiceException e) {
            webResponse.setStatus(WebResponseCode.FAIL);
            webResponse.setMsg(e.toString());
            return webResponse;
        }
        return webResponse;
    }


    public String authMemberName(String memberName) {
        MemberParam memberParam =new MemberParam();
        memberParam.setMemberName(memberName);
        try {
            List<Member> list= memberService.queryMemberByCondition(memberParam);
            if(list.size()!=0){
                return "用户名已存在";
            }else {
                return "用户名合法";
            }
        } catch (FdServiceException e) {

            return e.toString();
        }
    }

    public String authMemberNo(String memberNo) {
        MemberParam memberParam =new MemberParam();
        memberParam.setMemberNo(memberNo);
        try {
            List<Member> list= memberService.queryMemberByCondition(memberParam);
            if(list.size()!=0){
                return "账号已存在";
            }else {
                return "账号名合法";
            }
        } catch (FdServiceException e) {

            return e.toString();
        }
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 管理员登录
     * @Date 11:24 2022/1/19
     * @Param
     * @return
     **/
    @PostMapping(value = "/login")
    public WebResponse login(@RequestBody Member member) {
        WebResponse webResponse =new WebResponse();
        Member res=  memberService.login(member);
        if(res!=null){
            webResponse.setStatus(WebResponseCode.SUCCESS);
            String token =  TokenUtils.genToken(res.getId().toString(),res.getPassword());
            res.setToken(token);
            webResponse.setData(res);
            webResponse.setMsg("登录成功");
        }else {
            webResponse.setStatus(WebResponseCode.FAIL);
            webResponse.setMsg("用户名或密码错误");
        }
        return webResponse;
    }



    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/member")
    public WebPageResponse member() {
        WebPageResponse response =new WebPageResponse();
        try {
            Member member= TokenUtils.getMemberByToken();
            response.setData(member);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("查询成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }

    /**
     * 查询用户名是否已有
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/memberName")
    public WebPageResponse memberName(@RequestParam String memberName) {
        WebPageResponse response =new WebPageResponse();
        MemberParam memberParam =new MemberParam();
        memberParam.setMemberName(memberName);
        try {
            List<Member> list= memberService.queryMemberByCondition(memberParam);
            if(list!=null){
                response.setStatus(WebResponseCode.HAVED);
                response.setMsg("用户名已存在");
            }else {
                response.setStatus(WebResponseCode.SUCCESS);
                response.setMsg("用户名合法");
            }
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }

    /**
     * 查询账号是否已有
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/memberNo")
    public WebPageResponse memberNo(@RequestParam String memberNo) {
        WebPageResponse response =new WebPageResponse();
        MemberParam memberParam =new MemberParam();
        memberParam.setMemberName(memberNo);
        try {
            List<Member> list= memberService.queryMemberByCondition(memberParam);
            if(list!=null){
                response.setStatus(WebResponseCode.HAVED);
                response.setMsg("账号已存在");
            }else {
                response.setStatus(WebResponseCode.SUCCESS);
                response.setMsg("账号合法");
            }
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }

    /**
     * 方法说明：修改库存表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @RequestMapping(value = "/updateMember", method = RequestMethod.PUT)
    @ResponseBody
    public WebResponse updateMember(@RequestBody Member member){
        WebResponse response =new WebResponse();
        try {
            Member newMember  = memberService.updateMember(member);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(newMember);
            response.setMsg("修改成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }


    /**
     * 方法说明：删除库存表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @RequestMapping(value="/deleteByMemberId/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse deleteByMemberId(@PathVariable Long id){
        WebResponse response =new WebResponse();
        try {
            memberService.deleteByMemberId(id);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("删除成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg("删除失败");
            return response;
        }
        return response;
    }

}
