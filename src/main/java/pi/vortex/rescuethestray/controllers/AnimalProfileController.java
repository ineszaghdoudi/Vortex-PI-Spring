package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.repositories.AnimalProfileRepo;
import pi.vortex.rescuethestray.interfaces.IAnimalProfileService;
import pi.vortex.rescuethestray.entities.AnimalProfile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class AnimalProfileController {

    @Autowired
    IAnimalProfileService iAnimalProfileService;

    @GetMapping("/retriveAllAnimalProfile")
    List<AnimalProfile> retriveAllAnimalProfile(){
        return  iAnimalProfileService.retriveAllAnimalProfile();
    }

    @PostMapping("/addAnimalProfile")
    public AnimalProfile addAnimalProfile(@RequestBody AnimalProfile animalProfile) {
        return iAnimalProfileService.addAnimalProfile(animalProfile);
    }

    @GetMapping("/retrieveAnimalProfile/{id}")
    public Optional<AnimalProfile> retriveAnimalProfile(@PathVariable("id") Long id_animal) {
        return iAnimalProfileService.retriveAnimalProfile(id_animal);
    }

    @PutMapping("/updateAnimalProfile")
    public AnimalProfile updateAnimalProfile(@RequestBody AnimalProfile animalProfile) {
        return iAnimalProfileService.updateAnimalProfile(animalProfile);
    }

    @DeleteMapping("/removeAnimalProfile/{id}")
    public void removeAnimalProfile(@PathVariable("id") Long id_animal) {
        iAnimalProfileService.removeAnimalProfile(id_animal);
    }
/*
    @PostMapping("/addAnimalProfileAndAssignToUser/{id}")
    public AnimalProfile addAnimalProfileAndAssignToUser(@RequestBody AnimalProfile animalProfile,@PathVariable("id") Long id) {
        return iAnimalProfileService.addAnimalProfileAndAssignToUser(animalProfile, id);
    }

 */
}
