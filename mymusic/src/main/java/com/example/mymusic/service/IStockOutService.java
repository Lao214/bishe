package com.example.mymusic.service;


import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.StockOut;
import com.example.mymusic.entity.param.StockOutParam;

/**
 * 类说明：出库表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockOutService {
    

    /**
     * 方法说明：添加出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer addStockOut(StockOut stockOut) throws FdServiceException;

    /**
     * 删除出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer delStockOut(String stockOutNo) throws FdServiceException;

    /**
     * 修改出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒


     */
    public Integer updateStockOut(StockOut stockOut) throws FdServiceException;

    /**
     * 通过编号查询出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public StockOut queryStockOutByStockOutNo(String stockOutNo) throws FdServiceException;

    /**
     * 统计查询出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Long queryStockOutCountByCondition(StockOutParam stockOutParam) throws FdServiceException;

    /**
     * 分页查询出库表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public IPage<StockOut> queryStockOutByCondition(StockOutParam stockOutParam) throws FdServiceException;

    /**
     *  方法说明：创建出库订单
     * @return
     */
    public  Integer createStockOut(StockOut stockOut);

}