package com.example.apple.gtsafe.domain;

import java.util.List;

public class Loglist {
    public int total;
    public List<Loglist.ServerModel> rows;
    public class ServerModel{
        public int summary;
        public String id;
        public int score2;
        public int score3;
        public int score;
        public String checkTime;
        public int day;
        public String templateName;
        public String addTime;
        public int templateId;
        public String status;
        public String statusName;

        public int getSummary() {
            return summary;
        }

        public void setSummary(int summary) {
            this.summary = summary;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getScore2() {
            return score2;
        }

        public void setScore2(int score2) {
            this.score2 = score2;
        }

        public int getScore3() {
            return score3;
        }

        public void setScore3(int score3) {
            this.score3 = score3;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getTemplateName() {
            return templateName;
        }

        public void setTemplateName(String templateName) {
            this.templateName = templateName;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getTemplateId() {
            return templateId;
        }

        public void setTemplateId(int templateId) {
            this.templateId = templateId;
        }

        public String getStatus() {
            return statusName;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ServerModel> getRows() {
        return rows;
    }

    public void setRows(List<ServerModel> rows) {
        this.rows = rows;
    }
}
