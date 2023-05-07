package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.interfaces.IDonationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/donation")
public class DonationController {
    IDonationService donationService;

    @GetMapping("/retrieve-all-donations")
    public List<Donation> RetrieveAllDonation() {
        return donationService.retrieveAllDonations();
    }

    @GetMapping("/retrieve-all-donations/{id_don}")
    public Donation retrieveDonation(@PathVariable("id_don") long id_donation) {
        return donationService.retrieveDonation(id_donation);
    }

    @DeleteMapping("/remove-donation/{id_don}")
    public void deleteDonation(@PathVariable("id_don") long id_donation) {
        donationService.removeDonation(id_donation);
    }

    @PostMapping("/add-donation")
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addOrUpdateDonation(donation);
    }

    @PutMapping("/update-donation")
    public Donation UpdateDonation(@RequestBody Donation donation) {
        return donationService.addOrUpdateDonation(donation);
    }

    @PostMapping("/add-donation-assign-compaign/{idcomp}/{id_user}")
    public Donation addDonationAndAssignToCompaignAndAssignToUser(@RequestBody Donation donation, @PathVariable("idcomp") Long id_comp,
                                                                  @PathVariable("id_user") Long id_user) {
        return donationService.addDonationAndAssignToCompaignAndAssignToUser(donation, id_comp, id_user);
    }
}
