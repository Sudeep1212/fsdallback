package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Club;

public interface ClubService {
    
    public List<Club> getAllClubs();
    
    public Optional<Club> getClubById(int id);
    
    public Club createClub(Club club);
    
    public Club updateClub(int id, Club club);
    
    public void deleteClub(int id);
} 