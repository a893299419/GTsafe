package com.example.apple.gtsafe.contactBean;

import java.util.List;

/**
 * Created by siokagami on 15/4/26.
 */
public class contactcateBean
{
    private  String id;
    private  String name;
    private List<contactconBean> children;
    private String type;

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

    public List<contactconBean> getChildren() {
        return children;
    }

    public void setChildren(List<contactconBean> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
