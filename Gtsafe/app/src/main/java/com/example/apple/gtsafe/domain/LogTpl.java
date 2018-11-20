package com.example.apple.gtsafe.domain;

import java.util.List;

public class LogTpl {
    public int id;
    public String name;
    public List<LogTplcate> subContactCateList;

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

    public List<LogTplcate> getSubContactCateList() {
        return subContactCateList;
    }

    public void setSubContactCateList(List<LogTplcate> subContactCateList) {
        this.subContactCateList = subContactCateList;
    }
}
