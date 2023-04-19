package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.TypeResource;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LearningResourceService implements ILearningResourceService {

    @Autowired
    ILearningResourceRepo iLearningResourceRepo;

    @Override
    public List<LearningResource> retreiveAllLearningResources() {
        return (List<LearningResource>) iLearningResourceRepo.findAll();
    }

    @Override
    public Optional<LearningResource> retrieveLearningResource(Long idLearningResource) {
        return iLearningResourceRepo.findById(idLearningResource);
    }

    @Override
    public LearningResource addLearningResource(LearningResource learningResource)
    {
        return iLearningResourceRepo.save(learningResource);
    }

    @Override
    public LearningResource updateLearningResource(LearningResource learningResource) {
        return iLearningResourceRepo.save(learningResource);
    }

    public void removeLearningResource(Long idLearningResource) {

        iLearningResourceRepo.deleteById(idLearningResource);
    }

    public Map<TypeResource, Long> statsResourcesByType() {
        List<LearningResource> resources = iLearningResourceRepo.findAll();
        return resources.stream()
                .map(LearningResource::getType_learningr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
