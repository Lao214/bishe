package com.example.mymusic.op;



import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Member;
import com.example.mymusic.entity.param.MemberParam;
import com.example.mymusic.service.IMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 类说明：库存表管理接口
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
@RestController
@Controller
@RequestMapping("/api/member")
public class ManagerMemberAction {

    private static Logger logger = LoggerFactory.getLogger(ManagerMemberAction.class);

    @Resource
    private IMemberService memberService;


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
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "地址还没输入");
            }
            if (StringUtils.isEmpty(member.getMemberNo())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "账号未输入");
            }
            if (StringUtils.isEmpty(member.getMemberType())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "用户类型未选择");
            }
            if (StringUtils.isEmpty(member.getPassword())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "密码未填");
            }
            if (StringUtils.isEmpty(member.getTelephone())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "电话未填");
            }
            memberService.addMember(member);
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(member);
            webResponse.setMsg("添加成功");
        } catch (FdServiceException e) {
            webResponse.setStatus(WebResponseCode.FAIL);
            webResponse.setMsg(e.toString());
            return webResponse;
        }
        return webResponse;
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

//
//    /**
//     * 导出接口
//     */
//    @GetMapping("export")
//    public  void  export(HttpServletResponse response) throws  Exception{
//           MemberParam memberParam =new MemberParam();
//        try {
//            /**从数据库查询出所有的数据**/
//            List<Member> list =memberService.queryMemberByConditions(memberParam);
//            /**在内存操作，写出到浏览器**/
//            ExcelWriter writer = ExcelUtil.getWriter(true);
//            /**自定义表头**/
//            writer.addHeaderAlias("memberNo","物品编号");
//            writer.addHeaderAlias("memberName","地区");
//            writer.addHeaderAlias("oneType","一级分类");
//            writer.addHeaderAlias("secondType","二级分类");
//            writer.addHeaderAlias("name","货物名称");
//            writer.addHeaderAlias("brand","品牌");
//            writer.addHeaderAlias("specs","规格");
//            writer.addHeaderAlias("count","数量");
//            writer.addHeaderAlias("status","状态");
//            writer.addHeaderAlias("remark","备注");
//            writer.addHeaderAlias("createTime","创建时间");
//            writer.addHeaderAlias("updateTime","更新时间");
//            /**一次性写出list内的对象到excel，默认样式，强制输出标题**/
//            writer.write(list,true);
//
//            /**设置浏览器响应的格式  一般是固定的**/
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//            String fileName = URLEncoder.encode("库存单","UTF-8");
//            /**输出文件名**/
//            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
//            ServletOutputStream outputStream =response.getOutputStream();
//            writer.flush(outputStream,true);
//            outputStream.close();
//            writer.close();
//        }catch (FdServiceException e){
//
//        }
//
//    }
//
//    /**
//     * 导入接口
//     */
//    @PostMapping("/import")
//    public  Boolean  importMember(MultipartFile file) throws Exception{
//        InputStream inputStream =file.getInputStream();
//        ExcelReader reader =ExcelUtil.getReader(inputStream);
//          // 该方式通过javabean来读取Excel的对象，表头得是英文的
//          //List<Member> list =reader.readAll(Member.class);
//        List<List<Object>> list =reader.read(1);
//        List<Member> memberList = CollUtil.newArrayList();
//        Long i =0l;
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        for (List<Object> row:list){
//            Member member =new Member();
//            member.setMemberNo(row.get(1).toString());
//            member.setMemberName(row.get(2).toString());
//            member.setOneType(row.get(3).toString());
//            member.setSecondType(row.get(4).toString());
//            member.setName(row.get(5).toString());
//            member.setBrand(row.get(6).toString());
//            member.setSpecs(row.get(7).toString());
//            member.setCount(Long.valueOf(row.get(8).toString()));
//            member.setStatus(row.get(9).toString());
//            member.setRemark(row.get(10).toString());
////            member.setCreateTime(simpleDateFormat.parse(row.get(10).toString()));
////            member.setUpdateTime(simpleDateFormat.parse(row.get(11).toString()));
//            /**↓物品导入逻辑↓**/
//            //比对物品是否存在
//            MemberParam memberParam =new MemberParam();
//            memberParam.setMemberName(member.getMemberName());
//            memberParam.setOneType(member.getOneType());
//            memberParam.setSecondType(member.getSecondType());
//            memberParam.setBrand(member.getBrand());
//            memberParam.setName(member.getName());
//            memberParam.setSpecs(member.getSpecs());
//            //如果有同样的物品 改变数量即可
//            List<Member> memberIPage = memberService.queryMemberByConditions(memberParam);
//            if(memberIPage!=null){
//                Long num =  memberIPage.get(0).getCount();
//                Long result=  num+member.getCount();
//                member.setCount(result);
//            }
//            else {
//                member.setMemberNo(Random2Utils.buildSn("S")+i);
//                member.setStatus("正常");
//                i++;
//            }
//            /**↑物品导入逻辑↑**/
//            memberList.add(member);
//        }
//        memberService.insertOrUpdateBatch(memberList);
//        System.out.println(list);
//        return  true;
//    }

}