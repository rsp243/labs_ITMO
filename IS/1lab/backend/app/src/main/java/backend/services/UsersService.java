    package backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.DTO.UsersCreatedDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.UserAlreadyExists;
import backend.model.Users;
import backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersCreatedDTO register(UsersDTO req) throws UserAlreadyExists {
        if (usersRepository.existsByName(req.getName())) {
            throw new UserAlreadyExists(req.getName());
        }

        Users user = Users.builder()
                .name(req.getName())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        usersRepository.save(user);
        return new UsersCreatedDTO("OK", user.getName());
    }
}
