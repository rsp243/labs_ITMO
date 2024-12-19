package backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import backend.DTO.HistoryCreatedDTO;
import backend.DTO.LocationCreatedDTO;

@Entity
@Table(name = "history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "obj_id", nullable = false)
    private int idObj;
    
    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "objectClass", nullable = false)
    private String objectClass;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public static HistoryCreatedDTO getCreatedHistory(History history) {
        return new HistoryCreatedDTO(
            history.getUserName(),
            history.getTime().toString()
        );
    }
}
