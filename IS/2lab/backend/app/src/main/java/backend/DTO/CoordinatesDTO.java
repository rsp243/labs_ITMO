package backend.DTO;

import lombok.Data;

@Data
public class CoordinatesDTO implements TokenizedDTO {
    private Long x;
    private Double y;
    private boolean isEditableByAdmin;
    private TokenDTO token;
}
