package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.vortex.rescuethestray.entities.Reaction;

public interface IReactionRepo extends JpaRepository<Reaction,Long> {
}
