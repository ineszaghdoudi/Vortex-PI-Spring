package pi.vortex.rescuethestray.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.AdoptionApplication;
import pi.vortex.rescuethestray.interfaces.IAdoptionApplicationService;

import java.util.List;
import java.util.Optional;

@RestController
public class AdoptionApplicationController {

    @Autowired
    IAdoptionApplicationService iAdoptionApplicationService;

    @PostMapping("/addAdoptionApplication")
    public AdoptionApplication addAdoptionApplication(@RequestBody AdoptionApplication adoptionApplication) {
        return iAdoptionApplicationService.addAdoptionApplication(adoptionApplication);
    }

    @GetMapping("/retriveAllAdoptionApplication")
    public List<AdoptionApplication> retriveAllAdoptionApplication() {
        return iAdoptionApplicationService.retriveAllAdoptionApplication();
    }

    @GetMapping("/retriveAdoptionApplication/{id}")
    public Optional<AdoptionApplication> retriveAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        return iAdoptionApplicationService.retriveAdoptionApplication(id_adoptionapp);
    }

    @PutMapping("/updateAdoptionApplication")
    public AdoptionApplication updateAdoptionApplication(@RequestBody AdoptionApplication adoptionApplication) {
        return iAdoptionApplicationService.updateAdoptionApplication(adoptionApplication);
    }

    @DeleteMapping("/removeAdoptionApplication")
    public void removeAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        iAdoptionApplicationService.removeAdoptionApplication(id_adoptionapp);
    }
}
