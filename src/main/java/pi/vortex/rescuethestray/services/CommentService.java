package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import pi.vortex.rescuethestray.entities.Comment;
import pi.vortex.rescuethestray.interfaces.ICommentService;
import pi.vortex.rescuethestray.repositories.ICommentRepo;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    @Override
    public List<Comment> retreiveAllComments() {

        return  iCommentRepo.findAll();
    }

    @Override
    public Optional<Comment> retrieveComment(Long CommentId) {
        return iCommentRepo.findById(CommentId);
    }

    @Override
    public Comment updateComment(Comment com) {
        return null;
    }

    @Override
    public void removeComment(Long CommentId) {
        iCommentRepo.deleteById(CommentId);

    }

    @Override
    public Comment addComment(Comment com)
    {
        return iCommentRepo.save(com);
    }


}
