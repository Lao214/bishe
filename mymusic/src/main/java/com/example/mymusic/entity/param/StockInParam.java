package com.example.mymusic.entity.param;



import com.example.mymusic.common.pages.PageParamEntity;

import java.util.Date;


/**
 * 入库表参数
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public class StockInParam extends PageParamEntity {

    private static final long serialVersionUID = 1L;

    

    /**
     * id
     */
    private Long id;

    /**
     * 地点编号
     */
    private String stockInNo;

    /**
     * 地点
     */
    private String stockInName;

    /**
     * 订单号
     */
    private  String orderNo;

    /**
     * 一级分类
     */
    private String oneType;

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
     * 入库时间
     */
    private Date inTime;

    /**
     * 保质时间
     */
    private Date overdueTime;

    /**
     * 入库人员
     */
    private String inUser;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
    public String getStockInNo(){
        return stockInNo;
    }

    /**
     * 设置地点编号
     */
    public void setStockInNo(String stockInNo){
        this.stockInNo = stockInNo;
    }

    /**
     * 获取地点
     */
    public String getStockInName(){
        return stockInName;
    }

    /**
     * 设置地点
     */
    public void setStockInName(String stockInName){
        this.stockInName = stockInName;
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
     * 获取入库时间
     */
    public Date getInTime(){
        return inTime;
    }

    /**
     * 设置入库时间
     */
    public void setInTime(Date inTime){
        this.inTime = inTime;
    }

    /**
     * 获取保质时间
     */
    public Date getOverdueTime(){
        return overdueTime;
    }

    /**
     * 设置保质时间
     */
    public void setOverdueTime(Date overdueTime){
        this.overdueTime = overdueTime;
    }

    /**
     * 获取入库人员
     */
    public String getInUser(){
        return inUser;
    }

    /**
     * 设置入库人员
     */
    public void setInUser(String inUser){
        this.inUser = inUser;
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
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StockInParam [id=");
        builder.append(id);
        builder.append(", orderNo=");
        builder.append(orderNo);
        builder.append(", stockInNo=");
        builder.append(stockInNo);
        builder.append(", stockInName=");
        builder.append(stockInName);
        builder.append(", oneType=");
        builder.append(oneType);
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
        builder.append(", inTime=");
        builder.append(inTime);
        builder.append(", overdueTime=");
        builder.append(overdueTime);
        builder.append(", inUser=");
        builder.append(inUser);
        builder.append(", status=");
        builder.append(status);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append("]");
        return builder.toString();
    }

}