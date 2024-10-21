package backend.services;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import backend.DTO.UsersCreatedDTO;
import backend.DTO.UsersDTO;
import backend.exceptions.UserAlreadyExists;
import backend.model.Users;
import backend.model.UsersAdminStatus;
import backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository usersRepository;

    public static String encryptViaSHA384(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-384 
            MessageDigest md = MessageDigest.getInstance("SHA-384"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e);
        } 
    } 

    public UsersCreatedDTO register(UsersDTO req) throws UserAlreadyExists {
        if (usersRepository.existsByName(req.getName())) {
            throw new UserAlreadyExists(req.getName());
        }

        Users user = Users.builder()
                .name(req.getName())
                .password(encryptViaSHA384(req.getPassword()))
                .adminStatus(UsersAdminStatus.USER)
                .build();

        usersRepository.save(user);
        return new UsersCreatedDTO("Successfully created", user.getId(), user.getName());
    }

    public Users getById(int id) throws NotFoundException {
        Users user = usersRepository.getReferenceById(Long.valueOf(id));

        return user;
    }
}
