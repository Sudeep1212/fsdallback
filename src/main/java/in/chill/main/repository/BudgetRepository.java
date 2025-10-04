package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
