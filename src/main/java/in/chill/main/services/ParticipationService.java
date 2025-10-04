package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Participation;

public interface ParticipationService {
    
    public List<Participation> getAllParticipations();
    
    public Optional<Participation> getParticipationById(int id);
    
    public Participation createParticipation(Participation participation);
    
    public Participation updateParticipation(int id, Participation participation);
    
    public void deleteParticipation(int id);
}
