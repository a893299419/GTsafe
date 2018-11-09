package com.example.apple.gtsafe.domain;

import java.util.List;

public class LogTpl {
    private String id;
    private String name;
    private List<LogTplcate> children;
    private String number;
    private int pid;
    private int counts;
    private int orderId;
    private int isShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LogTplcate> getChildren() {
        return children;
    }

    public void setChildren(List<LogTplcate> children) {
        this.children = children;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }
}
