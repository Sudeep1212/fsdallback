package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Registration;
import in.chill.main.repository.RegistrationRepository;

@Service
public class RegistrationServiceImplementation implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> getRegistrationById(int id) {
        return registrationRepository.findById(id);
    }

    @Override
    public Registration createRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public Registration updateRegistration(int id, Registration registration) {
        Registration existingRegistration = registrationRepository.findById(id).orElse(null);
        if (existingRegistration != null) {
            existingRegistration.setName(registration.getName());
            existingRegistration.setCollege(registration.getCollege());
            existingRegistration.setEmail(registration.getEmail());
            existingRegistration.setContact(registration.getContact());
            return registrationRepository.save(existingRegistration);
        } else {
            throw new RuntimeException("Registration not found with id: " + id);
        }
    }

    @Override
    public void deleteRegistration(int id) {
        registrationRepository.deleteById(id);
    }
}
