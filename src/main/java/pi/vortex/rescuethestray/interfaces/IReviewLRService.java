package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.ReviewLR;

import java.util.List;

public interface IReviewLRService {
    ReviewLR addReviewToResource(ReviewLR review, LearningResource resource);

    List<ReviewLR> getAllReviewsForResource(LearningResource resource);

    double getAverageRatingForResource(LearningResource resource);
}
