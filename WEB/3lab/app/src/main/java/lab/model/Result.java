package lab.model;

import java.io.Serializable;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;

@ManagedBean(value = "result")
@RequestScoped
public class Result implements Serializable {
    private float xVal;
    private float yVal ,rVal;
    private boolean isHit;
    private String currentTime, executionTime;

    public Result(float aXVal, float anYVal, float aRVal, boolean anIsHit, String aCurrentTime, String anExecutionTime) {
        xVal = aXVal;
        yVal = anYVal;
        rVal = aRVal;
        isHit = anIsHit;
        currentTime = aCurrentTime;
        executionTime = anExecutionTime;
    }

    public Result() {
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

    @Override
    public String toString() {
        return "Result [xVal=" + xVal + ", yVal=" + yVal + ", rVal=" + rVal + ", isHit=" + isHit + ", currentTime="
                + currentTime + ", executionTime=" + executionTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(xVal);
        result = prime * result + Float.floatToIntBits(yVal);
        result = prime * result + Float.floatToIntBits(rVal);
        result = prime * result + (isHit ? 1231 : 1237);
        result = prime * result + ((currentTime == null) ? 0 : currentTime.hashCode());
        result = prime * result + ((executionTime == null) ? 0 : executionTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Result other = (Result) obj;
        if (Float.floatToIntBits(xVal) != Float.floatToIntBits(other.xVal))
            return false;
        if (Float.floatToIntBits(yVal) != Float.floatToIntBits(other.yVal))
            return false;
        if (Float.floatToIntBits(rVal) != Float.floatToIntBits(other.rVal))
            return false;
        if (isHit != other.isHit)
            return false;
        if (currentTime == null) {
            if (other.currentTime != null)
                return false;
        } else if (!currentTime.equals(other.currentTime))
            return false;
        if (executionTime == null) {
            if (other.executionTime != null)
                return false;
        } else if (!executionTime.equals(other.executionTime))
            return false;
        return true;
    }
}
