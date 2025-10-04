package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
}
