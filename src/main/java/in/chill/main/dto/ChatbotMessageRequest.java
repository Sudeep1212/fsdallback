package in.chill.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for chatbot message requests from frontend
 */
public class ChatbotMessageRequest {
    
    @NotBlank(message = "Message cannot be empty")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;
    
    private Object context;
    
    @JsonProperty("sessionId")
    private String sessionId;

    // Default constructor
    public ChatbotMessageRequest() {}

    // Constructor with parameters
    public ChatbotMessageRequest(String message, Object context, String sessionId) {
        this.message = message;
        this.context = context;
        this.sessionId = sessionId;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "ChatbotMessageRequest{" +
                "message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", hasContext=" + (context != null) +
                '}';
    }
}