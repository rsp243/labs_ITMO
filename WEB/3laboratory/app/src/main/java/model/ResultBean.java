package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; 

@Named
@Entity
@Table(name = "results")
@Data
public class ResultBean implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "x")
    private float x;
    
    @Column(name = "y")
    private float y;
    
    @Column(name = "r")
    private float r;

    @Column(name = "ishit")
    private boolean isHit;

    @Column(name = "currenttime")
    private LocalDateTime currentTime;

    @Column(name = "executiontime")
    private int executionTime;

    public String getFormattedCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter);
    }
    
    public String getFormattedIsHit() {
        return isHit ? "HIT" : "MISS";
    }
}
