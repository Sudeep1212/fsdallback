package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
