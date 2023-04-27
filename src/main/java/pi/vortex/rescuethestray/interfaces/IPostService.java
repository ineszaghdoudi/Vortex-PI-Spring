package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    Post addPost(Post post);

    List<Post> retreiveAllPosts();

    Optional<Post> retrievePost(Long PostId);

    Post updatePost(Post post);

    void removePost(Long PostId);
}
