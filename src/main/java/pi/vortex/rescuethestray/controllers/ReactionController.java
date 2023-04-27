package pi.vortex.rescuethestray.controllers;

import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Reaction;
import pi.vortex.rescuethestray.interfaces.IReactionService;

import java.util.List;
import java.util.Optional;

public class ReactionController {
    IReactionService iReactionService;


    @GetMapping("/retrieveAllReaction")
    public List<Reaction> getAllReactions() {
        return iReactionService.retreiveAllReactions();
    }

    @GetMapping("/retrieve-reaction/{reaction-id}")
    public Optional<Reaction> getReactionById(@PathVariable("ReactionId") Long ReactionId) {
        return iReactionService.retrieveReaction(ReactionId);
    }

    @PostMapping("/Add-reaction")
    public Reaction addReaction(@RequestBody Reaction reaction) {
        return iReactionService.addReaction(reaction);
    }

    @PutMapping("/update-reaction")
    public Reaction updateReaction(@RequestBody Reaction reaction) {
        return iReactionService.updateReaction(reaction);
    }

    @DeleteMapping("/remove-reaction/{reaction-id}")
    public void removeReaction(@PathVariable("reaction-id") Long ReactionId) {
        iReactionService.removeReaction(ReactionId);
    }

}
