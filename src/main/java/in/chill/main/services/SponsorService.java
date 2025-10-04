package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Sponsor;

public interface SponsorService {
    
    public List<Sponsor> getAllSponsors();
    
    public Optional<Sponsor> getSponsorById(int id);
    
    public Sponsor createSponsor(Sponsor sponsor);
    
    public Sponsor updateSponsor(int id, Sponsor sponsor);
    
    public void deleteSponsor(int id);
}
