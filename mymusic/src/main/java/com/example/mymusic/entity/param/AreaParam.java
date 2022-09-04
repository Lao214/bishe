package com.example.mymusic.entity.param;

/**
 * @author @劳威锟
 * @title: Area
 * @projectName mymusic
 * @description: TODO
 * @date 2022/4/1618:19
 */
import com.example.mymusic.common.pages.PageParamEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * 类说明：地区
 *
 * @author 聂枫
 * @date 2018年09月18日 11时58分55秒
 */
public class AreaParam extends PageParamEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 区域编号
     */
    private String areaNo;

    /**
     * 父区域编号
     */
    private String parentAreaNo;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域类型1省份2市3区县
     */
    private Integer areaType;

    /**
     * 拼音首字母
     */
    private String areaFirstspell;

    /**
     * 拼音
     */
    private String areaPinyin;

    /**
     * 拼音Index，目前没用到
     */
    private String areaSpellindex;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 代理商编号
     */
    private String memberNo;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
    /**
     * 邮费
     */
    private Long postage;

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
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取区域编号
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * 设置区域编号
     */
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    /**
     * 获取父区域编号
     */
    public String getParentAreaNo() {
        return parentAreaNo;
    }

    /**
     * 设置父区域编号
     */
    public void setParentAreaNo(String parentAreaNo) {
        this.parentAreaNo = parentAreaNo;
    }

    /**
     * 获取区域名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置区域名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取区域类型1省份2市3区县
     */
    public Integer getAreaType() {
        return areaType;
    }

    /**
     * 设置区域类型1省份2市3区县
     */
    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    /**
     * 获取拼音首字母
     */
    public String getAreaFirstspell() {
        return areaFirstspell;
    }

    /**
     * 设置拼音首字母
     */
    public void setAreaFirstspell(String areaFirstspell) {
        this.areaFirstspell = areaFirstspell;
    }

    /**
     * 获取拼音
     */
    public String getAreaPinyin() {
        return areaPinyin;
    }

    /**
     * 设置拼音
     */
    public void setAreaPinyin(String areaPinyin) {
        this.areaPinyin = areaPinyin;
    }

    /**
     * 获取拼音Index，目前没用到
     */
    public String getAreaSpellindex() {
        return areaSpellindex;
    }

    /**
     * 设置拼音Index，目前没用到
     */
    public void setAreaSpellindex(String areaSpellindex) {
        this.areaSpellindex = areaSpellindex;
    }

    /**
     * 获取区域编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区域编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取代理商编号
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 设置代理商编号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 获取状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPostage() { return postage; }

    public void setPostage(Long postage) { this.postage = postage; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Area [id=");
        builder.append(id);
        builder.append(", areaNo=");
        builder.append(areaNo);
        builder.append(", parentAreaNo=");
        builder.append(parentAreaNo);
        builder.append(", areaName=");
        builder.append(areaName);
        builder.append(", areaType=");
        builder.append(areaType);
        builder.append(", areaFirstspell=");
        builder.append(areaFirstspell);
        builder.append(", areaPinyin=");
        builder.append(areaPinyin);
        builder.append(", areaSpellindex=");
        builder.append(areaSpellindex);
        builder.append(", areaCode=");
        builder.append(areaCode);
        builder.append(", memberNo=");
        builder.append(memberNo);
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