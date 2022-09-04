package com.example.mymusic.op;



import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.enums.Status;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Member;
import com.example.mymusic.entity.MemberApply;
import com.example.mymusic.entity.Message;
import com.example.mymusic.entity.param.MemberApplyParam;
import com.example.mymusic.entity.param.MemberParam;
import com.example.mymusic.service.IMemberApplyService;
import com.example.mymusic.service.IMemberService;
import com.example.mymusic.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/memberApply")
public class ManagerMemberApplyAction {

    private static Logger logger = LoggerFactory.getLogger(ManagerMemberApplyAction.class);

    @Resource
    private IMemberService memberService;

    @Resource
    private IMemberApplyService memberApplyService;

    @Resource
    private IMessageService messageService;


    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryMemberApplyList")
    public WebPageResponse queryMemberApplyList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        MemberApplyParam memberParam =new MemberApplyParam();
        try {
        Long total= memberApplyService.queryMemberCountByCondition(memberParam);
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        memberParam.setLimit(limit);
        memberParam.setStart(start);
        List<MemberApply> pages=  memberApplyService.queryMemberByCondition(memberParam);
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
     * 方法说明：修改库存表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping(value = "/updateMember", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMember(@RequestBody Member member){
        WebResponse response =new WebResponse();
        try {
            memberService.updateMember(member);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(member);
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
            MemberApply memberApply =memberApplyService.queryMemberById(id);
            Message message=new Message();
            message.setContent("您的审核消息");
            message.setDetails("您的审核员申请已被驳回，祝您工作顺利");
            message.setMemberId(memberApply.getMemberId());
            messageService.addMessage(message);
            memberApplyService.deleteByMemberId(id);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("删除成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg("删除失败");
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
    @RequestMapping(value="/passByMemberId/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse passByMemberId(@PathVariable Long id){
        WebResponse response =new WebResponse();
        try {
            MemberApply memberApply =memberApplyService.queryMemberById(id);
            Member member=memberService.queryMemberById(memberApply.getMemberId());
            member.setMemberLevel(2l);
            memberApply.setStatus(Status.common.toString());
            Message message=new Message();
            message.setContent("您的审核消息");
            message.setDetails("您的审核员申请已经通过，下次登录时生效，祝您工作顺利");
            message.setMemberId(memberApply.getMemberId());
            messageService.addMessage(message);
            memberApplyService.updateMember(memberApply);
            memberService.updateMember(member);
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