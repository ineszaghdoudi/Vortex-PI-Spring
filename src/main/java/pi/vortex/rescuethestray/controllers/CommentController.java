package pi.vortex.rescuethestray.controllers;

import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Comment;
import pi.vortex.rescuethestray.interfaces.ICommentService;

import java.util.List;
import java.util.Optional;

public class CommentController {

    ICommentService iCommentService;


    @GetMapping("/retrieveAllComments")
    public List<Comment> getComments() {
        return iCommentService.retreiveAllComments();
    }

    @GetMapping("/retrieve-comment/{CommentId}")
    public Optional<Comment> getCommentById(@PathVariable("CommentId") Long CommentId) {
        return iCommentService.retrieveComment(CommentId);
    }

    @PostMapping("/Add-comment")
    public Comment addComment(@RequestBody Comment com) {
        return iCommentService.addComment(com);
    }

    @PutMapping("/update-comment")
    public Comment updateComment(@RequestBody Comment com) {
        return iCommentService.updateComment(com);
    }

    @DeleteMapping("/remove-comment/{CommentId}")
    public void removeComment(@PathVariable("CommentId") Long CommentId) {
        iCommentService.removeComment(CommentId);
    }
}
