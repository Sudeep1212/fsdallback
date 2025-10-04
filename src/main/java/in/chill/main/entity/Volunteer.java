package in.chill.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "volunteer")
public class Volunteer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    private int volunteerId;
    
    @Column(name = "volunteer_name")
    private String name;
    
    @Column(name = "volunteer_role")
    private String role;
    
    @Column(name = "volunteer_contact")
    private String contact;
    
    @Column(name = "is_assigned")
    private boolean isAssigned;
    
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Events event;
    
    // Default constructor
    public Volunteer() {}
    
    // Constructor with parameters
    public Volunteer(String name, String role, String contact, boolean isAssigned, 
                    Club club, Department department, Venue venue, Events event) {
        this.name = name;
        this.role = role;
        this.contact = contact;
        this.isAssigned = isAssigned;
        this.club = club;
        this.department = department;
        this.venue = venue;
        this.event = event;
    }
    
    // Getters and setters
    public int getVolunteerId() {
        return volunteerId;
    }
    
    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public boolean getIsAssigned() {
        return isAssigned;
    }
    
    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
    
    public Club getClub() {
        return club;
    }
    
    public void setClub(Club club) {
        this.club = club;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Venue getVenue() {
        return venue;
    }
    
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    
    public Events getEvent() {
        return event;
    }
    
    public void setEvent(Events event) {
        this.event = event;
    }
    
    @Override
    public String toString() {
        return "Volunteer{" +
                "volunteerId=" + volunteerId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contact='" + contact + '\'' +
                ", isAssigned=" + isAssigned +
                ", club=" + (club != null ? club.getClub_id() : null) +
                ", department=" + (department != null ? department.getDepartmentId() : null) +
                ", venue=" + (venue != null ? venue.getVenue_id() : null) +
                ", event=" + (event != null ? event.getEvent_id() : null) +
                '}';
    }
}
