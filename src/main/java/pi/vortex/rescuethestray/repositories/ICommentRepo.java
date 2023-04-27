package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.vortex.rescuethestray.entities.Comment;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
