package backend.services;

import java.util.List;
import java.util.LinkedList;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.DTO.TokenDTO;
import backend.DTO.UsersCreatedDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.WrongPasswordException;
import backend.model.Users;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import ch.qos.logback.core.subst.Token;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository usersRepository;
    private final JwtUtils jwtUtils;

    public TokenDTO login(UsersDTO req) throws DoesNotExistException, ApiException, WrongPasswordException {
        Users userEntity = usersRepository.findByName(req.getName())
                .orElseThrow(() -> new DoesNotExistException(req.getName()));

        if (!UsersService.encryptViaSHA384(req.getPassword()).equals(userEntity.getPassword()))
            throw new WrongPasswordException(req.getName());

        // CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        TokenDTO token = new TokenDTO(jwtUtils.generateAccessToken(userEntity));
        return token;
    }
}
