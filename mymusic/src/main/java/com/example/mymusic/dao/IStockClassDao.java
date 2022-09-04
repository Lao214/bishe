package com.example.mymusic.dao;



import com.example.mymusic.entity.StockClass;
import com.example.mymusic.entity.param.StockClassParam;

import java.util.ArrayList;
import java.util.List;


/**
 * 类说明：库存表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockClassDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return  
     */
    public Integer addStockClass(StockClass stockClass);

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer delStockClass(Long id);



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer updateStockClass(StockClass stockClass);

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public StockClass queryStockClassByStockClassNo(Long id);

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryStockClassCountByCondition(StockClassParam stockClassParam);

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<StockClass> queryStockClassByCondition(StockClassParam stockClassParam);



}