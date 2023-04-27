package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import pi.vortex.rescuethestray.entities.Reaction;
import pi.vortex.rescuethestray.interfaces.IReactionService;
import pi.vortex.rescuethestray.repositories.IReactionRepo;

import java.util.List;
import java.util.Optional;

public class ReactionService implements IReactionService {

    @Autowired
    IReactionRepo iReactionRepo;

    @Override
    public List<Reaction> retreiveAllReactions() {

        return  iReactionRepo.findAll();
    }

    @Override
    public Optional<Reaction> retrieveReaction(Long ReactionId) {
        return iReactionRepo.findById(ReactionId);
    }

    @Override
    public Reaction addReaction(Reaction reaction)
    {
        return iReactionRepo.save(reaction);
    }

    @Override
    public Reaction updateReaction(Reaction reaction) {

        return iReactionRepo.save(reaction);
    }

    public void removeReaction(Long ReactionId) {

        iReactionRepo.deleteById(ReactionId);
    }
}
