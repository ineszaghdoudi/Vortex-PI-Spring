package pi.vortex.rescuethestray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;
import pi.vortex.rescuethestray.services.LearningResourceService;

import java.util.List;

@RestController
public class LearningResourceController {

    @Autowired
    private ILearningResourceService iLearningResourceService;


    @GetMapping("/retrieve-all-learning-resources")
    public List<LearningResource> getLearningResources() {
        List<LearningResource> listLearningResources = iLearningResourceService.retreiveAllLearningResources();
        return listLearningResources;
    }

    @PostMapping("/add-learning-resource")
    public LearningResource addLearningResource(@RequestBody LearningResource lr) {
        LearningResource learningResource=iLearningResourceService.addLearningResource(lr);
        return learningResource;
    }
}
