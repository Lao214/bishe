package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IWareHouseDao;
import com.example.mymusic.entity.Warehouse;
import com.example.mymusic.entity.param.WarehouseParam;
import com.example.mymusic.service.IWareHouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：库存表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("warehouseService")
public class WareHouseServiceImpl implements IWareHouseService {

    private static Logger logger = LoggerFactory.getLogger(Warehouse.class);
    @Resource
    private IWareHouseDao warehouseDao;


    @Override
    public Integer addWarehouse(Warehouse warehouse) throws FdServiceException {
        try {
            return warehouseDao.addWarehouse(warehouse);
        } catch (Exception e) {
            logger.error("添加库存表["+warehouse+"]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION,"添加库存表失败",e);
        }
    }


    @Override
    public Integer delWarehouse(Long id) throws FdServiceException{
        try {
            return warehouseDao.delWarehouse(id);
        } catch (Exception e) {
            logger.error("删除库存表["+id+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"删除库存表失败",e);
        }
    }



    @Override
    public Integer updateWarehouse(Warehouse warehouse) throws FdServiceException{
        try {
            return warehouseDao.updateWarehouse(warehouse);
        } catch (Exception e) {
            logger.error("修改库存表["+warehouse+"]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION,"修改库存表失败",e);
        }
    }


    @Override
    public Warehouse queryWarehouseByWarehouseNo(Long id) throws FdServiceException {
        try {
            return warehouseDao.queryWarehouseByWarehouseNo(id);
        } catch (Exception e) {
            logger.error("根据编号查询库存表["+id+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据编号查询库存表失败",e);
        }
    }


    @Override
    public Long queryWarehouseCountByCondition(WarehouseParam warehouseParam) throws FdServiceException{
        try {
            return warehouseDao.queryWarehouseCountByCondition(warehouseParam);
        } catch (Exception e) {
            logger.error("统计查询库存表["+warehouseParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"统计查询库存表失败",e);
        }
    }


    @Override
    public IPage<Warehouse> queryWarehouseByCondition(WarehouseParam warehouseParam) throws FdServiceException{
        List<Warehouse> list = null;
        try {
            Long count = warehouseDao.queryWarehouseCountByCondition(warehouseParam);
            if(count!=null && count>0){
                list = warehouseDao.queryWarehouseByCondition(warehouseParam);
            }
            return new Page<Warehouse>(list,count,warehouseParam);
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+warehouseParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<Warehouse> queryWarehouseByConditionApp(WarehouseParam warehouseParam) throws FdServiceException{
        List<Warehouse> list = null;
        try {
                list = warehouseDao.queryWarehouseByConditionApp(warehouseParam);
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+warehouseParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<Warehouse> queryWarehouseByConditionManager(WarehouseParam warehouseParam) throws FdServiceException{
        List<Warehouse> list = null;
        try {
            list = warehouseDao.queryWarehouseByCondition(warehouseParam);
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+warehouseParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }

    @Override
    public List<Warehouse> queryWarehouseByConditions(WarehouseParam warehouseParam) throws FdServiceException{
        List<Warehouse> list = null;
        try {
            Long count = warehouseDao.queryWarehouseCountByCondition(warehouseParam);
            if(count!=null && count>0){
                if(warehouseParam.getSort()==0){
                    list = warehouseDao.queryWarehouseByCondition(warehouseParam);
                } else if(warehouseParam.getSort()==1){
                    list = warehouseDao.queryWarehouseByConditionSortByTime(warehouseParam);
                } else if(warehouseParam.getSort()==2){
                    list = warehouseDao.queryWarehouseByCondition(warehouseParam);
                }else if(warehouseParam.getSort()==3){
                    list = warehouseDao.queryWarehouseByCondition(warehouseParam);
                }
            }
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询库存表["+warehouseParam+"]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION,"根据条件查询库存表失败",e);
        }
    }






}