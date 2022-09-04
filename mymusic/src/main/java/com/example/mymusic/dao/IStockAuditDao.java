package com.example.mymusic.dao;

import com.example.mymusic.entity.StockAudit;
import com.example.mymusic.entity.param.StockAuditParam;


import java.util.List;


/**
 * 类说明：出库审核表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockAuditDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param StockAudit stockAudit
     * @return  
     */
    public Integer addStockAudit(StockAudit stockAudit);

    /**
     * 备注： Integer
     * 删除出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param String stockAuditNo
     * @return  
     */
    public Integer delStockAudit(String stockAuditNo);

    /**
     * 备注： Integer
     * 修改出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param StockAudit stockAudit
     * @return  
     */
    public Integer updateStockAudit(StockAudit stockAudit);

    /**
     * 备注： Integer
     * 通过编号查询出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param String stockAuditNo
     * @return Long
     */
    public StockAudit queryStockAuditByStockAuditNo(String stockAuditNo);

    public StockAudit queryStockAuditByStockAuditId(Long id);

    /**
     * 备注： Integer
     * 统计查询出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param StockAuditParam stockAuditParam
     * @return Long
     */
    public Long queryStockAuditCountByCondition(StockAuditParam stockAuditParam);

    /**
     * 备注： Integer
     * 分页查询出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param StockAuditParam stockAuditParam
     * @return List<StockAudit>
     */
    public List<StockAudit> queryStockAuditByCondition(StockAuditParam stockAuditParam);

    /**
     * 备注： Integer
     * 分页查询出库审核表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @param StockAuditParam stockAuditParam
     * @return List<StockAudit>
     */
    public List<StockAudit> queryStockAuditByTime(StockAuditParam stockAuditParam);

}