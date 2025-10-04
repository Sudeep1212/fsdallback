package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Volunteer;

public interface VolunteerService {
    
    public List<Volunteer> getAllVolunteers();
    
    public Optional<Volunteer> getVolunteerById(int id);
    
    public Volunteer createVolunteer(Volunteer volunteer);
    
    public Volunteer updateVolunteer(int id, Volunteer volunteer);
    
    public void deleteVolunteer(int id);
}
