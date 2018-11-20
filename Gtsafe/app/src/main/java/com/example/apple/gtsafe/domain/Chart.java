package com.example.apple.gtsafe.domain;

import java.util.List;

public class Chart {
    public List<Chart.TargetPoint> targetPoint;
    public class TargetPoint{
        public String id;
        public int point;
        public int day;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
    public int chart_day;

    public List<TargetPoint> getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(List<TargetPoint> targetPoint) {
        this.targetPoint = targetPoint;
    }

    public int getChart_day() {
        return chart_day;
    }

    public void setChart_day(int chart_day) {
        this.chart_day = chart_day;
    }
}
