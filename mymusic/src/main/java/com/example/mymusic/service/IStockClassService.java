package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.StockClass;
import com.example.mymusic.entity.param.StockClassParam;

import java.util.ArrayList;
import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockClassService {
    

    /**
     * 方法说明：添加库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer addStockClass(StockClass stockClass) throws FdServiceException;

    /**
     * 删除库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer delStockClass(Long id) throws FdServiceException;


    /**
     * 修改库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒


     */
    public Integer updateStockClass(StockClass stockClass) throws FdServiceException;

    /**
     * 通过编号查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public StockClass queryStockClassByStockClassNo(Long id) throws FdServiceException;

    /**
     * 统计查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Long queryStockClassCountByCondition(StockClassParam stockClassParam) throws FdServiceException;

    /**
     * 分页查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public IPage<StockClass> queryStockClassByCondition(StockClassParam stockClassParam) throws FdServiceException;

    public List<StockClass> queryStockClassByConditions(StockClassParam stockClassParam) throws FdServiceException;


}