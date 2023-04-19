package pi.vortex.rescuethestray.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.AnimalProfile;

@Repository
public interface AnimalProfileRepo extends JpaRepository<AnimalProfile, Long> {
}
