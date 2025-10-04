package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Participation;
import in.chill.main.repository.ParticipationRepository;

@Service
public class ParticipationServiceImplementation implements ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;
    
    @Override
    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    @Override
    public Optional<Participation> getParticipationById(int id) {
        return participationRepository.findById(id);
    }

    @Override
    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public Participation updateParticipation(int id, Participation participation) {
        Participation existingParticipation = participationRepository.findById(id).orElse(null);
        if (existingParticipation != null) {
            existingParticipation.setRegistration(participation.getRegistration());
            existingParticipation.setEvent(participation.getEvent());
            existingParticipation.setEventAmount(participation.getEventAmount());
            return participationRepository.save(existingParticipation);
        } else {
            throw new RuntimeException("Participation not found with id: " + id);
        }
    }

    @Override
    public void deleteParticipation(int id) {
        participationRepository.deleteById(id);
    }
}
