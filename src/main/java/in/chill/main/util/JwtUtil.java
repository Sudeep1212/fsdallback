package in.chill.main.util;

import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class JwtUtil {
    
    // Simple token generation for now - will be replaced with proper JWT later
    public String generateToken(String email, Long userId) {
        String payload = email + ":" + userId + ":" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }
    
    public String getEmailFromToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            return decoded.split(":")[0];
        } catch (Exception e) {
            return null;
        }
    }
    
    public Long getUserIdFromToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            return Long.parseLong(decoded.split(":")[1]);
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean isTokenValid(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split(":");
            return parts.length == 3 && parts[0] != null && parts[1] != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isTokenExpired(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split(":");
            long timestamp = Long.parseLong(parts[2]);
            long currentTime = System.currentTimeMillis();
            // Token expires after 24 hours (86400000 ms)
            return (currentTime - timestamp) > 86400000;
        } catch (Exception e) {
            return true;
        }
    }
}
