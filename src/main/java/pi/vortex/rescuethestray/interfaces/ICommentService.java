package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

    Comment addComment(Comment com);

    List<Comment> retreiveAllComments();

    Optional<Comment> retrieveComment(Long CommentId);

    Comment updateComment(Comment com);

    void removeComment(Long CommentId);
}
