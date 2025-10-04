package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Judge;

public interface JudgeService {
    
    public List<Judge> getAllJudges();
    
    public Optional<Judge> getJudgeById(int id);
    
    public Judge createJudge(Judge judge);
    
    public Judge updateJudge(int id, Judge judge);
    
    public void deleteJudge(int id);
} 