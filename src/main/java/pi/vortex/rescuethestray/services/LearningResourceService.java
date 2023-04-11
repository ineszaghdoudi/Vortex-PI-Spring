package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;

import java.util.List;

@Service
public class LearningResourceService implements ILearningResourceService {

    @Autowired
    ILearningResourceRepo iLearningResourceRepo;

    @Override
    public List<LearningResource> retreiveAllLearningResources() {
        return (List<LearningResource>) iLearningResourceRepo.findAll();
    }

    @Override
    public LearningResource addLearningResource(LearningResource learningResource)
    {
        return iLearningResourceRepo.save(learningResource);
    }
}
