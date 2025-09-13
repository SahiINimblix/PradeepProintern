package prointern.ProinternApplication.PlacementAssistance.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.Repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User u = new User(username, hashed);
        return userRepository.save(u);
    }

    public Optional<String> login(String username, String password) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt== null) return Optional.empty();
        User u = opt.get();
        if (BCrypt.checkpw(password, u.getPasswordHash())) {
            String token = UUID.randomUUID().toString();
            u.setToken(token);
            userRepository.save(u);
            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }

    public Optional<User> findByToken(String token) {
        if (token == null)throw new DetailsNotFoundException("Invalid token or not found");
        return userRepository.findByToken(token);
    }
}
