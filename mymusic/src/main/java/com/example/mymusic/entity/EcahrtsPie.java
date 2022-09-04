package com.example.mymusic.entity;

import java.io.Serializable;

/**
 * @author @劳威锟
 * @title: stockClass
 * @projectName mymusic
 * @description: TODO
 * @date 2022/4/1617:07
 */
public class EcahrtsPie implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 库存量
     */
    private String value;

    private String name;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
