package prointern.ProinternApplication.PlacementAssistance.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.PlacementAssistance.Service.AuthService;
import prointern.ProinternApplication.PlacementAssistance.dto.LoginRequest;
import prointern.ProinternApplication.PlacementAssistance.dto.LoginResponse;
import prointern.ProinternApplication.PlacementAssistance.dto.RegisterRequest;
import prointern.ProinternApplication.PlacementAssistance.entities.User;
import prointern.ProinternApplication.PlacementAssistance.repo.UserRepository;

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
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req.getUsername() == null || req.getPassword() == null) {
            return ResponseEntity.badRequest().body("username and password required");
        }
        if (userRepository.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(409).body("username already exists");
        }
        User u = authService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("registered: " + u.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<String> token = authService.login(req.getUsername(), req.getPassword());
        if (token.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(token.get()));
        } else {
            return ResponseEntity.status(401).body("invalid credentials");
        }
    }
}
