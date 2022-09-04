package com.example.mymusic.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 类说明：出库表
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public class StockOut implements Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * id
     */
    private Long id;

    /**
     * 地点编号
     */
    private String stockOutNo;

    /**
     * 地点
     */
    private String stockOutName;

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
     * 申请时间
     */
    private Date applyTime;

    /**
     * 出库时间
     */
    private Date outTime;

    /**
     * 申请人员
     */
    private String applyUser;

    /**
     * 领用人员
     */
    private String outUser;

    /**
     * 审核人员
     */
    private String auditUser;

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
    public String getStockOutNo(){
        return stockOutNo;
    }

    /**
     * 设置地点编号
     */
    public void setStockOutNo(String stockOutNo){
        this.stockOutNo = stockOutNo;
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
     * 获取地点
     */
    public String getStockOutName(){
        return stockOutName;
    }

    /**
     * 设置地点
     */
    public void setStockOutName(String stockOutName){
        this.stockOutName = stockOutName;
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
     * 获取申请时间
     */
    public Date getApplyTime(){
        return applyTime;
    }

    /**
     * 设置申请时间
     */
    public void setApplyTime(Date applyTime){
        this.applyTime = applyTime;
    }

    /**
     * 获取出库时间
     */
    public Date getOutTime(){
        return outTime;
    }

    /**
     * 设置出库时间
     */
    public void setOutTime(Date outTime){
        this.outTime = outTime;
    }

    /**
     * 获取申请人员
     */
    public String getApplyUser(){
        return applyUser;
    }

    /**
     * 设置申请人员
     */
    public void setApplyUser(String applyUser){
        this.applyUser = applyUser;
    }

    /**
     * 获取领用人员
     */
    public String getOutUser(){
        return outUser;
    }

    /**
     * 设置领用人员
     */
    public void setOutUser(String outUser){
        this.outUser = outUser;
    }

    /**
     * 获取审核人员
     */
    public String getAuditUser(){
        return auditUser;
    }

    /**
     * 设置审核人员
     */
    public void setAuditUser(String auditUser){
        this.auditUser = auditUser;
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
        builder.append("StockOut [id=");
        builder.append(id);
        builder.append(", stockOutNo=");
        builder.append(stockOutNo);
        builder.append(", stockOutName=");
        builder.append(stockOutName);
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
        builder.append(", applyTime=");
        builder.append(applyTime);
        builder.append(", orderNo=");
        builder.append(orderNo);
        builder.append(", outTime=");
        builder.append(outTime);
        builder.append(", applyUser=");
        builder.append(applyUser);
        builder.append(", outUser=");
        builder.append(outUser);
        builder.append(", auditUser=");
        builder.append(auditUser);
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