package backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import backend.DTO.CoordinatesCreatedDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coordinates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x", nullable = false)
    private Long x; // Максимальное значение поля: 288, Поле не может быть null

    @Column(name = "y", nullable = false)
    private Double y; // Поле не может быть null

    @JsonProperty("is_editable_by_admin")
    private boolean isEditableByAdmin;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "coordinates")
    private List<Person> people;
    
    public static CoordinatesCreatedDTO getCreatedCoordinates(Coordinates coordinates, int requestUserID) {
        return new CoordinatesCreatedDTO(
                coordinates.getId(),
                coordinates.getX(),
                coordinates.getY(),
                coordinates.isEditableByAdmin(),
                coordinates.getUserId().getId() == requestUserID);
    }
}
