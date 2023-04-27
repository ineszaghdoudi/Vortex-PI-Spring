package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.Reaction;

import java.util.List;
import java.util.Optional;

public interface IReactionService {

    Reaction addReaction(Reaction reaction);

    List<Reaction> retreiveAllReactions();

    Reaction updateReaction(Reaction reaction);

    void removeReaction(Long ReactionId);

    Optional<Reaction> retrieveReaction(Long ReactionId);
}
