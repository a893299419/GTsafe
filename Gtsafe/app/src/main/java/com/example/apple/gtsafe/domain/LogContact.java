package com.example.apple.gtsafe.domain;

import java.util.List;

public class LogContact {
    public int id;
    public String name;
    public int type;
    public List<LogTplattr> attributeList;

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

    public List<LogTplattr> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<LogTplattr> attributeList) {
        this.attributeList = attributeList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
