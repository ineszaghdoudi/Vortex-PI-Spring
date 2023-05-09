package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	Optional<User> findByUsername(String username);
	//Optional<User> findById(Long id);

	User existsUserByUsername(String username);
	Optional<User> findById(Long id);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}
