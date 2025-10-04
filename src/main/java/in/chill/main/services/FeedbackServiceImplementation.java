package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Feedback;
import in.chill.main.repository.FeedbackRepository;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> getFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(int id, Feedback feedback) {
        Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);
        if (existingFeedback != null) {
            existingFeedback.setDateTime(feedback.getDateTime());
            existingFeedback.setName(feedback.getName());
            existingFeedback.setEmail(feedback.getEmail());
            existingFeedback.setContactNo(feedback.getContactNo());
            existingFeedback.setOverallEventRating(feedback.getOverallEventRating());
            existingFeedback.setOrgManagement(feedback.getOrgManagement());
            existingFeedback.setVenueFacilities(feedback.getVenueFacilities());
            existingFeedback.setTechContent(feedback.getTechContent());
            existingFeedback.setComments(feedback.getComments());
            existingFeedback.setRegistration(feedback.getRegistration());
            return feedbackRepository.save(existingFeedback);
        } else {
            throw new RuntimeException("Feedback not found with id: " + id);
        }
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }
}
