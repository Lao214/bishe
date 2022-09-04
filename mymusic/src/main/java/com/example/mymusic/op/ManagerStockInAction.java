package com.example.mymusic.op;


import com.example.mymusic.common.ConstantVariable;
import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.*;
import com.example.mymusic.entity.param.StockInParam;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IStockAuditService;
import com.example.mymusic.service.IStockInService;
import com.example.mymusic.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：入库表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Controller
@RequestMapping("/manager/stockIn")
public class ManagerStockInAction  {

    private static Logger logger = LoggerFactory.getLogger(ManagerStockInAction.class);

    @Resource
    private IStockInService stockInService;
    @Resource
    private IStockAuditService stockAuditService;
    @Resource
    private IStockService stockService;


    /**
     * 方法说明：进入添加入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toAddStockIn.do")
    public String toAddStockIn(Model model){
        return "console/stockIn/addStockIn";
    }


    /**
     * @Author 劳威锟
     * @Description //方法说明： 添加入库表
     * @Date 9:59 2021/10/16
     * @Param
     * @return
     **/
    @RequestMapping(value="/addStockIn.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String addStockIn(StockIn stockIn){
        try {
            StockAudit stockAudit = new StockAudit();
            StockInParam stockInParam=new StockInParam();

            Long count = stockInService.queryStockInCountByCondition(stockInParam);
            StockParam stockParam= new StockParam();
            Long count2 =count+1L;
            stockAudit.setStockAuditNo("i"+count2.toString());  //生成订单号
            stockAudit.setStatus("通过");//生成状态 审核中
            stockAudit.setOrderNo("入库");
            stockAudit.setStockAuditName(stockIn.getStockInName());
            stockAudit.setOneType(stockIn.getOneType());
            stockAudit.setSecondType(stockIn.getSecondType());
            stockAudit.setBrand(stockIn.getBrand());
            stockAudit.setName(stockIn.getName());
            stockAudit.setSpecs(stockIn.getSpecs());
            stockAudit.setCount(stockIn.getCount());
            //不输入完全会报错
            if (StringUtils.isEmpty(stockIn.getStockInName())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "地址还没输入");
            }
            if (StringUtils.isEmpty(stockIn.getOneType())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请选择一级分类");
            }
            if (StringUtils.isEmpty(stockIn.getSecondType())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请选择二级级分类");
            }
            if (StringUtils.isEmpty(stockIn.getName())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "请输入物品名称");
            }
            if (StringUtils.isEmpty(stockIn.getBrand())) {
                throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "输入品牌");
            }
            if (StringUtils.isEmpty(stockIn.getSpecs())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入规格");
            }
//            //用正则表达式判断 如果输入不合法 返回错误文案
//            String  stockIntoString=stockIn.getCount().toString();
//            if (stockIntoString.matches("^[0-9_]+$")) {
//                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入数字");
//            }
            if (stockIn.getCount()==null) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "数量不合法");
            }
            if (stockIn.getCount()==0) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入大于0的数量");
            }


            stockIn.setStockInNo(stockAudit.getStockAuditNo());
            stockInService.createStockIn(stockIn);
            stockAuditService.createStockAudit(stockAudit);

            // 比对物品 如果有就进行加法
            stockParam.setStockName(stockIn.getStockInName());
            stockParam.setOneType(stockIn.getOneType());
            stockParam.setSecondType(stockIn.getSecondType());
            stockParam.setBrand(stockIn.getBrand());
            stockParam.setName(stockIn.getName());
            stockParam.setSpecs(stockIn.getSpecs());
            IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
            if(stockIPage.getTotal()!=0) {
                List<Stock> s = (List<Stock>) stockIPage.getRows();
                Long num = s.get(0).getCount();
                String stockNo = s.get(0).getStockNo();
                Long result = num + stockIn.getCount();
                Stock stock = stockService.queryStockByStockNo(stockNo);
                stock.setCount(result);
                stockService.updateStock(stock);
            }
            else {
                //如果没有 插入一条物品
                //计数统计
                Stock stock = new Stock();
                StockParam stockParam1 = new StockParam();
                Long countj = stockService.queryStockCountByCondition(stockParam1);
                Long count3 = countj + 1L;
                //放入数据
                stock.setStockNo("0"+count3.toString());
                stock.setStockName(stockIn.getStockInName());
                stock.setOneType(stockIn.getOneType());
                stock.setSecondType(stockIn.getSecondType());
                stock.setBrand(stockIn.getBrand());
                stock.setName(stockIn.getName());
                stock.setSpecs(stockIn.getSpecs());
                stock.setCount(stockIn.getCount());
                stock.setStatus("common");
                stockService.addStock(stock);
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


    /**
     * 方法说明：进入修改入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toUpdateStockIn.do")
    public String toUpdateStockIn(Model model, String stockInNo){
        StockIn stockIn = stockInService.queryStockInByStockInNo(stockInNo);
        model.addAttribute("stockIn", stockIn);
        return "console/stockIn/updateStockIn";
    }


    /**
     * @Author 劳威锟
     * @Description //方法说明： 修改入库表
     * @Date 9:59 2021/10/16
     * @Param
     * @return
     **/
    @RequestMapping(value="/updateStockIn.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String updateStockIn(StockIn stockIn){
        try {
            StockAudit stockAudit = new StockAudit();
            stockAudit.setStockAuditNo(stockIn.getStockInNo());  //获取订单号
            stockAudit.setOrderNo("入库");
            stockAudit.setStockAuditName(stockIn.getStockInName());
            stockAudit.setOneType(stockIn.getOneType());
            stockAudit.setSecondType(stockIn.getSecondType());
            stockAudit.setBrand(stockIn.getBrand());
            stockAudit.setName(stockIn.getName());
            stockAudit.setSpecs(stockIn.getSpecs());
            stockAudit.setCount(stockIn.getCount());
            //同时更新审核表中的入库信息
            stockAuditService.updateStockAudit(stockAudit);
            stockInService.updateStockIn(stockIn);
            return ConstantVariable.SUCCESS;
        } catch (FdServiceException e) {
            logger.error(e.getExceptionInfo(), e);
            return e.getExceptionInfo();
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            return e.getMessage();
        }
    }


    /**
     * @Author 劳威锟
     * @Description //方法说明： 删除入库表
     * @Date 10:00 2021/10/16
     * @Param
     * @return
     **/
    @RequestMapping(value="/delStockIn.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String delStockIn(String stockInNo){
        try {
            stockInService.delStockIn(stockInNo);
            stockAuditService.delStockAudit(stockInNo);
            return ConstantVariable.SUCCESS;
        } catch (FdServiceException e) {
            logger.error(e.getExceptionInfo(), e);
            return e.getExceptionInfo();
        } catch(Exception e){
            logger.error(e.getMessage(), e);
            return e.getMessage();
        }
    }


    /**
     * 方法说明：进入查询入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toQueryStockIn.do")
    public String toQueryStockIn(Model model){
        return "console/stockIn/queryStockIn";
    }


//    /**
//     * 方法说明：查询入库表
//     * @author 聂枫
//     * @date 2021年09月28日 18时19分06秒
//     * @return
//     */
//    @RequestMapping(value="/queryStockIn.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String queryStockIn(StockInParam stockInParam){
//        try {
//            IPage<StockIn> page = stockInService.queryStockInByCondition(stockInParam);
//            return JsonUtils.jsonFromObject_AllToString(page);
//        } catch (FdServiceException e) {
//            logger.error(e.getExceptionInfo(), e);
//            return e.getExceptionInfo();
//        } catch(Exception e){
//            logger.error(e.getMessage(), e);
//            return e.getMessage();
//        }
//    }


}