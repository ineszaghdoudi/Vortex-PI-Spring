package pi.vortex.rescuethestray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Post;
import pi.vortex.rescuethestray.interfaces.IPostService;
import java.util.List;
import java.util.Optional;

public class PostController {
    @Autowired
    IPostService iPostService;


    @GetMapping("/retrieveAllEvent")
    public List<Post> getPost() {
        return iPostService.retreiveAllPosts();
    }

    @GetMapping("/retrieve-post/{PostId}")
    public Optional<Post> getPostById(@PathVariable("PostId") Long PostId) {
        return iPostService.retrievePost(PostId);
    }

    @PostMapping("/Add-Post")
    public Post addPost(@RequestBody Post post) {
        return iPostService.addPost(post);
    }

    @PutMapping("/update-post")
    public Post updatePost(@RequestBody Post post) {
        return iPostService.updatePost(post);
    }

    @DeleteMapping("/remove-post/{PostId}")
    public void removeEvent(@PathVariable("PostId") Long PostId) {
        iPostService.removePost(PostId);
    }
}
