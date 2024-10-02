package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.model.validators.UsersValidator;
import backend.security.JwtUtils;
import backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth", produces = { "application/json" })
public class AuthController {
    private final JwtUtils jwtUtils;

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UsersDTO req) {
        UsersValidator validator = new UsersValidator();
        validator.validateName(req).validatePassword(req);

        return ControllerExecutor.execute(validator, () -> {
            TokenDTO tokenDTO;
            try {
                tokenDTO = authService.login(req);
            } catch (ApiException e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
            return ResponseEntity.ok().body(tokenDTO);
        });
    }
}
