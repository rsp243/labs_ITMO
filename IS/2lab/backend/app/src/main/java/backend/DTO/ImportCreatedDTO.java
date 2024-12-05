package backend.DTO;

import java.time.LocalDateTime;
import java.util.Map;

import backend.model.ImportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImportCreatedDTO {
    private ImportStatus status;
    private String userName;
    private int count;
    private String time;
    private Map<String, String> errors;
}
