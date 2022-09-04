package com.example.mymusic.op;



import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;

import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Admin;
import com.example.mymusic.entity.Area;
import com.example.mymusic.entity.param.AreaParam;
import com.example.mymusic.service.AdminService;
import com.example.mymusic.service.IAreaService;
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
@RequestMapping("/api/area")
public class AreaAction {

    private static Logger logger = LoggerFactory.getLogger(AreaAction.class);

    @Resource
    private IAreaService areaService;

    @Resource
    private AdminService adminService;



    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryAreaList")
    public WebPageResponse queryAreaList(@RequestParam(defaultValue = "") String areaNo,@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        AreaParam areaParam =new AreaParam();
        areaParam.setParentAreaNo(areaNo);
        try {
        Long total= areaService.queryAreaCountByCondition(areaParam);
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        areaParam.setLimit(limit);
        areaParam.setStart(start);
        List<Area> pages=  areaService.queryAreaByConditions(areaParam);
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
     * 方法说明：添加库存表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @PostMapping(value = "/addArea")
    public WebResponse addArea(@RequestBody Area area){
        WebResponse webResponse =new WebResponse();
        try {
            //不输入完全会报错
            if (StringUtils.isEmpty(area.getAreaName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "地址还没输入");
            }
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(area);
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
    @RequestMapping(value = "/updateArea", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateArea(@RequestBody Area area){
        WebResponse response =new WebResponse();
        try {
            areaService.updateArea(area);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(area);
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
    @RequestMapping(value="/deleteArea/{areaNo}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse deleteArea(@PathVariable String areaNo){
        WebResponse response =new WebResponse();
        try {
            areaService.delArea(areaNo);
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
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryProvinceList")
    public WebPageResponse queryProvinceList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        AreaParam areaParam =new AreaParam();
        areaParam.setAreaType(1);
        try {
            Long total= areaService.queryAreaCountByCondition(areaParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            areaParam.setLimit(limit);
            areaParam.setStart(start);
            List<Area> pages=  areaService.queryAreaByConditions(areaParam);
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

    @GetMapping(value = "/queryCityList")
    public WebPageResponse queryCityList(@RequestParam(defaultValue = "") String areaNo,@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        AreaParam areaParam =new AreaParam();
        areaParam.setParentAreaNo(areaNo);
        try {
            Long total= areaService.queryAreaCountByCondition(areaParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            areaParam.setLimit(limit);
            areaParam.setStart(start);
            List<Area> pages=  areaService.queryAreaByConditions(areaParam);
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
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryAdminList")
    public WebPageResponse queryAdminList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        Admin admin =new Admin();
        admin.setAdminLevel(2L);
        List<Admin> list=  adminService.queryAdminByCondition(admin);
        response.setData(list);
        response.setStatus(WebResponseCode.SUCCESS);
        response.setMsg("查询成功");
        return response;
    }

}