package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.Warehouse;
import com.example.mymusic.entity.param.WarehouseParam;

import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IWareHouseService {
    

    /**
     * 方法说明：添加库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer addWarehouse(Warehouse warehouse) throws FdServiceException;

    /**
     * 删除库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer delWarehouse(Long id) throws FdServiceException;


    /**
     * 修改库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒


     */
    public Integer updateWarehouse(Warehouse warehouse) throws FdServiceException;

    /**
     * 通过编号查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Warehouse queryWarehouseByWarehouseNo(Long id) throws FdServiceException;

    /**
     * 统计查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Long queryWarehouseCountByCondition(WarehouseParam warehouseParam) throws FdServiceException;

    /**
     * 分页查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public IPage<Warehouse> queryWarehouseByCondition(WarehouseParam warehouseParam) throws FdServiceException;

    public List<Warehouse> queryWarehouseByConditions(WarehouseParam warehouseParam) throws FdServiceException;

    public List<Warehouse> queryWarehouseByConditionApp(WarehouseParam warehouseParam) throws FdServiceException;

    public List<Warehouse>  queryWarehouseByConditionManager(WarehouseParam warehouseParam) throws FdServiceException;
}