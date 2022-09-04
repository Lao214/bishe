package com.example.mymusic.entity;

import com.example.mymusic.common.pages.PageParamEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员(Admin)实体类
 *
 * @author makejava
 * @since 2022-01-07 09:52:23
 */
public class Message extends PageParamEntity implements Serializable {
    private static final long serialVersionUID = -17587106344559585L;

    /**
     * id
     */
    private Long id;
    /**
     * 管理员编号
     */
    private String content;
    /**
     * 昵称
     */
    private String details;
    /**
     * 会员级别
     */
    private Long memberId;

    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;


    private String status;

    private String createTimeStr;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
