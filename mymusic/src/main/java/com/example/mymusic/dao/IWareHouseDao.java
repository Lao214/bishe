package com.example.mymusic.dao;



import com.example.mymusic.entity.Warehouse;
import com.example.mymusic.entity.param.WarehouseParam;

import java.util.List;


/**
 * 类说明：库存表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IWareHouseDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return  
     */
    public Integer addWarehouse(Warehouse harehouse);

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer delWarehouse(Long id);



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer updateWarehouse(Warehouse harehouse);

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Warehouse queryWarehouseByWarehouseNo(Long id);

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryWarehouseCountByCondition(WarehouseParam harehouseParam);

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<Warehouse>
     */
    public List<Warehouse> queryWarehouseByCondition(WarehouseParam harehouseParam);

    public List<Warehouse> queryWarehouseByConditionApp(WarehouseParam harehouseParam);

    public List<Warehouse> queryWarehouseByConditionSortByTime(WarehouseParam harehouseParam);

}