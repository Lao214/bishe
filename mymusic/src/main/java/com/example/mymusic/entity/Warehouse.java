package com.example.mymusic.entity;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * 类说明：库存表
 * @author 劳威锟
 * @date 2021年09月28日 18时19分06秒
 */
@ToString
public class Warehouse implements Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * id
     */
    private Long id;

    /**
     * 仓库名
     */
    private String wareHouseName;


    /**
     * 地址
     */
    private String address;

    /**
     * 管理者
     */
    private String HouseManager;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 品牌
     */
    private String latitude;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 货物存储量
     */
    private Long goodsCount;

    /**
     * 货物存储量
     */
    private Long goodsTypeCount;

    /**
     * 货物存储量
     */
    private String cover;

    private String imageUrl;

    private String filename;


    private String province;

    private String city;

    private String area;

    private String provinceNo;

    private String cityNo;

    private String areaNoA;

    private Long value;

    private String label;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(String provinceNo) {
        this.provinceNo = provinceNo;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getAreaNoA() {
        return areaNoA;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAreaNoA(String areaNoA) {
        this.areaNoA = areaNoA;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseManager() {
        return HouseManager;
    }

    public void setHouseManager(String houseManager) {
        HouseManager = houseManager;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Long getGoodsTypeCount() {
        return goodsTypeCount;
    }

    public void setGoodsTypeCount(Long goodsTypeCount) {
        this.goodsTypeCount = goodsTypeCount;
    }
}