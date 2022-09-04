package com.example.mymusic.op;

import com.example.mymusic.common.ConstantVariable;
import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.*;
import com.example.mymusic.entity.param.StockOutParam;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IStockAuditService;
import com.example.mymusic.service.IStockOutService;
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
 * 类说明：出库表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Controller
@RequestMapping("/manager/stockOut")
public class ManagerStockOutAction  {

    private static Logger logger = LoggerFactory.getLogger(ManagerStockOutAction.class);

    @Resource
    private IStockOutService stockOutService;
    @Resource
    private IStockAuditService stockAuditService;
    @Resource
    private IStockService stockService;



    /**
     * 方法说明：进入添加出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toAddStockOut.do")
    public String toAddStockOut(Model model){
        return "console/stockOut/addStockOut";
    }


    /**
     * 方法说明：添加出库表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping(value="/addStockOut.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String addStockOut(StockOut stockOut){
        try {
            StockAudit stockAudit = new StockAudit();
            StockOutParam stockOutParam=new StockOutParam();

            Long count = stockOutService.queryStockOutCountByCondition(stockOutParam);
            StockParam stockParam= new StockParam();
            Long count2 =count+1L;
            stockAudit.setStockAuditNo("o"+count2.toString());  //生成订单号
            stockAudit.setStatus(stockOut.getStatus());//生成状态 审核中
            stockAudit.setOrderNo("出库");
            stockAudit.setStockAuditName(stockOut.getStockOutName());
            stockAudit.setOneType(stockOut.getOneType());
            stockAudit.setSecondType(stockOut.getSecondType());
            stockAudit.setBrand(stockOut.getBrand());
            stockAudit.setName(stockOut.getName());
            stockAudit.setSpecs(stockOut.getSpecs());
            stockAudit.setCount(stockOut.getCount());
            stockAudit.setApplyUser(stockOut.getApplyUser());
            stockAudit.setAuditUser(stockOut.getAuditUser());
            //不输入完全会报错
            if (StringUtils.isEmpty(stockOut.getStockOutName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "地址还没输入");
            }
            if (StringUtils.isEmpty(stockOut.getOneType())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请选择一级分类");
            }
            if (StringUtils.isEmpty(stockOut.getSecondType())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请选择二级级分类");
            }
            if (StringUtils.isEmpty(stockOut.getName())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入物品名称");
            }
            if (StringUtils.isEmpty(stockOut.getBrand())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "输入品牌");
            }
            if (StringUtils.isEmpty(stockOut.getSpecs())) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入规格");
            }
            if (stockOut.getCount()==null) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "数量不合法");
            }
            if (stockOut.getCount()==0) {
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "请输入大于0的数量");
            }
            //比对 看是否有这种物品
            stockParam.setStockName(stockOut.getStockOutName());
            stockParam.setOneType(stockOut.getOneType());
            stockParam.setSecondType(stockOut.getSecondType());
            stockParam.setBrand(stockOut.getBrand());
            stockParam.setName(stockOut.getName());
            stockParam.setSpecs(stockOut.getSpecs());
            IPage<Stock> stockIPage = stockService.queryStockByCondition(stockParam);
            if(stockIPage.getTotal()==0){
                throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "没有这样的物品，亲");
            }
            //如果有同样的物品 可以申请出库
            if(stockIPage.getTotal()!=0) {
                List<Stock> s = (List<Stock>) stockIPage.getRows();
                Long num = s.get(0).getCount();
                String stockNo = s.get(0).getStockNo();
                Long result = num - stockOut.getCount();
                if (result < 0) {
                    throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "库存不足或没有物品");
                }
                //如果数量足够 可申请出库
                else {
                    stockOut.setStockOutNo(stockAudit.getStockAuditNo());
                    stockOutService.createStockOut(stockOut);
                    stockAuditService.createStockAudit(stockAudit);
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


    /**
     * 方法说明：进入修改出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toUpdateStockOut.do")
    public String toUpdateStockOut(Model model, String stockOutNo){
        StockOut stockOut = stockOutService.queryStockOutByStockOutNo(stockOutNo);
        model.addAttribute("stockOut", stockOut);
        return "console/stockOut/updateStockOut";
    }


    /**
     * 方法说明：修改出库表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping(value="/updateStockOut.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String updateStockOut(StockOut stockOut){
        try {
            StockAudit stockAudit = new StockAudit();
            stockAudit.setRemark(stockOut.getRemark());
            stockAudit.setStockAuditNo(stockOut.getStockOutNo());  //获取订单号
            stockAudit.setOrderNo("出库");
            stockAudit.setStockAuditName(stockOut.getStockOutName());
            stockAudit.setOneType(stockOut.getOneType());
            stockAudit.setSecondType(stockOut.getSecondType());
            stockAudit.setBrand(stockOut.getBrand());
            stockAudit.setName(stockOut.getName());
            stockAudit.setSpecs(stockOut.getSpecs());
            stockAudit.setCount(stockOut.getCount());
            stockAudit.setApplyUser(stockOut.getApplyUser());
            stockAudit.setAuditUser(stockOut.getAuditUser());
            stockAuditService.updateStockAudit(stockAudit);
            stockOutService.updateStockOut(stockOut);

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
     * 方法说明：删除出库表
     * @author 劳威锟
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping(value="/delStockOut.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String delStockOut(String stockOutNo){
        try {
            stockOutService.delStockOut(stockOutNo);
            stockAuditService.delStockAudit(stockOutNo);
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
     * 方法说明：进入查询出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    @RequestMapping("/toQueryStockOut.do")
    public String toQueryStockOut(Model model){
        return "console/stockOut/queryStockOut";
    }


//    /**
//     * 方法说明：查询出库表
//     * @author 劳威锟
//     * @date 2021年09月28日 18时19分06秒
//     * @return
//     */
//    @RequestMapping(value="/queryStockOut.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String queryStockOut(StockOutParam stockOutParam){
//        try {
//            IPage<StockOut> page = stockOutService.queryStockOutByCondition(stockOutParam);
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