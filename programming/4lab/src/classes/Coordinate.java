package src.classes;

public class Coordinate {
    private float topPosition;
    private float rightPosition;
    private float heightPosition;
    
    public Coordinate(float aTopPosition, float aRightPosition, float aHeightPosition) {
        this.topPosition = aTopPosition;
        this.rightPosition = aRightPosition;
        this.heightPosition = aHeightPosition;
    }

    public float getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(float topPosition) {
        this.topPosition = topPosition;
    }

    public float getRightPosition() {
        return rightPosition;
    }

    public void setRightPosition(float rightPosition) {
        this.rightPosition = rightPosition;
    }   

    public float getHeightPosition() {
        return heightPosition;
    }

    public void setHeightPosition(float heightPosition) {
        this.heightPosition = heightPosition;
    }
    
    @Override
    public String toString() {
        return "Coordinate [topPosition=" + topPosition + ", rightPosition=" + rightPosition + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(topPosition);
        result = prime * result + Float.floatToIntBits(rightPosition);
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
        Coordinate other = (Coordinate) obj;
        if (Float.floatToIntBits(topPosition) != Float.floatToIntBits(other.topPosition))
            return false;
        if (Float.floatToIntBits(rightPosition) != Float.floatToIntBits(other.rightPosition))
            return false;
        return true;
    }
}
