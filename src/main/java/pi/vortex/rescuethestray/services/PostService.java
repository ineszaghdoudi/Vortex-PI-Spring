package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import pi.vortex.rescuethestray.entities.Post;
import pi.vortex.rescuethestray.interfaces.IPostService;
import pi.vortex.rescuethestray.repositories.IPostRepo;

import java.util.List;
import java.util.Optional;

public class PostService implements IPostService {
    @Autowired
    IPostRepo iPostRepo;

    @Override
    public List<Post> retreiveAllPosts() {

        return  iPostRepo.findAll();
    }

    @Override
    public Optional<Post> retrievePost(Long PostId) {
        return iPostRepo.findById(PostId);
    }

    @Override
    public Post addPost(Post post)
    {
        return iPostRepo.save(post);
    }

    @Override
    public Post updatePost(Post post) {

        return iPostRepo.save(post);
    }

    public void removePost(Long PostId) {

        iPostRepo.deleteById(PostId);
    }
}
