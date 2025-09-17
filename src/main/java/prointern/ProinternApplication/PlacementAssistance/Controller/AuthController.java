package prointern.ProinternApplication.PlacementAssistance.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.PlacementAssistance.Service.AuthService;
import prointern.ProinternApplication.PlacementAssistance.dto.LoginRequest;
import prointern.ProinternApplication.PlacementAssistance.dto.LoginResponse;
import prointern.ProinternApplication.PlacementAssistance.dto.RegisterRequest;
import prointern.ProinternApplication.Repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        if (req.getUsername() == null || req.getPassword() == null) {
        	throw new DetailsNotFoundException("Username and password required");
        }
        if (userRepository.existsByUsername(req.getUsername())) {
        	throw new DetailsNotFoundException("Username already exists");
        }
        return authService.register(req.getUsername(), req.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<String> token = authService.login(req.getUsername(), req.getPassword());
        if (token.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(token.get()));
        } else {
        	throw new DetailsNotFoundException("Invalid credentials");
        }
    }
}
