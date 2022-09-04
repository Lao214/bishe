package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IStockDao;
import com.example.mymusic.entity.EcahrtsClass;
import com.example.mymusic.entity.EcahrtsPie;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IStockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 类说明：库存表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("stockService")
public class StockServiceImpl implements IStockService {

    private static Logger logger = LoggerFactory.getLogger(Stock.class);
    @Resource
    private IStockDao stockDao;


    @Override
    public Integer addStock(Stock stock) throws FdServiceException {
        stock.setStockNo(Random2Utils.buildSn("S"));
        stock.setStatus("正常");
        try {
            return stockDao.addStock(stock);
        } catch (Exception e) {
            logger.error("添加库存表["+stock+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加库存表失败",e);
        }
    }

    @Override
    public Integer insertOrUpdateBatch(List<Stock> list) throws FdServiceException {

        try {
            return stockDao.insertOrUpdateBatch(list);
        } catch (Exception e) {
            logger.error("添加库存表["+list+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加库存表失败",e);
        }
    }


    @Override
    public Integer delStock(String stockNo) throws FdServiceException{
        try {
            return stockDao.delStock(stockNo);
        } catch (Exception e) {
            logger.error("删除库存表["+stockNo+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除库存表失败",e);
        }
    }

    @Override
    public Integer recoverStock(String stockNo) throws FdServiceException {
        try {
            return stockDao.recoverStock(stockNo);
        } catch (Exception e) {
            logger.error("删除库存表["+stockNo+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除库存表失败",e);
        }
    }


    @Override
    public Integer updateStock(Stock stock) throws FdServiceException{
        try {
            return stockDao.updateStock(stock);
        } catch (Exception e) {
            logger.error("修改库存表["+stock+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改库存表失败",e);
        }
    }


    @Override
    public Stock queryStockByStockNo(String stockNo) throws FdServiceException {
        try {
            return stockDao.queryStockByStockNo(stockNo);
        } catch (Exception e) {
            logger.error("根据编号查询库存表["+stockNo+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询库存表失败",e);
        }
    }


    @Override
    public Long queryStockCountByCondition(StockParam stockParam) throws FdServiceException{
        try {
            return stockDao.queryStockCountByCondition(stockParam);
        } catch (Exception e) {
            logger.error("统计查询库存表["+stockParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询库存表失败",e);
        }
    }


    @Override
    public IPage<Stock> queryStockByCondition(StockParam stockParam) throws FdServiceException{
        List<Stock> list = null;
        try {
            Long count = stockDao.queryStockCountByCondition(stockParam);
            if(count!=null && count>0){
                list = stockDao.queryStockByCondition(stockParam);
            }
            return new Page<Stock>(list,count,stockParam);
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+stockParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<Stock> queryStockByConditions(StockParam stockParam) throws FdServiceException{
        List<Stock> list = null;
        try {
                list = stockDao.queryStockByCondition(stockParam);
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+stockParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<Stock> queryStockByConditionApp(StockParam stockParam) throws FdServiceException{
        List<Stock> list = null;
        try {
            if(stockParam.getType().equals("queryOneType")){
                list = stockDao.queryStockByConditionOneType(stockParam);
            }
            else if(stockParam.getType().equals("querySecondType")){
                list = stockDao.queryStockByConditionSecondType(stockParam);
            }
            else if(stockParam.getType().equals("queryName")){
                list = stockDao.queryStockByConditionName(stockParam);
            }
            else if(stockParam.getType().equals("queryBrand")){
                list = stockDao.queryStockByConditionBrand(stockParam);
            }
            else if(stockParam.getType().equals("querySpecs")){
                list = stockDao.queryStockByConditionSpecs(stockParam);
            }
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+stockParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public Long queryStockCountByAddress(StockParam stockParam) {
        try {
            return stockDao.queryStockCountByAddress(stockParam);
        } catch (Exception e) {
            logger.error("统计查询工作任务["+ stockParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询兼职任务失败",e);
        }
    }

    @Override
    public IPage<Stock> queryStockByAddress(StockParam stockParam) {
        List<Stock> list = null;
        try {
            Long count = stockDao.queryStockCountByAddress(stockParam);
            if(count!=null && count>0){
                list = stockDao.queryStockByAddress(stockParam);
            }
            return new Page<Stock>(list,count,stockParam);
        } catch (Exception e) {
            logger.error("根据编号查询工作任务["+list+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据查询订单失败",e);
        }
    }

    @Override
    public Long querySecondCountByOneType(String oneType) {
        try {
            return stockDao.querySecondCountByOneType(oneType);
        } catch (Exception e) {
            logger.error("统计查询工作任务["+ oneType+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询兼职任务失败",e);
        }
    }

    @Override
    public ArrayList querySecondByOneType(String oneType) {
        List<Stock> list=null;
        try {
            Long count = stockDao.querySecondCountByOneType(oneType);
            ArrayList array =new ArrayList();
            //去重
            if(count!=null && count>0){
                list = stockDao.querySecondByOneType(oneType);
                array.add(list);
            }
            return array;
        } catch (Exception e) {
            logger.error("根据编号查询工作任务["+oneType+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据查询订单失败",e);
        }
    }

    @Override
    public ArrayList<List<Stock>> queryStockBySecondType(String secondType) throws FdServiceException {
        List<Stock> list = null;
        try {
            Long count = stockDao.queryStockCountBySecondType(secondType);
            ArrayList<List<Stock>>  array =new ArrayList<List<Stock>>();
            if(count!=null && count>0){
                list = stockDao.queryStockBySecondType(secondType);
                array.add(list);
            }
            return array;
        } catch (Exception e) {
            logger.error("根据编号查询工作任务["+secondType+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据查询订单失败",e);
        }
    }

    @Override
    public Long queryStockCountBySecondType(String secondType) throws FdServiceException {
        try {
            return stockDao.queryStockCountBySecondType(secondType);
        } catch (Exception e) {
            logger.error("统计查询工作任务["+secondType+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询兼职任务失败",e);
        }
    }

    @Override
    public List<EcahrtsClass> queryClassParent() throws FdServiceException {
        return this.stockDao.queryClassParent();
    }

    @Override
    public List<EcahrtsPie> queryClassType() throws FdServiceException {
        return this.stockDao.queryClassType();
    }

}