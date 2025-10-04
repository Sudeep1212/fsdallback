package in.chill.main.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;
    
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "contact_no")
    private String contactNo;
    
    @Column(name = "overall_event_rating")
    private int overallEventRating;
    
    @Column(name = "org_management")
    private int orgManagement;
    
    @Column(name = "venue_facilities")
    private int venueFacilities;
    
    @Column(name = "tech_content")
    private int techContent;
    
    @Column(name = "comments")
    private String comments;
    
    @ManyToOne
    @JoinColumn(name = "registration_id")
    @JsonIgnore // Prevent JSON serialization issues
    private Registration registration;
    
    // Default constructor
    public Feedback() {}
    
    // Constructor with parameters
    public Feedback(LocalDateTime dateTime, String name, String email, String contactNo, 
                   int overallEventRating, int orgManagement, int venueFacilities, 
                   int techContent, String comments, Registration registration) {
        this.dateTime = dateTime;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.overallEventRating = overallEventRating;
        this.orgManagement = orgManagement;
        this.venueFacilities = venueFacilities;
        this.techContent = techContent;
        this.comments = comments;
        this.registration = registration;
    }
    
    // Getters and setters
    public int getFeedbackId() {
        return feedbackId;
    }
    
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContactNo() {
        return contactNo;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    
    public int getOverallEventRating() {
        return overallEventRating;
    }
    
    public void setOverallEventRating(int overallEventRating) {
        this.overallEventRating = overallEventRating;
    }
    
    public int getOrgManagement() {
        return orgManagement;
    }
    
    public void setOrgManagement(int orgManagement) {
        this.orgManagement = orgManagement;
    }
    
    public int getVenueFacilities() {
        return venueFacilities;
    }
    
    public void setVenueFacilities(int venueFacilities) {
        this.venueFacilities = venueFacilities;
    }
    
    public int getTechContent() {
        return techContent;
    }
    
    public void setTechContent(int techContent) {
        this.techContent = techContent;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Registration getRegistration() {
        return registration;
    }
    
    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
    
    @PrePersist
    protected void onCreate() {
        if (this.dateTime == null) {
            this.dateTime = LocalDateTime.now();
        }
    }
    
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", overallEventRating=" + overallEventRating +
                ", orgManagement=" + orgManagement +
                ", venueFacilities=" + venueFacilities +
                ", techContent=" + techContent +
                ", comments='" + comments + '\'' +
                ", registration=" + (registration != null ? registration.getRegistrationId() : null) +
                '}';
    }
}
