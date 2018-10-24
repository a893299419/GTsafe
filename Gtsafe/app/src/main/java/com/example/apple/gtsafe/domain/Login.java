package com.example.apple.gtsafe.domain;

import java.util.List;

public class Login {
    public int status;
    public List<ServerModel> data;
    public String info;

    public class ServerModel{

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ServerModel> getData() {
        return data;
    }

    public void setData(List<ServerModel> data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
