package dk.binfo.repositories;

import dk.binfo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Vagabonden
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {
	 User findByEmail(String email);
}
