package pi.vortex.rescuethestray.interfaces;


import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.TypeLRInterest;
import pi.vortex.rescuethestray.entities.TypeResource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ILearningResourceService {
    LearningResource addLearningResource(LearningResource learningResource);

    List<LearningResource> retreiveAllLearningResources();

    Optional<LearningResource> retrieveLearningResource(Long idLearningResource);

    LearningResource updateLearningResource(LearningResource learningResource);

    void removeLearningResource(Long idLearningResource);

    Map<TypeResource, Long> statsResourcesByType();

    Map<TypeLRInterest, Long> statsResourcesByTheme();

    void sendmail(LearningResource lr);

    void sendLearningResourceEmails();



}
