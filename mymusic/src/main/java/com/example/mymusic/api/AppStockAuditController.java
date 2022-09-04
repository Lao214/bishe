package com.example.mymusic.api;


import com.example.mymusic.common.ConstantVariable;
import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.StockAudit;
import com.example.mymusic.entity.StockOut;
import com.example.mymusic.entity.param.StockAuditParam;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IStockAuditService;
import com.example.mymusic.service.IStockOutService;
import com.example.mymusic.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 类说明：出库审核表管理接口
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
@RestController
@Controller
@RequestMapping("/app/stockAudit")
public class AppStockAuditController {

    private static Logger logger = LoggerFactory.getLogger(AppStockAuditController.class);

    @Resource
    private IStockAuditService stockAuditService;
    @Resource
    private IStockService stockService;
    @Resource
    private IStockOutService stockOutService;

    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryAuditDetails")
    public WebPageResponse queryAuditDetails(@RequestParam(defaultValue = "") Long id) {
        WebPageResponse response =new WebPageResponse();
        try {
            StockAudit stockAudit= stockAuditService.queryStockAuditByStockAuditId(id);
            response.setData(stockAudit);
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
    @GetMapping(value = "/queryAuditList")
    public WebPageResponse queryAuditList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit,@RequestParam(defaultValue = "") String orderNo,@RequestParam(defaultValue = "") String applyUser) {
        WebPageResponse response =new WebPageResponse();
        StockAuditParam stockAuditParam =new StockAuditParam();
        stockAuditParam.setOrderNo(orderNo);
        stockAuditParam.setApplyUser(applyUser);
        stockAuditParam.setStatus("待审核");
        try {
            Long total= stockAuditService.queryStockAuditCountByCondition(stockAuditParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            stockAuditParam.setLimit(limit);
            stockAuditParam.setStart(start);
            List<StockAudit> pages=  stockAuditService.queryStockAuditByConditions(stockAuditParam);
            /**转换时间戳**/
            SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(total>0){
                for (StockAudit stockAudit:pages) {
                    stockAudit.setCreateTimeStr(sim2.format(stockAudit.getCreateTime()));
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
    @GetMapping(value = "/queryMyAuditList")
    public WebPageResponse queryMyAuditList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit,@RequestParam(defaultValue = "") String orderNo,@RequestParam(defaultValue = "") String applyUser) {
        WebPageResponse response =new WebPageResponse();
        StockAuditParam stockAuditParam =new StockAuditParam();
        stockAuditParam.setOrderNo(orderNo);
        stockAuditParam.setApplyUser(applyUser);
        try {
            Long total= stockAuditService.queryStockAuditCountByCondition(stockAuditParam);
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            stockAuditParam.setLimit(limit);
            stockAuditParam.setStart(start);
            List<StockAudit> pages=  stockAuditService.queryStockAuditByConditions(stockAuditParam);
            /**转换时间戳**/
            SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(total>0){
                for (StockAudit stockAudit:pages) {
                    stockAudit.setCreateTimeStr(sim2.format(stockAudit.getCreateTime()));
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
     * 方法说明：添加出入库申请
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @PostMapping(value = "/addStockAudit")
    public WebResponse addStockAudit(@RequestBody StockAudit stockAudit){
        WebResponse webResponse =new WebResponse();
        try {
            //不输入完全会报错
            if (StringUtils.isEmpty(stockAudit.getStockAuditName())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "地址还没输入");
            }
            if (StringUtils.isEmpty(stockAudit.getOneType())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请选择一级分类");
            }
            if (StringUtils.isEmpty(stockAudit.getSecondType())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请选择二级级分类");
            }
            if (StringUtils.isEmpty(stockAudit.getName())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请输入物品名称");
            }
            if (StringUtils.isEmpty(stockAudit.getBrand())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "输入品牌");
            }
            if (StringUtils.isEmpty(stockAudit.getOrderNo())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请选择操作类型");
            }
            if (StringUtils.isEmpty(stockAudit.getSpecs())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请输入规格");
            }
            if (stockAudit.getCount()==null) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "数量不合法");
            }
            if (stockAudit.getCount()==0) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请输入大于0的数量");
            }
            stockAuditService.addStockAudit(stockAudit);
            webResponse.setStatus(WebResponseCode.SUCCESS);
            webResponse.setData(stockAudit);
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
    @RequestMapping(value = "/updateStockAudit", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateStockAudit(@RequestBody StockAudit stockAudit){
        WebResponse response =new WebResponse();
        try {
            stockAuditService.updateStockAudit(stockAudit);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setData(stockAudit);
            response.setMsg("修改成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }


    /**
     * 方法说明：删除该审核
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @RequestMapping(value="/deleteStockAudit/{stockAuditNo}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse deleteStock(@PathVariable String stockAuditNo){
        WebResponse response =new WebResponse();
        try {
            stockAuditService.delStockAudit(stockAuditNo);
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
     * 方法说明：通过审核
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    @RequestMapping(value="/stockAuditPass/{stockAuditNo}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse stockAuditPass(@PathVariable String stockAuditNo){
        WebResponse response =new WebResponse();
        try {
            StockAudit stockAudit = stockAuditService.queryStockAuditByStockAuditNo(stockAuditNo);
            stockAudit.setStatus("审核通过");
            /**↓出库逻辑↓**/
            if(stockAudit.getOrderNo().equals("出库")){
                //比对
                StockParam stockParam =new StockParam();
                stockParam.setStockName(stockAudit.getStockAuditName());
                stockParam.setOneType(stockAudit.getOneType());
                stockParam.setSecondType(stockAudit.getSecondType());
                stockParam.setBrand(stockAudit.getBrand());
                stockParam.setName(stockAudit.getName());
                stockParam.setSpecs(stockAudit.getSpecs());
                //如果有同样的物品 改变数量即可
                IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
                if(stockIPage.getTotal()!=0){
                    List<Stock> s  = (List<Stock>) stockIPage.getRows();
                    Long num =  s.get(0).getCount();
                    String stockNo= s.get(0).getStockNo();
                    Long result=  num-stockAudit.getCount();
                    if(result<0){
                        response.setStatus(WebResponseCode.FAIL);
                        response.setMsg(ErrorCode.UNDER_STOCK);
                        throw new FdServiceException(ErrorCode.UNDER_STOCK, "库存不足");
                    }
                    Stock stock = stockService.queryStockByStockNo(stockNo);
                    stock.setCount(result);
                    stockService.updateStock(stock);
                }
                else {
                    response.setStatus(WebResponseCode.FAIL);
                    response.setMsg(ErrorCode.NO_STOCK);
                    throw new FdServiceException(ErrorCode.NO_STOCK, "已经没有库存");
                }
            }
            /**↑出库逻辑↑**/
            /**↓入库逻辑↓**/
            if(stockAudit.getOrderNo().equals("入库")){
                //比对
                StockParam stockParam =new StockParam();
                stockParam.setStockName(stockAudit.getStockAuditName());
                stockParam.setOneType(stockAudit.getOneType());
                stockParam.setSecondType(stockAudit.getSecondType());
                stockParam.setBrand(stockAudit.getBrand());
                stockParam.setName(stockAudit.getName());
                stockParam.setSpecs(stockAudit.getSpecs());
                //如果有同样的物品 改变数量即可
                IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
                if(stockIPage.getTotal()!=0){
                    List<Stock> s  = (List<Stock>) stockIPage.getRows();
                    Long num =  s.get(0).getCount();
                    String stockNo= s.get(0).getStockNo();
                    Long result=  num+stockAudit.getCount();
                    Stock stock = stockService.queryStockByStockNo(stockNo);
                    stock.setCount(result);
                    stockService.updateStock(stock);
                }
                else {
                    Stock stock =new Stock();
                    stock.setStockName(stockAudit.getStockAuditName());
                    stock.setOneType(stockAudit.getOneType());
                    stock.setSecondType(stockAudit.getSecondType());
                    stock.setBrand(stockAudit.getBrand());
                    stock.setName(stockAudit.getName());
                    stock.setSpecs(stockAudit.getSpecs());
                    stock.setCount(stockAudit.getCount());
                    stockService.addStock(stock);
                }
            }
            /**↑入库逻辑↑**/
            stockAuditService.updateStockAudit(stockAudit);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("审核通过成功");
        } catch (FdServiceException e) {
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
    @RequestMapping(value="/stockAuditReject/{stockAuditNo}",method = RequestMethod.POST)
    @ResponseBody
    public  WebResponse stockAuditReject(@PathVariable String stockAuditNo){
        WebResponse response =new WebResponse();
        try {
            StockAudit stockAudit = stockAuditService.queryStockAuditByStockAuditNo(stockAuditNo);
            stockAudit.setStatus("驳回");
            stockAuditService.updateStockAudit(stockAudit);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("审核驳回成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 修改审核表
     * @Date 10:47 2021/10/11
     * 调试成功
     **/
    @RequestMapping(value="/updateStockAudit.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String updateStockAudits(StockAudit stockAudit){
        try {
            //设一个新状态
            String newStatus= stockAudit.getStatus();
            //获取旧状态 获取要修改的审核表
            StockAudit stockAudit1 =stockAuditService.queryStockAuditByStockAuditNo(stockAudit.getStockAuditNo());
            //如果新状态不等于旧状态
            if(!newStatus.equals(stockAudit1.getStatus())&&stockAudit1.getOrderNo().equals("出库"))
            {
                stockAuditService.updateStockAudit(stockAudit);
                //如果新状态=审核通过 那么物品数量应该要发生改变。
                if(stockAudit.getStatus().equals("审核通过")&&stockAudit1.getOrderNo().equals("出库")){
                    //比对
                    StockParam stockParam =new StockParam();
                    stockParam.setStockName(stockAudit1.getStockAuditName());
                    stockParam.setOneType(stockAudit1.getOneType());
                    stockParam.setSecondType(stockAudit1.getSecondType());
                    stockParam.setBrand(stockAudit1.getBrand());
                    stockParam.setName(stockAudit1.getName());
                    stockParam.setSpecs(stockAudit1.getSpecs());
                    //如果有同样的物品 改变数量即可
                    IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
                    if(stockIPage.getTotal()!=0){
                        List<Stock> s  = (List<Stock>) stockIPage.getRows();
                        Long num =  s.get(0).getCount();
                        String stockNo= s.get(0).getStockNo();
                        Long result=  num-stockAudit1.getCount();
                        if(result<0){
                            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "库存不足");
                        }
                        //更新数量
                        Stock stock = stockService.queryStockByStockNo(stockNo);
                        stock.setCount(result);
                        stockService.updateStock(stock);
                        //通过订单编号查到相应的出库订单 并把出库表里的出库订单也更新一下状态
                        StockOut stockOut= stockOutService.queryStockOutByStockOutNo(stockAudit1.getStockAuditNo());
                        stockOut.setStatus("审核通过");
                        stockOutService.updateStockOut(stockOut);
                    }
                    else {
                        throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "已经没有库存");
                    }
                }
                //更新出库表的出库订单的状态
                if(stockAudit.getStatus().equals("已驳回")&&stockAudit1.getOrderNo().equals("出库")) {
                    StockOut stockOut= stockOutService.queryStockOutByStockOutNo(stockAudit1.getStockAuditNo());
                    stockOut.setStatus(stockAudit.getStatus());
                    stockOutService.updateStockOut(stockOut);
                }
                if(stockAudit.getStatus().equals("已撤销")&&stockAudit1.getOrderNo().equals("出库")) {
                    StockOut stockOut= stockOutService.queryStockOutByStockOutNo(stockAudit1.getStockAuditNo());
                    stockOut.setStatus(stockAudit.getStatus());
                    stockOutService.updateStockOut(stockOut);
                }
                if(stockAudit.getStatus().equals("待审核")&&stockAudit1.getOrderNo().equals("出库")) {
                    StockOut stockOut= stockOutService.queryStockOutByStockOutNo(stockAudit1.getStockAuditNo());
                    stockOut.setStatus(stockAudit.getStatus());
                    stockOutService.updateStockOut(stockOut);
                }
        }
            return ConstantVariable.SUCCESS;
        } catch (FdServiceException e) {
            logger.error(e.getExceptionInfo(), e);
            return e.getExceptionInfo();
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            return e.getMessage();
        }
    }


//    /**
//     * @Author 劳威锟
//     * @Description //方法说明： 删除审核表
//     * @Date 9:58 2021/10/16
//     * @Param
//     * @return
//     **/
//    @RequestMapping(value="/delStockAudit.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String delStockAudit(String stockAuditNo){
//        try {
//            stockAuditService.delStockAudit(stockAuditNo);
//            stockOutService.delStockOut(stockAuditNo);
//            return ConstantVariable.SUCCESS;
//        } catch (FdServiceException e) {
//            logger.error(e.getExceptionInfo(), e);
//            return e.getExceptionInfo();
//        } catch(Exception e){
//            logger.error(e.getMessage(), e);
//            return e.getMessage();
//        }
//    }
//
//
//    /**
//     * 方法说明：进入查询出库审核表
//     * @author 劳威锟
//     * @date 2021年09月28日 18时19分06秒
//     * @return
//     */
//    @RequestMapping("/toQueryStockAudit.do")
//    public String toQueryStockAudit(Model model){
//        return "console/stockAudit/queryStockAudit";
//    }


//    /**
//     * 方法说明：查询出库审核表
//     * @author 劳威锟
//     * @date 2021年09月28日 18时19分06秒
//     * @return
//     */
//    @RequestMapping(value="/queryStockAudit.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String queryStockAudit(StockAuditParam stockAuditParam){
//        try {
//            Long count=  stockAuditService.queryStockAuditCountByCondition(stockAuditParam);
//            stockAuditParam.setLimit((int)(count+1L));
//            IPage<StockAudit> page = stockAuditService.queryStockAuditByCondition(stockAuditParam);
//            //将入库订单去重
//            List<StockAudit>  page2 =  (List<StockAudit>)page.getRows();
//            if(page.getTotal()!=0) {
//                for (int i = page2.size() - 1; i >= 0; i--) {
//                    String type = page2.get(i).getOrderNo();
//                    if (!type.equals("出库")) {
//                        page2.remove(i);
//                        page.getRows().remove(i);
//                        if (page2.isEmpty()) {
//                            break;
//                        }
//                    }
//                }
//                return JsonUtils.jsonFromObject_AllToString(page);
//            }
//            else {
//                return JsonUtils.jsonFromObject_AllToString(page);
//            }
//        } catch (FdServiceException e) {
//            logger.error(e.getExceptionInfo(), e);
//            return e.getExceptionInfo();
//        } catch(Exception e){
//            logger.error(e.getMessage(), e);
//            return e.getMessage();
//        }
//    }


}