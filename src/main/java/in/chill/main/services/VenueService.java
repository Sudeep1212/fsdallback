package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Venue;

public interface VenueService {
    
    public List<Venue> getAllVenues();
    
    public Optional<Venue> getVenueById(int id);
    
    public Venue createVenue(Venue venue);
    
    public Venue updateVenue(int id, Venue venue);
    
    public void deleteVenue(int id);
} 