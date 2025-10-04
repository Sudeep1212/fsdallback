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
@Table(name = "department")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;
    
    @Column(name = "department_name")
    private String name;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Volunteer> volunteers;
    
    // Default constructor
    public Department() {}
    
    // Constructor with parameters
    public Department(String name) {
        this.name = name;
    }
    
    // Getters and setters
    public int getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
    
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }
}
