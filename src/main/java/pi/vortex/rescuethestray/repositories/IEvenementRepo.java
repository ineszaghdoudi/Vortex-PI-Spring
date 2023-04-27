package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.Evenement;

@Repository
public interface IEvenementRepo extends JpaRepository<Evenement,Long> {
}
