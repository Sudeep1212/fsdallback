package in.chill.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "venue")
public class Venue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private int venue_id;
    
    @Column(name = "venue_name")
    private String name;
    
    @Column(name = "floor")
    private int floor;
    
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Events> events;
    
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Volunteer> volunteers;
    
    // Default constructor
    public Venue() {}
    
    // Constructor with parameters
    public Venue(String name, int floor) {
        this.name = name;
        this.floor = floor;
    }
    
    // Getters and setters
    public int getVenue_id() {
        return venue_id;
    }
    
    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    public List<Events> getEvents() {
        return events;
    }
    
    public void setEvents(List<Events> events) {
        this.events = events;
    }
    
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
    
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
    
    @Override
    public String toString() {
        return "Venue{" +
                "venue_id=" + venue_id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }
} 