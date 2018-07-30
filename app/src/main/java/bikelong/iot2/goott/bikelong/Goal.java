package bikelong.iot2.goott.bikelong;

import java.io.Serializable;

public class Goal implements Serializable {

    private int goalNo;
    private String goalName;
    private int goalAmount;
    private String goalInfo;
    private String date;

    public int getGoalNo() {
        return goalNo;
    }

    public void setGoalNo(int goalNo) {
        this.goalNo = goalNo;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getGoalInfo() {
        return goalInfo;
    }

    public void setGoalInfo(String goalInfo) {
        this.goalInfo = goalInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
