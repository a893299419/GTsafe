package com.example.apple.gtsafe.domain;

import java.util.List;

public class LogTplcate {
    public  int id;
    public String name;
    public List<LogContact> contactList;

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

    public List<LogContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<LogContact> contactList) {
        this.contactList = contactList;
    }
}
