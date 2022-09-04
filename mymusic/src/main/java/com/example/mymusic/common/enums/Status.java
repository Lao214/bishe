package com.example.mymusic.common.enums;

/**
 * 类说明：status
 *
 * @Author 劳威锟
 * @date 2022/1/711:23
 **/
public enum Status {
    noread("未读"),
    read("已读"),
    common("状态正常"),
    delete("删除");

    /**
     * Instantiates a new member status.
     *
     * @param chName the ch name
     */
    Status(String chName){
        this.chName = chName;
    }

    /** 中文名称. */
    private String chName;

    /**
     * Gets the 中文名称.
     *
     * @return the 中文名称
     */
    public String getChName() {
        return chName;
    }

    /**
     * Sets the 中文名称.
     *
     * @param chName the new 中文名称
     */
    public void setChName(String chName) {
        this.chName = chName;
    }

}
