package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.LearningResource;

import java.util.List;

public interface ILearningResourceService {
    LearningResource addLearningResource(LearningResource learningResource);

    List<LearningResource> retreiveAllLearningResources();
}
