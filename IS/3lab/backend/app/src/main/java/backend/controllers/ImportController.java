package backend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.DTO.ImportCreatedDTO;
import backend.DTO.TokenDTO;
import backend.exceptions.DoesNotExistException;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.AdminService;
import backend.services.ImportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/import", produces = { "application/json" })
public class ImportController {
    private final JwtUtils jwtUtils;
    private final ImportService importService;
    private final AdminService adminService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("token") TokenDTO token)
            throws IOException, DoesNotExistException {

        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(token.getToken());

        return ControllerExecutor.execute(validator, () -> {
            ImportCreatedDTO result = importService.processYamlFile(file, token);

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping("/history")
    public ResponseEntity<?> getHistory(@RequestBody TokenDTO token) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(token.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<ImportCreatedDTO> result = importService.getImports(token);
        
            return ResponseEntity.ok().body(result);
        });
    }
}
