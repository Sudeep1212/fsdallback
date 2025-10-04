package in.chill.main.controller;

import in.chill.main.dto.AuthResponse;
import in.chill.main.dto.LoginRequest;
import in.chill.main.dto.RegisterRequest;
import in.chill.main.entity.Admin;
import in.chill.main.services.AdminService;
import in.chill.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201", "http://localhost:3000"})
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminService adminService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        // Only students can register via UI
        AuthResponse response = userService.register(registerRequest);
        
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response;
        
        // Check if this is an admin login (email ends with @admin.org)
        if (Admin.isValidAdminEmail(loginRequest.getEmail())) {
            response = adminService.login(loginRequest);
        } else {
            // Regular student login
            response = userService.login(loginRequest);
        }
        
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * Endpoint to check user type based on email
     * Used by frontend to determine which login form to show
     */
    @GetMapping("/user-type")
    public ResponseEntity<String> getUserType(@RequestParam String email) {
        if (Admin.isValidAdminEmail(email)) {
            return ResponseEntity.ok("ADMIN");
        } else {
            return ResponseEntity.ok("STUDENT");
        }
    }
}
