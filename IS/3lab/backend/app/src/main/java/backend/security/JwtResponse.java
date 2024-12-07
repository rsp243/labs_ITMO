package backend.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String headerType = "Bearer";
    private String Name;
    private String accessToken;
    private String requestDate;
    private String expirationDate;
}
