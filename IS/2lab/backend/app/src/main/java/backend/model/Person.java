package backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import backend.DTO.PersonCreatedDTO;


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
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location; // Поле не может быть null

    @Column(name = "height", nullable = false, scale = 0)
    @Min(0)
    private int height; // Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", nullable = true)
    private Country nationality; // Поле может быть null

    @JsonProperty("is_editable_by_admin")
    private boolean isEditableByAdmin;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

    public static PersonCreatedDTO getCreatedPerson(Person person, int requestUserID) {
        return new PersonCreatedDTO(
            person.getId(),
            person.getName(),
            person.getCoordinates().getId(),
            person.getCreationDate(),
            person.getEyeColor(),
            person.getHairColor(),
            person.getLocation().getId(),
            person.getHeight(),
            person.getNationality(),
            person.isEditableByAdmin(),
            person.getUserId().getId() == requestUserID
        );
    }
}
