package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Registration;

public interface RegistrationService {
    
    public List<Registration> getAllRegistrations();
    
    public Optional<Registration> getRegistrationById(int id);
    
    public Registration createRegistration(Registration registration);
    
    public Registration updateRegistration(int id, Registration registration);
    
    public void deleteRegistration(int id);
}
