package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IStockInDao;
import com.example.mymusic.entity.StockIn;
import com.example.mymusic.entity.param.StockInParam;
import com.example.mymusic.service.IStockInService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：入库表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("stockInService")
public class StockInServiceImpl implements IStockInService {

    private static Logger logger = LoggerFactory.getLogger(StockIn.class);
    @Resource
    private IStockInDao stockInDao;


    @Override
    public Integer addStockIn(StockIn stockIn) throws FdServiceException {
      //  stockIn.setStockInNo(Random2Utils.buildSn(""));
        try {
            return stockInDao.addStockIn(stockIn);
        } catch (Exception e) {
            logger.error("添加入库表["+stockIn+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加入库表失败",e);
        }
    }


    @Override
    public Integer delStockIn(String stockInNo) throws FdServiceException{
        try {
            return stockInDao.delStockIn(stockInNo);
        } catch (Exception e) {
            logger.error("删除入库表["+stockInNo+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除入库表失败",e);
        }
    }


    @Override
    public Integer updateStockIn(StockIn stockIn) throws FdServiceException{
        try {
            return stockInDao.updateStockIn(stockIn);
        } catch (Exception e) {
            logger.error("修改入库表["+stockIn+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改入库表失败",e);
        }
    }


    @Override
    public StockIn queryStockInByStockInNo(String stockInNo) throws FdServiceException{
        try {
            return stockInDao.queryStockInByStockInNo(stockInNo);
        } catch (Exception e) {
            logger.error("根据编号查询入库表["+stockInNo+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询入库表失败",e);
        }
    }


    @Override
    public Long queryStockInCountByCondition(StockInParam stockInParam) throws FdServiceException{
        try {
            return stockInDao.queryStockInCountByCondition(stockInParam);
        } catch (Exception e) {
            logger.error("统计查询入库表["+stockInParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询入库表失败",e);
        }
    }


    @Override
    public IPage<StockIn> queryStockInByCondition(StockInParam stockInParam) throws FdServiceException{
        List<StockIn> list = null;
        try {
            Long count = stockInDao.queryStockInCountByCondition(stockInParam);
            if(count!=null && count>0){
                list = stockInDao.queryStockInByCondition(stockInParam);
            }
            return new Page<StockIn>(list,count,stockInParam);
        } catch (Exception e) {
            logger.error("根据条件查询入库表["+stockInParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询入库表失败",e);
        }
    }

    @Override
    public Integer createStockIn(StockIn stockIn) {
        try {
            return stockInDao.addStockIn(stockIn);
        } catch (Exception e) {
            logger.error("添加入库表["+stockIn+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加入库表失败",e);
        }
    }


}