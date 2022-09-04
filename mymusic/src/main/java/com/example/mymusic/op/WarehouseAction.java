package com.example.mymusic.op;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Area;
import com.example.mymusic.entity.Warehouse;
import com.example.mymusic.entity.param.WarehouseParam;
import com.example.mymusic.service.IAreaService;
import com.example.mymusic.service.IWareHouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类说明：库存表管理接口
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
@RestController
@Controller
@RequestMapping("/api/warehouse")
public class WarehouseAction {

    private static Logger logger = LoggerFactory.getLogger(WarehouseAction.class);

    @Resource
    private IWareHouseService warehouseService;
    @Resource
    private IAreaService areaService;

    @Value("${server.port}")
    private String port;

    private  static  final  String ip="http://localhost";


    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryWarehouseList")
    public WebPageResponse queryWarehouseList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        WarehouseParam warehouseParam =new WarehouseParam();
        try {
        Long total= warehouseService.queryWarehouseCountByCondition(warehouseParam);
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        warehouseParam.setLimit(limit);
        warehouseParam.setStart(start);
        List<Warehouse> pages=  warehouseService.queryWarehouseByConditionManager(warehouseParam);
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
    @PostMapping(value = "/addWarehouse")
    public WebResponse addWarehouse(@RequestBody Warehouse warehouse){
        WebResponse webResponse =new WebResponse();
        try {
            byte[] imagedata = DatatypeConverter.parseBase64Binary(warehouse.getImageUrl().substring(warehouse.getImageUrl().indexOf(",") + 1));
            String originalFilename =  warehouse.getFilename();         //获取源文件名称
            String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
            String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
            FileUtil.writeBytes(imagedata,rootFilePath);
            warehouse.setCover(ip+":"+port+"/api/file/"+flag);
            Area ap = areaService.queryAreaByAreaNo(warehouse.getProvinceNo());
            Area ac = areaService.queryAreaByAreaNo(warehouse.getCityNo());
            Area aa = areaService.queryAreaByAreaNo(warehouse.getAreaNoA());
            warehouse.setProvince(ap.getAreaName());
            warehouse.setCity(ac.getAreaName());
            warehouse.setArea(aa.getAreaName());
            warehouseService.addWarehouse(warehouse);
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(warehouse);
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
    @RequestMapping(value = "/updateWarehouse", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateWarehouse(@RequestBody Warehouse warehouse){
        WebResponse response =new WebResponse();
        try {
            byte[] imagedata = DatatypeConverter.parseBase64Binary(warehouse.getImageUrl().substring(warehouse.getImageUrl().indexOf(",") + 1));
            String originalFilename =  warehouse.getFilename();         //获取源文件名称
            String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
            String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
            FileUtil.writeBytes(imagedata,rootFilePath);
            warehouse.setCover(ip+":"+port+"/api/file/"+flag);
            Area ap = areaService.queryAreaByAreaNo(warehouse.getProvinceNo());
            Area ac = areaService.queryAreaByAreaNo(warehouse.getCityNo());
            Area aa = areaService.queryAreaByAreaNo(warehouse.getAreaNoA());
            warehouse.setProvince(ap.getAreaName());
            warehouse.setCity(ac.getAreaName());
            warehouse.setArea(aa.getAreaName());
            warehouseService.updateWarehouse(warehouse);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(warehouse);
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
    @RequestMapping(value="/deleteWarehouse/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse deleteWarehouse(@PathVariable Long id){
        WebResponse response =new WebResponse();
        try {
            warehouseService.delWarehouse(id);
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
     * 导出接口
     */
    @GetMapping("export")
    public  void  export(HttpServletResponse response) throws  Exception{
           WarehouseParam warehouseParam =new WarehouseParam();
        try {
            /**从数据库查询出所有的数据**/
            List<Warehouse> list =warehouseService.queryWarehouseByConditions(warehouseParam);
            /**在内存操作，写出到浏览器**/
            ExcelWriter writer = ExcelUtil.getWriter(true);
            /**自定义表头**/
            writer.addHeaderAlias("warehouseNo","物品编号");
            writer.addHeaderAlias("warehouseName","地区");
            writer.addHeaderAlias("oneType","一级分类");
            writer.addHeaderAlias("secondType","二级分类");
            writer.addHeaderAlias("name","货物名称");
            writer.addHeaderAlias("brand","品牌");
            writer.addHeaderAlias("specs","规格");
            writer.addHeaderAlias("count","数量");
            writer.addHeaderAlias("status","状态");
            writer.addHeaderAlias("remark","备注");
            writer.addHeaderAlias("createTime","创建时间");
            writer.addHeaderAlias("updateTime","更新时间");
            /**一次性写出list内的对象到excel，默认样式，强制输出标题**/
            writer.write(list,true);

            /**设置浏览器响应的格式  一般是固定的**/
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String fileName = URLEncoder.encode("库存单","UTF-8");
            /**输出文件名**/
            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
            ServletOutputStream outputStream =response.getOutputStream();
            writer.flush(outputStream,true);
            outputStream.close();
            writer.close();
        }catch (FdServiceException e){

        }

    }



    /**
     * @Author 劳威锟
     * @Description //方法说明： 构建数据映射关系
     * @Date 18:02 2021/10/13
     * @Param 
     * @return 
     **/
    private Map<Integer, String> buildColFieldMapping2() {
        Map<Integer, String> headers = new HashMap<>();
        headers.put(0, "warehouseName");
        headers.put(1, "oneType");
        headers.put(2, "secondType");
        headers.put(3, "name");
        headers.put(4, "brand");
        headers.put(5, "specs");
        headers.put(6, "count");
        headers.put(7, "status");
        headers.put(8, "remark");
        return headers;
    }


    /**
     * @Author 劳威锟
     * @Description //方法说明： 构建数据映射关系(下载模板)
     * @Date 18:02 2021/10/13
     * @Param
     * @return
     **/
    private Map<Integer, String> buildColFieldMapping3() {
        Map<Integer, String> headers = new HashMap<>();
        return headers;
    }


  /**
   * @Author 劳威锟
   * @Description //方法说明： 生成表格头
   * @Date 18:04 2021/10/13
   * @Param
   * @return
   **/
    private Map<Integer, String> buildHeaders2() {
        Map<Integer, String> headers = new HashMap<>();
        headers.put(0, "地址");
        headers.put(1, "一级分类");
        headers.put(2, "二级分类");
        headers.put(3, "物品名称");
        headers.put(4, "品牌");
        headers.put(5, "规格");
        headers.put(6, "数量");
        headers.put(7, "状态");
        headers.put(8, "备注");
        return headers;
    }




//   /**
//    * @Author 劳威锟
//    * @Description //方法说明： 导出物品表格
//    * @Date 18:06 2021/10/13
//    * @Param
//    * @return
//    **/
//    @RequestMapping("/exportWarehouse.do")
//    @ResponseBody
//    public String exportWarehouse(HttpServletResponse response, WarehouseParam warehouseParam, HttpSession session) {
//        warehouseParam.setStart(0);
//        warehouseParam.setLimit(999999999);
//        IPage<Warehouse> page = warehouseService.queryWarehouseByCondition(warehouseParam);
//        List<Warehouse> exportData = (List) page.getRows();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Map<Integer, String> headers = buildHeaders2();
//        Map<Integer, String> colFieldMapping = buildColFieldMapping2();
//        String dateStr = "yyyyMMddHHmmssSSS";
//        // 指定下载的文件名
//        response.setHeader("Content-Disposition", "attachment;filename=" + dateStr + ".xlsx");
//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        OutputStream output;
//        try {
//            output = response.getOutputStream();
//            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
//            ExcelUtil.dataListToExcel(exportData, "订单列表", headers, colFieldMapping, Warehouse.class,
//                    bufferedOutPut);
//            bufferedOutPut.flush();
//            bufferedOutPut.close();
//            return null;
//        } catch (Exception e) {
//            logger.error("导出Excel文件异常");
//            return "导出文件失败：" + e.getMessage();
//        }
//    }


//    /**
//     * @Author 劳威锟
//     * @Description //方法说明： 下载模板
//     * @Date 18:06 2021/10/13
//     * @Param
//     * @return
//     **/
//    @RequestMapping("/exportWarehouseModel.do")
//    @ResponseBody
//    public String exportWarehouseModel(HttpServletResponse response, WarehouseParam warehouseParam, HttpSession session) {
//        warehouseParam.setStart(0);
//        warehouseParam.setLimit(999999999);
//        IPage<Warehouse> page = warehouseService.queryWarehouseByCondition(warehouseParam);
//        List<Warehouse> exportData = (List) page.getRows();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Map<Integer, String> headers = buildHeaders2();
//        Map<Integer, String> colFieldMapping = buildColFieldMapping3();
//        String dateStr = DateUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS");
//        // 指定下载的文件名
//        response.setHeader("Content-Disposition", "attachment;filename=" + dateStr + ".xlsx");
//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        OutputStream output;
//        try {
//            output = response.getOutputStream();
//            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
//            ExcelUtilKun.dataListToExcelModel(exportData, "订单列表", headers, colFieldMapping,
//                    bufferedOutPut);
//            bufferedOutPut.flush();
//            bufferedOutPut.close();
//            return null;
//        } catch (Exception e) {
//            logger.error("导出Excel文件异常");
//            return "导出文件失败：" + e.getMessage();
//        }
//    }



//    /**
//     * @Author 劳威锟
//     * @Description //方法说明： 导入execl表
//     * @Date 14:54 2021/10/14
//     * @Param
//     * @return
//     **/
//    @RequestMapping(value = "/importWarehouse.do",produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String importData(@RequestPart("file") MultipartFile file, HttpServletResponse response, HttpSession session) {
//        try{
//
//            if (StringUtils.isEmpty(file.getOriginalFilename())) {
//                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "没有选择文件");
//            }
//
//            //判断文件格式是否合适
//            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            if(".xls".equals(fileType)||".xlsx".equals(fileType)){
//            }else{
//                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "文件格式有误");
//            }
//
//            List<HashMap> list = ExcelUtilKun.getTemplateFileByInputStream(file.getInputStream(), file.getOriginalFilename());
//            for (HashMap map :
//                    list) {
//
////            if (StringUtils.isEmpty(file.getOriginalFilename())) {
////                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请导入xlsx或xls文件");
////            }
//
//                Warehouse warehouse = new Warehouse();
////
////            if(map.containsKey(0)){
////                //去除小数点
////                Double count = (Double) map.get(0);
////               warehouse.setWarehouseNo(String.valueOf(count.longValue()));
////            }
////            if(map.containsKey(1)){
////                //去除小数点
////                Double count = (Double) map.get(1);
////                warehouse.setOrderNo(String.valueOf(count.longValue()));
////            }
//                if(map.containsKey(0)) warehouse.setWarehouseName(map.get(0).toString());
//                if(map.containsKey(1)) warehouse.setOneType(map.get(1).toString());
//                if(map.containsKey(2)) warehouse.setSecondType(map.get(2).toString());
//                if(map.containsKey(3)) warehouse.setName(map.get(3).toString());
//                if(map.containsKey(4)) warehouse.setBrand(map.get(4).toString());
//                if(map.containsKey(5)) warehouse.setSpecs(map.get(5).toString());
//                if(map.containsKey(6)) {
//                    Double count = (Double) map.get(6);
//                    warehouse.setCount(count.longValue());
//                }
//                if(map.containsKey(7)) warehouse.setStatus(map.get(7).toString());
//                if(map.containsKey(8)) warehouse.setRemark(map.get(8).toString());
//                //对比物品
//                WarehouseParam warehouseParam =new WarehouseParam();
//                warehouseParam.setWarehouseName(warehouse.getWarehouseName());
//                warehouseParam.setOneType(warehouse.getOneType());
//                warehouseParam.setSecondType(warehouse.getSecondType());
//                warehouseParam.setBrand(warehouse.getBrand());
//                warehouseParam.setName(warehouse.getName());
//                warehouseParam.setSpecs(warehouse.getSpecs());
//                IPage<Warehouse>  warehouseIPage = warehouseService.queryWarehouseByCondition(warehouseParam);
//                //如果有 则 更新数量
//                if(warehouseIPage.getTotal()!=0) {
//                    List<Warehouse> s = (List<Warehouse>) warehouseIPage.getRows();
//                    //   Long num = s.get(0).getCount();
//                    String warehouseNo = s.get(0).getWarehouseNo();
//                    //  Long result = num + warehouse.getCount();
//                    Warehouse warehouse2 = warehouseService.queryWarehouseByWarehouseNo(warehouseNo);
//                    warehouse2.setCount(warehouse.getCount());
//                    warehouseService.updateWarehouse(warehouse2);
//                    return ConstantVariable.SUCCESS;
//                }
//                else {
//                    //如果没有 插入一条物品
//                    //放入数据
//                    warehouse.setWarehouseName(warehouse.getWarehouseName());
//                    warehouse.setOneType(warehouse.getOneType());
//                    warehouse.setSecondType(warehouse.getSecondType());
//                    warehouse.setBrand(warehouse.getBrand());
//                    warehouse.setName(warehouse.getName());
//                    warehouse.setSpecs(warehouse.getSpecs());
//                    warehouse.setCount(warehouse.getCount());
//                    warehouse.setStatus(warehouse.getStatus());
//                    warehouseService.addWarehouse(warehouse);
//                    return ConstantVariable.SUCCESS;
//                }
//            }
//
//            return ConstantVariable.SUCCESS;
//        }
//        //重定位
//       catch (FdServiceException e) {
//            logger.error(e.getExceptionInfo(), e);
//            return e.getExceptionInfo();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return e.getMessage();
//        }
//    }

}