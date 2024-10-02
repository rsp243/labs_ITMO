package backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import backend.DTO.PersonCreatedDTO;
import backend.DTO.PointsCreatedDTO;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

// @Entity
// @Table(name = "points")
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// public class Points {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "x")
//     private float x;

//     @Column(name = "y")
//     private float y;

//     @Column(name = "r")
//     private float r;

//     @Column(name = "ishit")
//     private boolean isHit;

//     @Column(name = "currenttime")
//     private LocalDateTime currentTime;

//     @Column(name = "executiontime")
//     private int executionTime;

//     @Column(name = "userid")
//     private long userId;
//     @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)

//     public String getFormattedCurrentTime() {
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//         return currentTime.format(formatter);
//     }

//     public String getFormattedIsHit() {
//         return isHit ? "HIT" : "MISS";
//     }

//     public PointsCreatedDTO getCreatedPoint(Points point) {
//         return new PointsCreatedDTO(
//             point.getX(),
//             point.getY(),
//             point.getR(),
//             point.getFormattedIsHit(),
//             point.getFormattedCurrentTime(),
//             point.getExecutionTime()
//         );
//     }
// }

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть
                    // уникальным, Значение этого поля должно генерироваться автоматически

    @Column(name = "name", nullable = false)
    private String name; // Поле не может быть null, Строка не может быть пустой

    @Embedded
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; // Поле не может быть null

    @Column(name = "creation_date", nullable = false)
    private java.time.LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                              // автоматически

    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = true)
    private Color eyeColor; // Поле может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", nullable = false)
    private Color hairColor; // Поле не может быть null

    @Embedded
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location; // Поле не может быть null

    @Column(name = "height", nullable = false, scale = 0)
    @Min(0)
    private int height; // Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    private Country nationality; // Поле может быть null

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

    public PersonCreatedDTO getCreatedPerson(Person person) {
        return new PersonCreatedDTO(
            person.getName(),
            person.getCoordinates().getId(),
            person.getCreationDate(),
            person.getEyeColor(),
            person.getHairColor(),
            person.getLocation().getId(),
            person.getHeight(),
            person.getNationality()
        );
    }
}
