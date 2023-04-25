package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.interfaces.IDonationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/donation")
public class DonationController {
    IDonationService donationService;
    @GetMapping("/retrieve-all-donations")
    public List<Donation> RetrieveAllDonation(){
        return donationService.retrieveAllDonations();
    }
    @GetMapping("/retrieve-all-donations/{id_don}")
    public Donation retrieveDonation(@PathVariable("id_don")long id_donation){
        return donationService.retrieveDonation(id_donation);
    }
    @DeleteMapping("/remove-donation/{id_don}")
    public void deleteDonation(@PathVariable("id_don")long id_donation){
        donationService.removeDonation(id_donation);
    }
    @PostMapping("/add-donation")
    public Donation addDonation(@RequestBody Donation donation){
        return donationService.addOrUpdateDonation(donation);
    }
    @PutMapping("/update-donation")
    public Donation UpdateDonation(@RequestBody Donation donation){
        return donationService.addOrUpdateDonation(donation);
    }
}
