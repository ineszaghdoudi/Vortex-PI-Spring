package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.ReviewLR;

import java.util.List;

@Repository
public interface ReviewLRRepo extends JpaRepository<ReviewLR,Long> {
    List<ReviewLR> findByResource(LearningResource resource);
}
