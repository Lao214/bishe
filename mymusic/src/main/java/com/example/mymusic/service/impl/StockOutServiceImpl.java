package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IStockOutDao;
import com.example.mymusic.entity.StockOut;
import com.example.mymusic.entity.param.StockOutParam;
import com.example.mymusic.service.IStockOutService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：出库表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("stockOutService")
public class StockOutServiceImpl implements IStockOutService {

    private static Logger logger = LoggerFactory.getLogger(StockOut.class);
    @Resource
    private IStockOutDao stockOutDao;


    @Override
    public Integer addStockOut(StockOut stockOut) throws FdServiceException {
       // stockOut.setStockOutNo(Random2Utils.buildSn(""));
        try {
            return stockOutDao.addStockOut(stockOut);
        } catch (Exception e) {
            logger.error("添加出库表["+stockOut+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加出库表失败",e);
        }
    }


    @Override
    public Integer delStockOut(String stockOutNo) throws FdServiceException{
        try {
            return stockOutDao.delStockOut(stockOutNo);
        } catch (Exception e) {
            logger.error("删除出库表["+stockOutNo+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除出库表失败",e);
        }
    }


    @Override
    public Integer updateStockOut(StockOut stockOut) throws FdServiceException{
        try {
            return stockOutDao.updateStockOut(stockOut);
        } catch (Exception e) {
            logger.error("修改出库表["+stockOut+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改出库表失败",e);
        }
    }


    @Override
    public StockOut queryStockOutByStockOutNo(String stockOutNo) throws FdServiceException{
        try {
            return stockOutDao.queryStockOutByStockOutNo(stockOutNo);
        } catch (Exception e) {
            logger.error("根据编号查询出库表["+stockOutNo+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询出库表失败",e);
        }
    }


    @Override
    public Long queryStockOutCountByCondition(StockOutParam stockOutParam) throws FdServiceException{
        try {
            return stockOutDao.queryStockOutCountByCondition(stockOutParam);
        } catch (Exception e) {
            logger.error("统计查询出库表["+stockOutParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询出库表失败",e);
        }
    }


    @Override
    public IPage<StockOut> queryStockOutByCondition(StockOutParam stockOutParam) throws FdServiceException{
        List<StockOut> list = null;
        try {
            Long count = stockOutDao.queryStockOutCountByCondition(stockOutParam);
            if(count!=null && count>0){
                list = stockOutDao.queryStockOutByCondition(stockOutParam);
            }
            return new Page<StockOut>(list,count,stockOutParam);
        } catch (Exception e) {
            logger.error("根据条件查询出库表["+stockOutParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询出库表失败",e);
        }
    }

    @Override
    public Integer createStockOut(StockOut stockOut) {
        try {
            return stockOutDao.addStockOut(stockOut);
        } catch (Exception e) {
            logger.error("添加出库表["+stockOut+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加出库表失败",e);
        }
    }


}