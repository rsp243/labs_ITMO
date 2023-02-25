package server.data.classes;

import java.time.LocalDate;

public class Human {
    private Integer age; //Значение поля должно быть больше 0
    private Float height; //Значение поля должно быть больше 0
    private LocalDate birthday;
    
    public Human(Integer age, Float height, LocalDate birthday) {
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }
    
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Human [age=" + age + ", height=" + height + ", birthday=" + birthday + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
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
        Human other = (Human) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (height == null) {
            if (other.height != null)
                return false;
        } else if (!height.equals(other.height))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        return true;
    }
}
