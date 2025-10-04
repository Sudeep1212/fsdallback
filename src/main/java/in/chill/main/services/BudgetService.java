package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import in.chill.main.entity.Budget;

public interface BudgetService {
    
    public List<Budget> getAllBudgets();
    
    public Optional<Budget> getBudgetById(int id);
    
    public Budget createBudget(Budget budget);
    
    public Budget updateBudget(int id, Budget budget);
    
    public void deleteBudget(int id);
}
