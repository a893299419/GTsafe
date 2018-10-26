package com.example.apple.gtsafe.contactBean;

import java.util.List;

/**
 * Created by siokagami on 15/4/26.
 */
public class contactdataBean
{
    private String id;
    private String number;
    private String pid;
    private List<contactcateBean> cate;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<contactcateBean> getCate() {
        return cate;
    }

    public void setCate(List<contactcateBean> cate) {
        this.cate = cate;
    }
}
