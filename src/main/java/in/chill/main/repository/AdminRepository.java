package in.chill.main.repository;

import in.chill.main.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    /**
     * Find admin by email
     * @param email The email to search for
     * @return Optional containing the admin if found
     */
    Optional<Admin> findByEmail(String email);
    
    /**
     * Check if admin exists by email
     * @param email The email to check
     * @return true if admin exists, false otherwise
     */
    boolean existsByEmail(String email);
}