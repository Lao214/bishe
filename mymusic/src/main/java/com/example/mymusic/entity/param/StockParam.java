package com.example.mymusic.entity.param;



import com.example.mymusic.common.pages.PageParamEntity;

import java.util.Date;


/**
 * 库存表参数
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public class StockParam extends PageParamEntity {

    private static final long serialVersionUID = 1L;

    

    /**
     * id
     */
    private Long id;

    /**
     * 地点编号
     */
    private String stockNo;

    /**
     * 地点
     */
    private String stockName;

    /**
     * 一级分类
     */
    private String oneType;

    /**
     * 订单号
     */
    private  String orderNo;

    /**
     * 二级分类
     */
    private String secondType;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 规格
     */
    private String specs;

    /**
     * 数量
     */
    private Long count;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 保质期
     */
    private Date overDueTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取id
     */
    public Long getId(){
        return id;
    }

    /**
     * 设置id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * 获取地点编号
     */
    public String getStockNo(){
        return stockNo;
    }

    /**
     * 设置地点编号
     */
    public void setStockNo(String stockNo){
        this.stockNo = stockNo;
    }

    /**
     * 获取地点
     */
    public String getStockName(){
        return stockName;
    }

    /**
     * 设置地点
     */
    public void setStockName(String stockName){
        this.stockName = stockName;
    }

    /**
     * 获取订单号
     * @return
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取一级分类
     */
    public String getOneType(){
        return oneType;
    }

    /**
     * 设置一级分类
     */
    public void setOneType(String oneType){
        this.oneType = oneType;
    }

    /**
     * 获取二级分类
     */
    public String getSecondType(){
        return secondType;
    }

    /**
     * 设置二级分类
     */
    public void setSecondType(String secondType){
        this.secondType = secondType;
    }

    /**
     * 获取物品名称
     */
    public String getName(){
        return name;
    }

    /**
     * 设置物品名称
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 获取品牌
     */
    public String getBrand(){
        return brand;
    }

    /**
     * 设置品牌
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * 获取规格
     */
    public String getSpecs(){
        return specs;
    }

    /**
     * 设置规格
     */
    public void setSpecs(String specs){
        this.specs = specs;
    }

    /**
     * 获取数量
     */
    public Long getCount(){
        return count;
    }

    /**
     * 设置数量
     */
    public void setCount(Long count){
        this.count = count;
    }

    /**
     * 获取状态
     */
    public String getStatus(){
        return status;
    }

    /**
     * 设置状态
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * 获取备注
     */
    public String getRemark(){
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * 获取保质期
     * @return
     */
    public Date getOverDueTime() {
        return overDueTime;
    }

    public void setOverDueTime(Date overDueTime) {
        this.overDueTime = overDueTime;
    }



    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StockParam [id=");
        builder.append(id);
        builder.append(", stockNo=");
        builder.append(stockNo);
        builder.append(", stockName=");
        builder.append(stockName);
        builder.append(", oneType=");
        builder.append(oneType);
        builder.append(", orderNo=");
        builder.append(orderNo);
        builder.append(", secondType=");
        builder.append(secondType);
        builder.append(", name=");
        builder.append(name);
        builder.append(", brand=");
        builder.append(brand);
        builder.append(", specs=");
        builder.append(specs);
        builder.append(", count=");
        builder.append(count);
        builder.append(", status=");
        builder.append(status);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", overDueTime=");
        builder.append(overDueTime);
        builder.append("]");
        return builder.toString();
    }

}