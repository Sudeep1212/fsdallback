package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Feedback;

public interface FeedbackService {
    
    public List<Feedback> getAllFeedbacks();
    
    public Optional<Feedback> getFeedbackById(int id);
    
    public Feedback createFeedback(Feedback feedback);
    
    public Feedback updateFeedback(int id, Feedback feedback);
    
    public void deleteFeedback(int id);
}
