package backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.DTO.CoordinatesCreatedDTO;
import backend.DTO.IdDTO;
import backend.DTO.TokenDTO;
import backend.DTO.UsersCreatedDTO;
import backend.model.Coordinates;
import backend.model.Person;
import backend.model.Users;
import backend.model.validators.TokenValidator;
import backend.security.JwtUtils;
import backend.services.AdminService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.LinkedList;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin", produces = { "application/json" })
public class AdminController {
    private final JwtUtils jwtUtils;
    private final AdminService adminService;
    

    @PostMapping(path = "/add")
    public ResponseEntity<?> getAdminRequests(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());
        
        return ControllerExecutor.execute(validator, () -> {
            UsersCreatedDTO usersDTO = adminService.addAdmin(req);
            
            return ResponseEntity.ok().body(usersDTO);
        });
    }

    @PostMapping(path = "/requests")
    public ResponseEntity<?> addAdmin(@RequestBody TokenDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken());

        return ControllerExecutor.execute(validator, () -> {
            List<Users> allRequests = adminService.getAllRequests(req);
            List<UsersCreatedDTO> result = new LinkedList<UsersCreatedDTO>();
            for (int i = 0; i < allRequests.size(); i++) {
                Users iUser = allRequests.get(i);
                result.add(new UsersCreatedDTO("Requests for admin permissions", iUser.getId(), iUser.getName()));
            }

            return ResponseEntity.ok().body(result);
        });
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addAdmin(@RequestBody IdDTO req) {
        TokenValidator validator = new TokenValidator(jwtUtils).validateToken(req.getToken().getToken());

        return ControllerExecutor.execute(validator, () -> {
            UsersCreatedDTO result = adminService.approveAdmin(req);

            return ResponseEntity.ok().body(result);
        });
    }
}
