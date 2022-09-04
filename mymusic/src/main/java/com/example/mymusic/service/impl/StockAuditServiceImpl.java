package com.example.mymusic.service.impl;


import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IStockAuditDao;
import com.example.mymusic.entity.StockAudit;
import com.example.mymusic.entity.param.StockAuditParam;
import com.example.mymusic.service.IStockAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：出库审核表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("stockAuditService")
public class StockAuditServiceImpl implements IStockAuditService {

    private static Logger logger = LoggerFactory.getLogger(StockAudit.class);
    @Resource
    private IStockAuditDao stockAuditDao;


    @Override
    public Integer addStockAudit(StockAudit stockAudit) throws FdServiceException {
        stockAudit.setStockAuditNo(Random2Utils.buildSn("SA"));
        stockAudit.setStatus("待审核");
        try {
            return stockAuditDao.addStockAudit(stockAudit);
        } catch (Exception e) {
            logger.error("添加出库审核表["+stockAudit+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加出库审核表失败",e);
        }
    }


    @Override
    public Integer delStockAudit(String stockAuditNo) throws FdServiceException{
        try {
            return stockAuditDao.delStockAudit(stockAuditNo);
        } catch (Exception e) {
            logger.error("删除出库审核表["+stockAuditNo+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除出库审核表失败",e);
        }
    }


    @Override
    public Integer updateStockAudit(StockAudit stockAudit) throws FdServiceException{
        try {
            return stockAuditDao.updateStockAudit(stockAudit);
        } catch (Exception e) {
            logger.error("修改出库审核表["+stockAudit+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改出库审核表失败",e);
        }
    }


    @Override
    public StockAudit queryStockAuditByStockAuditNo(String stockAuditNo) throws FdServiceException{
        try {
            return stockAuditDao.queryStockAuditByStockAuditNo(stockAuditNo);
        } catch (Exception e) {
            logger.error("根据编号查询出库审核表["+stockAuditNo+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询出库审核表失败",e);
        }
    }

    @Override
    public StockAudit queryStockAuditByStockAuditId(Long id) throws FdServiceException{
        try {
            return stockAuditDao.queryStockAuditByStockAuditId(id);
        } catch (Exception e) {
            logger.error("根据编号查询出库审核表["+id+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询出库审核表失败",e);
        }
    }


    @Override
    public Long queryStockAuditCountByCondition(StockAuditParam stockAuditParam) throws FdServiceException{
        try {
            return stockAuditDao.queryStockAuditCountByCondition(stockAuditParam);
        } catch (Exception e) {
            logger.error("统计查询出库审核表["+stockAuditParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询出库审核表失败",e);
        }
    }


    @Override
    public IPage<StockAudit> queryStockAuditByCondition(StockAuditParam stockAuditParam) throws FdServiceException{
        List<StockAudit> list = null;
        try {
            Long count = stockAuditDao.queryStockAuditCountByCondition(stockAuditParam);
            if(count!=null && count>0){
                list = stockAuditDao.queryStockAuditByCondition(stockAuditParam);
            }
            return new Page<StockAudit>(list,count,stockAuditParam);
        } catch (Exception e) {
            logger.error("根据条件查询出库审核表["+stockAuditParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询出库审核表失败",e);
        }
    }


    @Override
    public List<StockAudit> queryStockAuditByConditions(StockAuditParam stockAuditParam) throws FdServiceException{
        List<StockAudit> list = null;
        try {
            Long count = stockAuditDao.queryStockAuditCountByCondition(stockAuditParam);
            if(count!=null && count>0){
                list = stockAuditDao.queryStockAuditByCondition(stockAuditParam);
            }
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询出库审核表["+stockAuditParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询出库审核表失败",e);
        }
    }

    @Override
    public Integer createStockAudit(StockAudit stockAudit) {
        try {
            return stockAuditDao.addStockAudit(stockAudit);
        } catch (Exception e) {
            logger.error("添加出库审核表["+stockAudit+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加出库审核表失败",e);
        }
    }


}