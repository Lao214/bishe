package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IStockClassDao;
import com.example.mymusic.entity.StockClass;
import com.example.mymusic.entity.param.StockClassParam;
import com.example.mymusic.service.IStockClassService;
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
@Service("stockClassService")
public class StockClassServiceImpl implements IStockClassService {

    private static Logger logger = LoggerFactory.getLogger(StockClass.class);
    @Resource
    private IStockClassDao stockClassDao;


    @Override
    public Integer addStockClass(StockClass stockClass) throws FdServiceException {
        try {
            return stockClassDao.addStockClass(stockClass);
        } catch (Exception e) {
            logger.error("添加库存表["+stockClass+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加库存表失败",e);
        }
    }


    @Override
    public Integer delStockClass(Long id) throws FdServiceException{
        try {
            return stockClassDao.delStockClass(id);
        } catch (Exception e) {
            logger.error("删除库存表["+id+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除库存表失败",e);
        }
    }



    @Override
    public Integer updateStockClass(StockClass stockClass) throws FdServiceException{
        try {
            return stockClassDao.updateStockClass(stockClass);
        } catch (Exception e) {
            logger.error("修改库存表["+stockClass+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改库存表失败",e);
        }
    }


    @Override
    public StockClass queryStockClassByStockClassNo(Long id) throws FdServiceException {
        try {
            return stockClassDao.queryStockClassByStockClassNo(id);
        } catch (Exception e) {
            logger.error("根据编号查询库存表["+id+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询库存表失败",e);
        }
    }


    @Override
    public Long queryStockClassCountByCondition(StockClassParam stockClassParam) throws FdServiceException{
        try {
            return stockClassDao.queryStockClassCountByCondition(stockClassParam);
        } catch (Exception e) {
            logger.error("统计查询库存表["+stockClassParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询库存表失败",e);
        }
    }


    @Override
    public IPage<StockClass> queryStockClassByCondition(StockClassParam stockClassParam) throws FdServiceException{
        List<StockClass> list = null;
        try {
            Long count = stockClassDao.queryStockClassCountByCondition(stockClassParam);
            if(count!=null && count>0){
                list = stockClassDao.queryStockClassByCondition(stockClassParam);
            }
            return new Page<StockClass>(list,count,stockClassParam);
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+stockClassParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<StockClass> queryStockClassByConditions(StockClassParam stockClassParam) throws FdServiceException{
        List<StockClass> list = null;
        try {
            Long count = stockClassDao.queryStockClassCountByCondition(stockClassParam);
            if(count!=null && count>0){
                list = stockClassDao.queryStockClassByCondition(stockClassParam);
            }
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+stockClassParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }






}