package backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.DTO.TokenDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.ApiException;
import backend.exceptions.DoesNotExistException;
import backend.exceptions.WrongPasswordException;
import backend.model.Users;
import backend.repository.UserRepository;
import backend.security.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository usersRepository;
    // private final AuthenticationManager authenticationManager;
    // private final AuthentificatedMap authMap;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(UsersDTO req) throws DoesNotExistException, ApiException, WrongPasswordException {
        Users userEntity = usersRepository.findByName(req.getName())
                .orElseThrow(() -> new DoesNotExistException(req.getName()));

        if (!passwordEncoder.matches(req.getPassword(), userEntity.getPassword()))
            throw new WrongPasswordException(req.getName());

        // CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        TokenDTO token = new TokenDTO(jwtUtils.generateAccessToken(userEntity));
        return token;
    }

    public long getUserIdFromToken(String token) throws DoesNotExistException {
        Claims userClaims = jwtUtils.getClaims(token);
        final String username = userClaims.get("sub", String.class);

        if (!usersRepository.existsByName(username)) {
            throw new DoesNotExistException(username);
        }

        Users userEntity = usersRepository.findByName(username)
            .orElseThrow(() -> new DoesNotExistException(username));
        final long userId = userEntity.getId();
        return userId;
    }
}
