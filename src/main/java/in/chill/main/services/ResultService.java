package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Result;

public interface ResultService {
    
    public List<Result> getAllResults();
    
    public Optional<Result> getResultById(int id);
    
    public Result createResult(Result result);
    
    public Result updateResult(int id, Result result);
    
    public void deleteResult(int id);
}
