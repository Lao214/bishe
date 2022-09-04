package com.example.mymusic.service;


import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.StockIn;
import com.example.mymusic.entity.param.StockInParam;

/**
 * 类说明：入库表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockInService {
    

    /**
     * 方法说明：添加入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public Integer addStockIn(StockIn stockIn) throws FdServiceException;

    /**
     * 删除入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public Integer delStockIn(String stockInNo) throws FdServiceException;

    /**
     * 修改入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer updateStockIn(StockIn stockIn) throws FdServiceException;

    /**
     * 通过编号查询入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public StockIn queryStockInByStockInNo(String stockInNo) throws FdServiceException;

    /**
     * 统计查询入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Long queryStockInCountByCondition(StockInParam stockInParam) throws FdServiceException;

    /**
     * 分页查询入库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public IPage<StockIn> queryStockInByCondition(StockInParam stockInParam) throws FdServiceException;

    /**
     *  方法说明：创建入库订单
     * @param stockIn
     * @return
     */
    public  Integer createStockIn(StockIn stockIn);

}