package model;

import java.io.Serializable;

public class UserData implements Serializable {
    
    private float xVal;
    private float yVal ,rVal;
    private boolean isHit;
    private String currentTime, executionTime;

    public UserData(float aXVal, float anYVal, float aRVal, boolean anIsHit, String aCurrentTime, String anExecutionTime) {
        xVal = aXVal;
        yVal = anYVal;
        rVal = aRVal;
        isHit = anIsHit;
        currentTime = aCurrentTime;
        executionTime = anExecutionTime;
    }

    public UserData() {
        this(0, 0, 0, false, "", "");
    }

    public float getxVal() {
        return xVal;
    }

    public void setxVal(float xVal) {
        this.xVal = xVal;
    }
    
    public float getyVal() {
        return yVal;
    }
    
    public void setyVal(float yVal) {
        this.yVal = yVal;
    }
    
    public float getrVal() {
        return rVal;
    }
    
    public void setrVal(float rVal) {
        this.rVal = rVal;
    }
    
    public boolean isHit() {
        return isHit;
    }
    
    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public String getCurrentTime() {
        return currentTime;
    }
    
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    
    public String getExecutionTime() {
        return executionTime;
    }
    
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

}
