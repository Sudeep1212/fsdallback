package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Volunteer;
import in.chill.main.repository.VolunteerRepository;

@Service
public class VolunteerServiceImplementation implements VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    
    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Optional<Volunteer> getVolunteerById(int id) {
        return volunteerRepository.findById(id);
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer updateVolunteer(int id, Volunteer volunteer) {
        Volunteer existingVolunteer = volunteerRepository.findById(id).orElse(null);
        if (existingVolunteer != null) {
            existingVolunteer.setName(volunteer.getName());
            existingVolunteer.setRole(volunteer.getRole());
            existingVolunteer.setContact(volunteer.getContact());
            existingVolunteer.setIsAssigned(volunteer.getIsAssigned());
            existingVolunteer.setClub(volunteer.getClub());
            existingVolunteer.setDepartment(volunteer.getDepartment());
            existingVolunteer.setVenue(volunteer.getVenue());
            existingVolunteer.setEvent(volunteer.getEvent());
            return volunteerRepository.save(existingVolunteer);
        } else {
            throw new RuntimeException("Volunteer not found with id: " + id);
        }
    }

    @Override
    public void deleteVolunteer(int id) {
        volunteerRepository.deleteById(id);
    }
}
