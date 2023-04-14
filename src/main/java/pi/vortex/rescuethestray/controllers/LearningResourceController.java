package pi.vortex.rescuethestray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;

import java.util.List;
import java.util.Optional;

@RestController
public class LearningResourceController {

    @Autowired
    private ILearningResourceService iLearningResourceService;


    @GetMapping("/retrieve-all-learning-resources")
    public List<LearningResource> getLearningResources() {
        List<LearningResource> listLearningResources = iLearningResourceService.retreiveAllLearningResources();
        return listLearningResources;
    }

    @GetMapping("/retrieve-learning-resource/{learning_resource-id}")
    public Optional<LearningResource> getLearningResourceById(@PathVariable("learning_resource-id") Long id_learningr) {
        return iLearningResourceService.retrieveLearningResource(id_learningr);
    }

    @PostMapping("/add-learning-resource")
    public LearningResource addLearningResource(@RequestBody LearningResource lr) {
        LearningResource learningResource=iLearningResourceService.addLearningResource(lr);
        return learningResource;
    }

    @PutMapping("/update-learning-resource")
    public LearningResource updateLearningResource(@RequestBody LearningResource lr) {
        LearningResource learningResource= iLearningResourceService.updateLearningResource(lr);
        return learningResource;
    }

    @DeleteMapping("/remove-learning-resource/{learning_resource-id}")
    public void removeLearningResource(@PathVariable("learning_resource-id") Long id_learningr) {
        iLearningResourceService.removeLearningResource(id_learningr);
    }
}
