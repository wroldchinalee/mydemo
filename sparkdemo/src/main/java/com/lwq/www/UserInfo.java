package com.lwq.www;

import java.math.BigDecimal;

/**
 * @author: LWQ
 * @create: 2019/9/3
 * @description: UserInfo
 **/
public class UserInfo {
    private int id;
    private String name;
    private String info;
    private BigDecimal cash;

    public UserInfo(int id, String name, String info, BigDecimal cash) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.cash = cash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
}
