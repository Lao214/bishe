package com.example.mymusic.dao;

import com.example.mymusic.entity.StockIn;
import com.example.mymusic.entity.param.StockInParam;


import java.util.List;


/**
 * 类说明：入库表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockInDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer addStockIn(StockIn stockIn);



    /**
     * 备注： Integer
     * 删除入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return  
     */
    public Integer delStockIn(String stockInNo);

    /**
     * 备注： Integer
     * 修改入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return  
     */
    public Integer updateStockIn(StockIn stockIn);

    /**
     * 备注： Integer
     * 通过编号查询入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public StockIn queryStockInByStockInNo(String stockInNo);

    /**
     * 备注： Integer
     * 统计查询入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryStockInCountByCondition(StockInParam stockInParam);

    /**
     * 备注： Integer
     * 分页查询入库表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockIn>
     */
    public List<StockIn> queryStockInByCondition(StockInParam stockInParam);

}