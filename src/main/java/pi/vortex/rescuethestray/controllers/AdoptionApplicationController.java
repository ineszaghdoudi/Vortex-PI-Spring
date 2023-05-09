package pi.vortex.rescuethestray.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.AdoptionApplication;
import pi.vortex.rescuethestray.entities.TypeAnimal;
import pi.vortex.rescuethestray.interfaces.IAdoptionApplicationService;
import pi.vortex.rescuethestray.repositories.AdoptionApplicationRepo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class AdoptionApplicationController {


    IAdoptionApplicationService iAdoptionApplicationService;

     AdoptionApplicationRepo adoptionApplicationRepo;

    @GetMapping("/retriveAllAdoptionApplication")
    public List<AdoptionApplication> retriveAllAdoptionApplication() {
        return iAdoptionApplicationService.retriveAllAdoptionApplication();
    }

    @GetMapping("/retriveByAdoptionPost/{id}")
    public List<AdoptionApplication> retriveByAdoptionPost(@PathVariable("id") Long id_adoptionpost) {
        return iAdoptionApplicationService.retriveByAdoptionPost(id_adoptionpost);
    }

    @GetMapping("/retriveAdoptionApplication/{id}")
    public Optional<AdoptionApplication> retriveAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        return iAdoptionApplicationService.retriveAdoptionApplication(id_adoptionapp);
    }

    @PutMapping("/updateAdoptionApplication")
    public AdoptionApplication updateAdoptionApplication(@RequestBody AdoptionApplication adoptionApplication) {
        return iAdoptionApplicationService.updateAdoptionApplication(adoptionApplication);
    }

    @DeleteMapping("/removeAdoptionApplication/{id}")
    public void removeAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        iAdoptionApplicationService.removeAdoptionApplication(id_adoptionapp);
    }

    @PostMapping("/addAdoptionApplicationWithAdoptionPostAndUser/{id}/{id2}")
    public AdoptionApplication addAdoptionApplicaionAndAssignToAdoptionPostAndUser(@RequestBody AdoptionApplication adoptionApplication,@PathVariable("id")
    Long id_adoptionpost, @PathVariable("id2") Long id) {
        return iAdoptionApplicationService.addAdoptionApplicaionAndAssignToAdoptionPostAndUser(adoptionApplication, id_adoptionpost , id);
    }



    @GetMapping("/send")
    public void Send() {
        iAdoptionApplicationService.Send();
    }

    @GetMapping("/adoptionapplication-animaltype-stats")
    public ResponseEntity<List<Map<String, Object>>> statsApplicationsByAnimalType() {
        List<AdoptionApplication> applications = adoptionApplicationRepo.findAll();
        Map<TypeAnimal, Long> stats = applications.stream()
                .map(a -> a.getAdoptionPost().getAnimalProfile().getType_animal())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Map<String, Object>> data = new ArrayList<>();
        for (TypeAnimal type : stats.keySet()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("label", type.name());
            entry.put("data", stats.get(type));
            data.add(entry);
        }

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/aproveadoptionapp/{id}")
    public ResponseEntity<String> approveAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        iAdoptionApplicationService.aproveAdoptionApplication(id_adoptionapp);
        return ResponseEntity.ok("Adoption Application approved");
    }

    @GetMapping("/denyadoptionapp/{id}")
    public ResponseEntity<String> denyAdoptionApplication(@PathVariable("id") Long id_adoptionapp) {
        iAdoptionApplicationService.denyAdoptionApplication(id_adoptionapp);
        return ResponseEntity.ok("Adoption Application denied");

    }

    @GetMapping("/bestadoptionapps/{id}")
    public List<AdoptionApplication> getBestAdoptionApplications(@PathVariable("id") Long id_adoptionpost) {
        return iAdoptionApplicationService.getBestAdoptionApplications(id_adoptionpost);
    }


}
