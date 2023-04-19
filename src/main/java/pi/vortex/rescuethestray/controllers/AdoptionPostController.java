package pi.vortex.rescuethestray.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.interfaces.IAdoptionPostService;

import java.util.List;
import java.util.Optional;

@RestController
public class AdoptionPostController {

    @Autowired
    IAdoptionPostService iAdoptionPostService;

    @PostMapping("/addAdoptionPost")
    public AdoptionPost addAdoptionPost(@RequestBody AdoptionPost adoptionPost) {
        return iAdoptionPostService.addAdoptionPost(adoptionPost);
    }

    @GetMapping("/retriveAllAdoptionPost")
    public List<AdoptionPost> retriveAllAdoptionPost() {
        return iAdoptionPostService.retriveAllAdoptionPost();
    }

    @GetMapping("/retriveAdoptionPost/{id}")
    public Optional<AdoptionPost> retriveAdoptionPost(@PathVariable("id") Long id_adoptionpost) {
        return iAdoptionPostService.retriveAdoptionPost(id_adoptionpost);
    }

    @PutMapping("/updateAdoptionPost")
    public AdoptionPost updateAdoptionPost(@RequestBody AdoptionPost adoptionPost) {
        return iAdoptionPostService.updateAdoptionPost(adoptionPost);
    }

    @DeleteMapping("/removeAdoptionPost/{id}")
    public void removeAdoptionPost(@PathVariable("id") Long id_adoptionpost) {
        iAdoptionPostService.removeAdoptionPost(id_adoptionpost);
    }
}
