package com.example.apple.gtsafe.contactBean;

import java.util.List;

/**
 * Created by siokagami on 15/4/26.
 */
public class contactconBean
{
    private String id;
    private String name;
    private String type;
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<contactattrBean> attr;

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

    public List<contactattrBean> getAttr() {
        return attr;
    }

    public void setAttr(List<contactattrBean> attr) {
        this.attr = attr;
    }
}
