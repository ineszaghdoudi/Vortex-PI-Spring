package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.vortex.rescuethestray.entities.Post;

public interface IPostRepo extends JpaRepository<Post,Long> {
}
