package in.chill.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chill.main.entity.Budget;
import in.chill.main.repository.BudgetRepository;

@Service
public class BudgetServiceImplementation implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    
    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> getBudgetById(int id) {
        return budgetRepository.findById(id);
    }

    @Override
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Budget updateBudget(int id, Budget budget) {
        Budget existingBudget = budgetRepository.findById(id).orElse(null);
        if (existingBudget != null) {
            existingBudget.setAllocatedAmount(budget.getAllocatedAmount());
            existingBudget.setUtilizedAmount(budget.getUtilizedAmount());
            existingBudget.setSponsor(budget.getSponsor());
            existingBudget.setClub(budget.getClub());
            existingBudget.setEvent(budget.getEvent());
            return budgetRepository.save(existingBudget);
        } else {
            throw new RuntimeException("Budget not found with id: " + id);
        }
    }

    @Override
    public void deleteBudget(int id) {
        budgetRepository.deleteById(id);
    }
}
