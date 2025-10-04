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
@Table(name = "judge")
public class Judge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int judge_id;
    
    @Column
    private String judge_name;
    
    @OneToMany(mappedBy = "judge", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Events> events;
    
    // Default constructor
    public Judge() {}
    
    // Constructor with parameters
    public Judge(String judge_name) {
        this.judge_name = judge_name;
    }
    
    // Getters and setters
    public int getJudge_id() {
        return judge_id;
    }
    
    public void setJudge_id(int judge_id) {
        this.judge_id = judge_id;
    }
    
    public String getJudge_name() {
        return judge_name;
    }
    
    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
    }
    
    public List<Events> getEvents() {
        return events;
    }
    
    public void setEvents(List<Events> events) {
        this.events = events;
    }
    
    @Override
    public String toString() {
        return "Judge{" +
                "judge_id=" + judge_id +
                ", judge_name='" + judge_name + '\'' +
                '}';
    }
} 