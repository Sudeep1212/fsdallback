package in.chill.main.dto;

public class RegisterRequest {
    
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
    private String college;
    private String contact;
    
    // Default constructor
    public RegisterRequest() {}
    
    // Getters and setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
}
