package backend.model;

import java.time.LocalDateTime;

import backend.DTO.ImportCreatedDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "import")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ImportStatus status;
    
    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public static ImportCreatedDTO getCreatedImport(Import importObj) {
        return new ImportCreatedDTO(
            importObj.getStatus(),
            importObj.getUserName(),
            importObj.getCount(),
            importObj.getTime().toString(),
            null
        );
    }
}
