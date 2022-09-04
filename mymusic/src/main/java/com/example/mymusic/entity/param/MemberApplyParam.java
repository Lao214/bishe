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
public class MemberApplyParam extends PageParamEntity implements Serializable {
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
    private Long memberId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private String status;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
