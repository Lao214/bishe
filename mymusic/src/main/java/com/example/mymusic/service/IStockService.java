package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.EcahrtsClass;
import com.example.mymusic.entity.EcahrtsPie;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.param.StockParam;

import java.util.ArrayList;
import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockService {
    

    /**
     * 方法说明：添加库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer addStock(Stock stock) throws FdServiceException;

    public Integer insertOrUpdateBatch(List<Stock> list) throws FdServiceException;

    /**
     * 删除库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Integer delStock(String stockNo) throws FdServiceException;


    public  Integer recoverStock(String stockNo) throws  FdServiceException;

    /**
     * 修改库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒


     */
    public Integer updateStock(Stock stock) throws FdServiceException;

    /**
     * 通过编号查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Stock queryStockByStockNo(String stockNo) throws FdServiceException;

    /**
     * 统计查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     */
    public Long queryStockCountByCondition(StockParam stockParam) throws FdServiceException;

    /**
     * 分页查询库存表
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     */
    public IPage<Stock> queryStockByCondition(StockParam stockParam) throws FdServiceException;

    public List<Stock> queryStockByConditions(StockParam stockParam) throws FdServiceException;

    public List<Stock> queryStockByConditionApp(StockParam stockParam) throws FdServiceException;
    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据地区查找物品一级分类统计
     * @Date 9:39 2021/9/28
     * @Param
     * @return
     **/
    public Long queryStockCountByAddress (StockParam stockParam);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据地区查找物品一级分类
     * @Date 9:39 2021/9/28
     * @Param
     * @return
     **/
    public  IPage<Stock> queryStockByAddress (StockParam stockParam);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据一级分类查询二级分类统计
     * @Date 10:06 2021/9/28
     * @Param
     * @return
     **/
    public Long querySecondCountByOneType (String oneType);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据一级分类查询二级分类
     * @Date 10:06 2021/9/28
     * @Param
     * @return
     **/
    public ArrayList querySecondByOneType (String oneType);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据二级菜单查询订单
     * @Date 11:16 2021/9/26
     * @Param
     * @return
     **/
    public ArrayList<List<Stock>> queryStockBySecondType(String secondType)  throws FdServiceException;


    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据二级菜单查询订单统计
     * @Date 11:12 2021/9/27
     * @Param
     * @return
     **/
    public Long queryStockCountBySecondType(String secondType)throws FdServiceException;

    public List<EcahrtsClass> queryClassParent()throws FdServiceException;

    public List<EcahrtsPie> queryClassType();
}