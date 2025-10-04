package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Result;
import in.chill.main.repository.ResultRepository;

@Service
public class ResultServiceImplementation implements ResultService {

    @Autowired
    private ResultRepository resultRepository;
    
    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Optional<Result> getResultById(int id) {
        return resultRepository.findById(id);
    }

    @Override
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(int id, Result result) {
        Result existingResult = resultRepository.findById(id).orElse(null);
        if (existingResult != null) {
            existingResult.setRank(result.getRank());
            existingResult.setScore(result.getScore());
            existingResult.setEvent(result.getEvent());
            existingResult.setParticipation(result.getParticipation());
            return resultRepository.save(existingResult);
        } else {
            throw new RuntimeException("Result not found with id: " + id);
        }
    }

    @Override
    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }
}
