package com.example.mymusic.dao;

import com.example.mymusic.entity.StockOut;
import com.example.mymusic.entity.param.StockOutParam;

import java.util.List;


/**
 * 类说明：出库表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockOutDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer addStockOut(StockOut stockOut);

    /**
     * 备注： Integer
     * 删除出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer delStockOut(String stockOutNo);

    /**
     * 备注： Integer
     * 修改出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer updateStockOut(StockOut stockOut);

    /**
     * 备注： Integer
     * 通过编号查询出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public StockOut queryStockOutByStockOutNo(String stockOutNo);

    /**
     * 备注： Integer
     * 统计查询出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryStockOutCountByCondition(StockOutParam stockOutParam);

    /**
     * 备注： Integer
     * 分页查询出库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockOut>
     */
    public List<StockOut> queryStockOutByCondition(StockOutParam stockOutParam);

}