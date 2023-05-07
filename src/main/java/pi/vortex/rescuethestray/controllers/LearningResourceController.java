package pi.vortex.rescuethestray.controllers;

import com.sun.nio.sctp.NotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.config.NotificationLRHandler;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.TypeLRInterest;
import pi.vortex.rescuethestray.entities.TypeResource;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;
import pi.vortex.rescuethestray.repositories.UserRepository;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LearningResourceController {

    @Autowired
    private ILearningResourceService iLearningResourceService;

    @Autowired
    private ILearningResourceRepo iLearningResourceRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationLRHandler notificationHandler;

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

        // Broadcast a notification to connected clients
        String message = "A new resource has been created: " + lr.getTitle_learningr()+ "\n Check it out!";
        try {
            notificationHandler.broadcastNotification(message);
        } catch (IOException e) {

        }

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



    @GetMapping("/learning-resource-type-stats")
    public ResponseEntity<List<Map<String, Object>>> statsResourcesByType() {
        List<LearningResource> resources = iLearningResourceRepo.findAll();
        Map<TypeResource, Long> stats = resources.stream()
                .map(LearningResource::getType_learningr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map<String, Object>> data = new ArrayList<>();
        for (TypeResource type : stats.keySet()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("label", type.name());
            entry.put("data", stats.get(type));
            data.add(entry);
        }

        return ResponseEntity.ok().body(data);
    }


    @GetMapping("/learning-resource-theme-stats")
    public ResponseEntity<List<Map<String, Object>>> statsResourcesByTheme() {
        List<LearningResource> resources = iLearningResourceRepo.findAll();

        Map<TypeLRInterest, Long> stats = resources.stream()
                .map(LearningResource::getTheme)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map<String, Object>> data = new ArrayList<>();
        for (TypeLRInterest theme : stats.keySet()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("label", theme.name());
            entry.put("data", stats.get(theme));
            data.add(entry);
        }

        return ResponseEntity.ok().body(data);
    }





}
