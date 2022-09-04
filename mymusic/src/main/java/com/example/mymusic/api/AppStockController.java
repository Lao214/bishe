package com.example.mymusic.api;


import cn.hutool.core.collection.CollUtil;
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
import com.example.mymusic.entity.Article;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.param.AreaParam;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IAreaService;
import com.example.mymusic.service.IStockService;
import com.example.mymusic.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
@RequestMapping("/app/stock")
public class AppStockController {

    private static Logger logger = LoggerFactory.getLogger(AppStockController.class);

    @Resource
    private IStockService stockService;

    @Resource
    private IAreaService areaService;


    /**
     * App查询区域列表多项联动
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryAreaList")
    public WebPageResponse queryAreaList() {
        WebPageResponse response =new WebPageResponse();
        AreaParam areaParam =new AreaParam();
        try {
            List<Area> pages=  areaService.queryAreaByConditionApp(areaParam);
            response.setData(pages);
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
    @GetMapping(value = "/queryStockList")
    public WebPageResponse queryStockList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        StockParam stockParam =new StockParam();
        try {
        Long total= stockService.queryStockCountByCondition(stockParam);
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        stockParam.setLimit(limit);
        stockParam.setStart(start);
        List<Stock> pages=  stockService.queryStockByConditions(stockParam);
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
    @PostMapping(value = "/queryStockListByCondition")
    public WebPageResponse queryStockListByCondition(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit,@RequestBody Stock stock) {
        WebPageResponse response =new WebPageResponse();
        StockParam stockParam =new StockParam();
        BeanUtils.copyPropertiesIgnoreNull(stock, stockParam, true);
        try {
            Long total= stockService.queryStockCountByCondition(stockParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            stockParam.setLimit(limit);
            stockParam.setStart(start);
            List<Stock> pages=  stockService.queryStockByConditionApp(stockParam);
            /**转换时间戳**/
            SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(total>0){
                for (Stock stock1:pages) {
                    stock1.setCreateTimeStr(sim2.format(stock1.getCreateTime()));
                }
            }
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
    @PostMapping(value = "/queryStockListByConditions")
    public WebPageResponse queryStockListByConditions(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit,@RequestBody Stock stock) {
        WebPageResponse response =new WebPageResponse();
        StockParam stockParam =new StockParam();
        BeanUtils.copyPropertiesIgnoreNull(stock, stockParam, true);
        try {
            Long total= stockService.queryStockCountByCondition(stockParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            stockParam.setLimit(limit);
            stockParam.setStart(start);
            List<Stock> pages=  stockService.queryStockByConditions(stockParam);
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
    @PostMapping(value = "/addStock")
    public WebResponse addStock(@RequestBody Stock stock){
        WebResponse webResponse =new WebResponse();
        try {
            //不输入完全会报错
            if (StringUtils.isEmpty(stock.getStockName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "地址还没输入");
            }
            if (StringUtils.isEmpty(stock.getOneType())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请选择一级分类");
            }
            if (StringUtils.isEmpty(stock.getSecondType())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请选择二级级分类");
            }
            if (StringUtils.isEmpty(stock.getName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入物品名称");
            }
            if (StringUtils.isEmpty(stock.getBrand())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "输入品牌");
            }
            if (StringUtils.isEmpty(stock.getSpecs())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入规格");
            }
            if (stock.getCount()==null) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "数量不合法");
            }
            if (stock.getCount()==0) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入大于0的数量");
            }
            //比对
            StockParam stockParam =new StockParam();
            stockParam.setStockName(stock.getStockName());
            stockParam.setOneType(stock.getOneType());
            stockParam.setSecondType(stock.getSecondType());
            stockParam.setBrand(stock.getBrand());
            stockParam.setName(stock.getName());
            stockParam.setSpecs(stock.getSpecs());
            //如果有同样的物品 改变数量即可
            IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
            if(stockIPage.getTotal()!=0){
                List<Stock> s  = (List<Stock>) stockIPage.getRows();
                Long num =  s.get(0).getCount();
                Long result=  num+stock.getCount();
                stock.setCount(result);
                stockService.updateStock(stock);
            }else {
                stockService.addStock(stock);
            }
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(stock);
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
    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateStock(@RequestBody Stock stock){
        WebResponse response =new WebResponse();
        try {
            stockService.updateStock(stock);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(stock);
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
    @RequestMapping(value="/deleteStock/{stockNo}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse deleteStock(@PathVariable String stockNo){
        WebResponse response =new WebResponse();
        try {
            stockService.delStock(stockNo);
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
           StockParam stockParam =new StockParam();
        try {
            /**从数据库查询出所有的数据**/
            List<Stock> list =stockService.queryStockByConditions(stockParam);
            /**在内存操作，写出到浏览器**/
            ExcelWriter writer = ExcelUtil.getWriter(true);
            /**自定义表头**/
            writer.addHeaderAlias("stockNo","物品编号");
            writer.addHeaderAlias("stockName","地区");
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
     * 导入接口
     */
    @PostMapping("/import")
    public  Boolean  importStock(MultipartFile file) throws Exception{
        InputStream inputStream =file.getInputStream();
        ExcelReader reader =ExcelUtil.getReader(inputStream);
          // 该方式通过javabean来读取Excel的对象，表头得是英文的
          //List<Stock> list =reader.readAll(Stock.class);
        List<List<Object>> list =reader.read(1);
        List<Stock> stockList = CollUtil.newArrayList();
        Long i =0l;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (List<Object> row:list){
            Stock stock =new Stock();
            stock.setStockNo(row.get(1).toString());
            stock.setStockName(row.get(2).toString());
            stock.setOneType(row.get(3).toString());
            stock.setSecondType(row.get(4).toString());
            stock.setName(row.get(5).toString());
            stock.setBrand(row.get(6).toString());
            stock.setSpecs(row.get(7).toString());
            stock.setCount(Long.valueOf(row.get(8).toString()));
            stock.setStatus(row.get(9).toString());
            stock.setRemark(row.get(10).toString());
//            stock.setCreateTime(simpleDateFormat.parse(row.get(10).toString()));
//            stock.setUpdateTime(simpleDateFormat.parse(row.get(11).toString()));
            /**↓物品导入逻辑↓**/
            //比对物品是否存在
            StockParam stockParam =new StockParam();
            stockParam.setStockName(stock.getStockName());
            stockParam.setOneType(stock.getOneType());
            stockParam.setSecondType(stock.getSecondType());
            stockParam.setBrand(stock.getBrand());
            stockParam.setName(stock.getName());
            stockParam.setSpecs(stock.getSpecs());
            //如果有同样的物品 改变数量即可
            List<Stock> stockIPage = stockService.queryStockByConditions(stockParam);
            if(stockIPage!=null){
                Long num =  stockIPage.get(0).getCount();
                Long result=  num+stock.getCount();
                stock.setCount(result);
            }
            else {
                stock.setStockNo(Random2Utils.buildSn("S")+i);
                stock.setStatus("正常");
                i++;
            }
            /**↑物品导入逻辑↑**/
            stockList.add(stock);
        }
        stockService.insertOrUpdateBatch(stockList);
        System.out.println(list);
        return  true;
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
        headers.put(0, "stockName");
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
//    @RequestMapping("/exportStock.do")
//    @ResponseBody
//    public String exportStock(HttpServletResponse response, StockParam stockParam, HttpSession session) {
//        stockParam.setStart(0);
//        stockParam.setLimit(999999999);
//        IPage<Stock> page = stockService.queryStockByCondition(stockParam);
//        List<Stock> exportData = (List) page.getRows();
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
//            ExcelUtil.dataListToExcel(exportData, "订单列表", headers, colFieldMapping, Stock.class,
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
//    @RequestMapping("/exportStockModel.do")
//    @ResponseBody
//    public String exportStockModel(HttpServletResponse response, StockParam stockParam, HttpSession session) {
//        stockParam.setStart(0);
//        stockParam.setLimit(999999999);
//        IPage<Stock> page = stockService.queryStockByCondition(stockParam);
//        List<Stock> exportData = (List) page.getRows();
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
//    @RequestMapping(value = "/importStock.do",produces="text/html;charset=UTF-8")
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
//                Stock stock = new Stock();
////
////            if(map.containsKey(0)){
////                //去除小数点
////                Double count = (Double) map.get(0);
////               stock.setStockNo(String.valueOf(count.longValue()));
////            }
////            if(map.containsKey(1)){
////                //去除小数点
////                Double count = (Double) map.get(1);
////                stock.setOrderNo(String.valueOf(count.longValue()));
////            }
//                if(map.containsKey(0)) stock.setStockName(map.get(0).toString());
//                if(map.containsKey(1)) stock.setOneType(map.get(1).toString());
//                if(map.containsKey(2)) stock.setSecondType(map.get(2).toString());
//                if(map.containsKey(3)) stock.setName(map.get(3).toString());
//                if(map.containsKey(4)) stock.setBrand(map.get(4).toString());
//                if(map.containsKey(5)) stock.setSpecs(map.get(5).toString());
//                if(map.containsKey(6)) {
//                    Double count = (Double) map.get(6);
//                    stock.setCount(count.longValue());
//                }
//                if(map.containsKey(7)) stock.setStatus(map.get(7).toString());
//                if(map.containsKey(8)) stock.setRemark(map.get(8).toString());
//                //对比物品
//                StockParam stockParam =new StockParam();
//                stockParam.setStockName(stock.getStockName());
//                stockParam.setOneType(stock.getOneType());
//                stockParam.setSecondType(stock.getSecondType());
//                stockParam.setBrand(stock.getBrand());
//                stockParam.setName(stock.getName());
//                stockParam.setSpecs(stock.getSpecs());
//                IPage<Stock>  stockIPage = stockService.queryStockByCondition(stockParam);
//                //如果有 则 更新数量
//                if(stockIPage.getTotal()!=0) {
//                    List<Stock> s = (List<Stock>) stockIPage.getRows();
//                    //   Long num = s.get(0).getCount();
//                    String stockNo = s.get(0).getStockNo();
//                    //  Long result = num + stock.getCount();
//                    Stock stock2 = stockService.queryStockByStockNo(stockNo);
//                    stock2.setCount(stock.getCount());
//                    stockService.updateStock(stock2);
//                    return ConstantVariable.SUCCESS;
//                }
//                else {
//                    //如果没有 插入一条物品
//                    //放入数据
//                    stock.setStockName(stock.getStockName());
//                    stock.setOneType(stock.getOneType());
//                    stock.setSecondType(stock.getSecondType());
//                    stock.setBrand(stock.getBrand());
//                    stock.setName(stock.getName());
//                    stock.setSpecs(stock.getSpecs());
//                    stock.setCount(stock.getCount());
//                    stock.setStatus(stock.getStatus());
//                    stockService.addStock(stock);
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