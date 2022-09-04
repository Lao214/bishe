package com.example.mymusic.entity.param;

import com.example.mymusic.common.pages.PageParamEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员(Admin)实体类
 *
 * @author makejava
 * @since 2022-01-07 09:52:23
 */
public class MemberParam extends PageParamEntity implements Serializable {
    private static final long serialVersionUID = -17587106344559585L;

    /**
     * id
     */
    private Long id;
    /**
     * 管理员编号
     */
    private String memberNo;
    /**
     * 昵称
     */
    private String memberName;
    /**
     * 会员级别
     */
    private String memberLevel;
    /**
     * 会员类型
     */
    private String memberType;
    /**
     * 电话号码
     */
    private String telephone;
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
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private String status;

    private String headPath;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
