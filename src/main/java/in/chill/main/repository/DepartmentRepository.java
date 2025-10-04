package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
