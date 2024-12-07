package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryCreatedDTO {
    private String userName;
    private String time;
}
