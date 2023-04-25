package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.Compaign;

@Repository
public interface CompaignRepo extends JpaRepository<Compaign,Long> {
}
