package com.example.mymusic.entity;
import com.example.mymusic.common.pages.PageParamEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 管理员(Admin)实体类
 * @author makejava
 * @since 2022-01-07 09:52:23
 */
public class Admin extends PageParamEntity implements Serializable {
    private static final long serialVersionUID = -17587106344559585L;

    /**
     * id
     */
    private Long id;
    /**
     * 管理员编号
     */
    private String adminNo;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 真实姓名
     */
    private String adminName;
    /**
     * 身份证号
     */
    private String cardId;
    /**
     * 登录账号
     */
    private String loginNo;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String mobileNo;
    /**
     * 是否手机验证登录
     */
    private Object isLoginMobile;
    /**
     * 管理员级别0普通职员1管理员2超级管理员
     */
    private Long adminLevel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 父级管理员编号
     */
    private String parentAdminNo;
    /**
     * 群组号
     */
    private String groupNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 商家编号
     */
    private String merchantNo;
    /**
     * 是否支持线下 0 不支持 1 支持
     */
    private String adminImg;
    /**
     * 线下地址
     */
    private String adminSon;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getIsLoginMobile() {
        return isLoginMobile;
    }

    public void setIsLoginMobile(Object isLoginMobile) {
        this.isLoginMobile = isLoginMobile;
    }

    public Long getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Long adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentAdminNo() {
        return parentAdminNo;
    }

    public void setParentAdminNo(String parentAdminNo) {
        this.parentAdminNo = parentAdminNo;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getAdminImg() {
        return adminImg;
    }

    public void setAdminImg(String adminImg) {
        this.adminImg = adminImg;
    }

    public String getAdminSon() {
        return adminSon;
    }

    public void setAdminSon(String adminSon) {
        this.adminSon = adminSon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
