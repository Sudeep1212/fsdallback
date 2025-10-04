package in.chill.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for chatbot responses to frontend
 */
public class ChatbotMessageResponse {
    
    private String reply;
    private String status;
    
    @JsonProperty("sessionId")
    private String sessionId;
    
    private Long timestamp;

    // Default constructor
    public ChatbotMessageResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    // Constructor with parameters
    public ChatbotMessageResponse(String reply, String status, String sessionId) {
        this.reply = reply;
        this.status = status;
        this.sessionId = sessionId;
        this.timestamp = System.currentTimeMillis();
    }

    // Success response factory method
    public static ChatbotMessageResponse success(String reply, String sessionId) {
        return new ChatbotMessageResponse(reply, "success", sessionId);
    }

    // Error response factory method
    public static ChatbotMessageResponse error(String errorMessage, String sessionId) {
        return new ChatbotMessageResponse(errorMessage, "error", sessionId);
    }

    // Getters and Setters
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatbotMessageResponse{" +
                "reply='" + reply + '\'' +
                ", status='" + status + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}