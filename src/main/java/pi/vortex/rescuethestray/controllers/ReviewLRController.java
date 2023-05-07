package pi.vortex.rescuethestray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.ReviewLR;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;
import pi.vortex.rescuethestray.services.LearningResourceService;
import pi.vortex.rescuethestray.services.ReviewLRService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReviewLRController {

    @Autowired
    private ILearningResourceRepo learningResourceRepository;

    @Autowired
    private ReviewLRService reviewLRService;

    @Autowired
    private LearningResourceService learningResourceService;

    @PostMapping("/addReview/{id}")
    public ResponseEntity<ReviewLR> addReview(@PathVariable Long id, @RequestBody ReviewLR review) {
        Optional<LearningResource> resourceOptional = learningResourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            LearningResource resource = resourceOptional.get();
            ReviewLR newReview = reviewLRService.addReviewToResource(review, resource);
            return ResponseEntity.ok(newReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<List<ReviewLR>> getReviews(@PathVariable Long id) {
        Optional<LearningResource> resourceOptional = learningResourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            LearningResource resource = resourceOptional.get();
            List<ReviewLR> reviews = reviewLRService.getAllReviewsForResource(resource);
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/average-rating-score/{resourceId}")
    public ResponseEntity<Double> getAverageRatingScore(@PathVariable Long resourceId) {
        LearningResource resource = new LearningResource();
        resource.setId_learningr(resourceId);

        double averageRating = reviewLRService.getAverageRatingScoreForResource(resource);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }


}
