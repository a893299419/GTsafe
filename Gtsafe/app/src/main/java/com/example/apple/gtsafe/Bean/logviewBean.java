package com.example.apple.gtsafe.Bean;

/**
 * Created by siokagami on 15/4/24.
 */
//格式为
/**{"total":38,
        "rows":
        [
        {
        "template":"安全工地01",
        "summary":70,
        "templateId":1,
        "id":39,
        "status":2,
        "statusName":"已审核通过",
        "score2":70,
        "score3":10,
        "score":-10,
        "checkTime":1419945903,
        "day":38,
        "addTime":"2014-12-21 21:24"
        }
        ]
}*/
public class logviewBean
{
    private String status;
    private String addTime;
    private  String id;
    private int score2;
    private int score3;
    private int score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
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
}
