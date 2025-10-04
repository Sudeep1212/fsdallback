package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
}
