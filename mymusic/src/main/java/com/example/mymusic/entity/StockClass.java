package com.example.mymusic.entity;

import java.io.Serializable;

/**
 * @author @劳威锟
 * @title: stockClass
 * @projectName mymusic
 * @description: TODO
 * @date 2022/4/1617:07
 */
public class StockClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 分类名
     */
    private String className;

    /**
     * 地点
     */
    private String classLevel;

    /**
     * 订单号
     */
    private String classParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getClassParent() {
        return classParent;
    }

    public void setClassParent(String classParent) {
        this.classParent = classParent;
    }
}
