package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.TypeResource;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILearningResourceRepo extends JpaRepository<LearningResource,Long> {

}
