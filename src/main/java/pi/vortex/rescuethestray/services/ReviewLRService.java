package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.ReviewLR;
import pi.vortex.rescuethestray.repositories.ReviewLRRepo;

import java.util.List;

@Service
public class ReviewLRService {

    @Autowired
    private ReviewLRRepo reviewRepository;

    public ReviewLR addReviewToResource(ReviewLR review, LearningResource resource) {
        review.setResource(resource);
        return reviewRepository.save(review);
    }

    public List<ReviewLR> getAllReviewsForResource(LearningResource resource) {
        return reviewRepository.findByResource(resource);
    }

    public double getAverageRatingScoreForResource(LearningResource resource) {
        List<ReviewLR> reviews = getAllReviewsForResource(resource);
        if (reviews.isEmpty()) {
            return 0.0;
        } else {
            double sum = reviews.stream().mapToInt(ReviewLR::getRating).sum();
            double averageRating = sum / reviews.size();
            return Math.round(averageRating * 10) / 10.0; // round to 1 decimal place
        }
    }
}
