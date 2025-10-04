package in.chill.main.dto;

public class AuthResponse {
    
    private String token;
    private UserResponse user;
    private String message;
    
    // Default constructor
    public AuthResponse() {}
    
    // Constructor
    public AuthResponse(String token, UserResponse user, String message) {
        this.token = token;
        this.user = user;
        this.message = message;
    }
    
    // Getters and setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public UserResponse getUser() {
        return user;
    }
    
    public void setUser(UserResponse user) {
        this.user = user;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
