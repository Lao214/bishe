package com.example.mymusic.entity;

import java.io.Serializable;

/**
 * @author @劳威锟
 * @title: stockClass
 * @projectName mymusic
 * @description: TODO
 * @date 2022/4/1617:07
 */
public class EcahrtsClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年月日 格式；yyyy-mm-dd
     */
    private String yearMonthDay;

    /**
     * 出库数
     */
    private String className;

    /**
     * 入库数
     */
    private String classLevel;

    /**
     * 库存量
     */
    private String classParent;


    /**
     * 库存量
     */
    private String days;

    private String gptimes;

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getGptimes() {
        return gptimes;
    }

    public void setGptimes(String gptimes) {
        this.gptimes = gptimes;
    }
}
