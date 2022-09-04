package com.example.mymusic.service;


import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.StockAudit;
import com.example.mymusic.entity.param.StockAuditParam;

import java.util.List;

/**
 * 类说明：出库审核表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockAuditService {
    

    /**
     * 方法说明：添加出库审核表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public Integer addStockAudit(StockAudit stockAudit) throws FdServiceException;

    /**
     * 删除出库审核表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer delStockAudit(String stockAuditNo) throws FdServiceException;

    /**
     * 修改出库审核表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public Integer updateStockAudit(StockAudit stockAudit) throws FdServiceException;

    /**
     * 通过编号查询出库审核表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public StockAudit queryStockAuditByStockAuditNo(String stockAuditNo) throws FdServiceException;
    public StockAudit queryStockAuditByStockAuditId(Long id) throws FdServiceException;
    /**
     * 统计查询出库审核表
     * @author 聂枫
     */
    public Long queryStockAuditCountByCondition(StockAuditParam stockAuditParam) throws FdServiceException;

    /**
     * 分页查询出库审核表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public IPage<StockAudit> queryStockAuditByCondition(StockAuditParam stockAuditParam) throws FdServiceException;

    public List<StockAudit> queryStockAuditByConditions(StockAuditParam stockAuditParam) throws FdServiceException;

    /**
     *  方法说明：创建入库订单
     * @return
     */
    public  Integer createStockAudit(StockAudit stockAudit);


}