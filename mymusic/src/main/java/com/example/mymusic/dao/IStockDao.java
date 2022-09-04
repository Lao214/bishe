package com.example.mymusic.dao;



import com.example.mymusic.entity.EcahrtsClass;
import com.example.mymusic.entity.EcahrtsPie;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.param.StockParam;

import java.util.ArrayList;
import java.util.List;


/**
 * 类说明：库存表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IStockDao {
    

    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return  
     */
    public Integer addStock(Stock stock);

    public Integer insertOrUpdateBatch(List<Stock> list);

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer delStock(String stockNo);


    /**
     * 备注： Integer
     * 删除库存表通过级别 dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer recoverStock(String stockNo);

    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return  
     */
    public Integer updateStock(Stock stock);

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Stock queryStockByStockNo(String stockNo);

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryStockCountByCondition(StockParam stockParam);

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<Stock>
     */
    public List<Stock> queryStockByCondition(StockParam stockParam);

    public List<Stock> queryStockByConditionOneType(StockParam stockParam);

    public List<Stock> queryStockByConditionSecondType(StockParam stockParam);

    public List<Stock> queryStockByConditionName(StockParam stockParam);

    public List<Stock> queryStockByConditionBrand(StockParam stockParam);

    public List<Stock> queryStockByConditionSpecs(StockParam stockParam);


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
    public List<Stock> queryStockByAddress (StockParam stockParam);

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
    public List<Stock> queryStockBySecondType(String secondType);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 根据二级菜单查询统计订单
     * @Date 11:12 2021/9/26
     * @Param
     * @return
     **/
    public Long queryStockCountBySecondType(String secondType);




    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Stock queryStockByOrderNo(String orderNo);

    public List<EcahrtsClass> queryClassParent();

    public List<EcahrtsPie> queryClassType();
}