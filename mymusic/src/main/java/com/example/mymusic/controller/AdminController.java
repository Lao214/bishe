package com.example.mymusic.controller;


import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.enums.Status;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Admin;
import com.example.mymusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 管理员(Admin)表控制层
 *
 * @author makejava
 * @since 2022-01-07 09:52:21
 */
@RestController
@Controller
@RequestMapping("api/admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param adminNo 主键
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/queryAdminbyAdminNo", method = RequestMethod.POST)
    @ResponseBody
    public Admin queryAdminbyAdminNo(String adminNo) {
        return this.adminService.queryAdminByAdminNo(adminNo);
    }


    /**
     * 修改Admin
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return    WebResponse.success(admin);
    }


    /**
     * 验证查询该管理员的信息
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getById(@PathVariable Long id) {
        Admin admin= adminService.queryAdminById(id);
        return  WebResponse.success(admin);
    }


    /**
     * 修改Admin
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "deleteAdmin/{adminNo}", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteAdmin(@PathVariable String adminNo) {
        WebResponse webResponse =new WebResponse();
        adminService.deleteAdminByAdminNo(adminNo);
        webResponse.setMsg("删除成功");
        webResponse.setStatus(WebResponseCode.SUCCESS);
        return   webResponse;
    }




    /**
     * 添加(Admin)
     *
     * @param admin 主键
     * @return 单条数据
     * @Author 劳威锟
     */
    @PostMapping(value = "/addAdmin")
    public WebResponse insertAdmin(@RequestBody Admin admin) {
        WebResponse webResponse =new WebResponse();
        admin.setAdminNo(Random2Utils.orderBuildSn("admin"));
        admin.setStatus(Status.common.name());
        adminService.insert(admin);
        webResponse.setStatus(WebResponseCode.SUCCESS);
        webResponse.setData(admin);
        webResponse.setMsg("添加成功");
        return webResponse;
    }

    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryAdminList")
    public WebPageResponse queryAdminList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        Long total= adminService.queryAllCount();
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        List<Admin> pages=  adminService.queryAllByLimit(start,limit);
        response.setData(pages);
        response.setOffset(start);
        response.setLimit(limit);
        response.setTotal(total);
        response.setStatus(WebResponseCode.SUCCESS);
        response.setMsg("查询成功");
        return response ;
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 管理员登录
     * @Date 11:24 2022/1/19
     * @Param
     * @return
     **/
    @PostMapping(value = "/login")
    public WebResponse login(@RequestBody Admin admin) {
        WebResponse webResponse =new WebResponse();
        Admin res=  adminService.login(admin);
        if(res!=null){
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(res);
            webResponse.setMsg("登录成功");
        }else {
            webResponse.setStatus(WebResponseCode.FAIL);
            webResponse.setMsg("用户名或密码错误");
        }
        return webResponse;
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 管理员注册
     * @Date 14:01 2022/1/19
     * @Param
     * @return
     **/
    @PostMapping(value = "/register")
    public WebResponse register(@RequestBody Admin admin) {
        WebResponse webResponse =new WebResponse();
        Admin res=  adminService.login(admin);
        if(res!=null){
            webResponse.setStatus(WebResponseCode.FAIL);
            webResponse.setMsg("该用户名已占用");
        }else {
            admin.setAdminNo(Random2Utils.buildSn("admin"));
            admin.setStatus(Status.common.toString());
            adminService.insert(admin);
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setMsg("注册成功");
        }
        return webResponse;
    }

}
