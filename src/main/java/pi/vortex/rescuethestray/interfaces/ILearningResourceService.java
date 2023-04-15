package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.LearningResource;

import java.util.List;
import java.util.Optional;

public interface ILearningResourceService {
    LearningResource addLearningResource(LearningResource learningResource);

    List<LearningResource> retreiveAllLearningResources();

    Optional<LearningResource> retrieveLearningResource(Long idLearningResource);

    LearningResource updateLearningResource(LearningResource learningResource);

    void removeLearningResource(Long idLearningResource);

}
