package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Evenement;
import pi.vortex.rescuethestray.interfaces.IEvenementService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EvenementController {


IEvenementService evenementService;

    @GetMapping("/retrieveAllEvent")
    public List<Evenement> getEvent() {
        return evenementService.retreiveAllEvenements();
    }

    @GetMapping("/retrieve-event/{event-id}")
    public Optional<Evenement> getEventById(@PathVariable("event-id") Long id_event) {
        return evenementService.retrieveEvenement(id_event);
    }

    @PostMapping("/Add-event")
    public Evenement addEvent(@RequestBody Evenement ev) {
        return evenementService.addEvenement(ev);
    }

    @PutMapping("/update-event")
    public Evenement updateEvent(@RequestBody Evenement ev) {
        return evenementService.updateEvenement(ev);
    }

    @DeleteMapping("/remove-event/{event-id}")
    public void removeEvent(@PathVariable("event-id") Long id_event) {
        evenementService.removeEvenement(id_event);
    }
}
