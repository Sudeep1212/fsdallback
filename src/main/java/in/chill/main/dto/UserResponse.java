package in.chill.main.dto;

public class UserResponse {
    
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String college;
    private String contact;
    private String role;
    
    // Default constructor
    public UserResponse() {}
    
    // Constructor
    public UserResponse(Long userId, String email, String firstName, String lastName, String college, String contact, String role) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.contact = contact;
        this.role = role;
    }
    
    // Getters and setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCollege() {
        return college;
    }
    
    public void setCollege(String college) {
        this.college = college;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
