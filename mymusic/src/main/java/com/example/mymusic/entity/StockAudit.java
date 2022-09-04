package com.example.mymusic.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 类说明：出库审核表
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
public class StockAudit implements Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * id
     */
    private Long id;

    /**
     * 地点编号
     */
    private String stockAuditNo;

    /**
     * 地点
     */
    private String stockAuditName;

    /**
     * 订单号
     */
    private String orderNo;

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
     * 申请人员
     */
    private String applyUser;

    /**
     * 审核人员
     */
    private String auditUser;

    /**
     * 拒绝原因
     */
    private String failMsg;

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

    private String createTimeStr;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
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
    public String getStockAuditNo(){
        return stockAuditNo;
    }

    /**
     * 获取订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 设置地点编号
     */
    public void setStockAuditNo(String stockAuditNo){
        this.stockAuditNo = stockAuditNo;
    }

    /**
     * 获取地点
     */
    public String getStockAuditName(){
        return stockAuditName;
    }

    /**
     * 设置地点
     */
    public void setStockAuditName(String stockAuditName){
        this.stockAuditName = stockAuditName;
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
     * 获取拒绝原因
     */
    public String getFailMsg(){
        return failMsg;
    }

    /**
     * 设置拒绝原因
     */
    public void setFailMsg(String failMsg){
        this.failMsg = failMsg;
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
        builder.append("StockAudit [id=");
        builder.append(id);
        builder.append(", stockAuditNo=");
        builder.append(stockAuditNo);
        builder.append(", stockAuditName=");
        builder.append(stockAuditName);
        builder.append(", orderNo=");
        builder.append(orderNo);
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
        builder.append(", applyUser=");
        builder.append(applyUser);
        builder.append(", auditUser=");
        builder.append(auditUser);
        builder.append(", failMsg=");
        builder.append(failMsg);
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