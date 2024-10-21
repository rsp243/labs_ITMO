package backend.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import backend.DTO.LocationCreatedDTO;
import backend.DTO.PersonCreatedDTO;
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
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x", nullable = false)
    private Integer x; // Поле не может быть null

    @Column(name = "y", nullable = false)
    private Integer y; // Поле не может быть null

    @Column(name = "z")
    private long z;

    @JsonProperty("is_editable_by_admin")
    private boolean isEditableByAdmin;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "location")
    private List<Person> people;

    public static LocationCreatedDTO getCreatedLocation(Location location, int requestUserID) {
        return new LocationCreatedDTO(
            location.getId(),
            location.getX(),
            location.getY(),
            location.getZ(),
            location.isEditableByAdmin(),
            location.getUserId().getId() == requestUserID
        );
    }
}
