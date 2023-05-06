package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.interfaces.IDonationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/donation")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/add-donation-assign-compaign/{idcomp}/{id_user}")
    public Donation addDonationAndAssignToCompaignAndAssignToUser(@RequestBody Donation donation, @PathVariable("idcomp") Long id_comp,
                                                                  @PathVariable("id_user") Long id_user) {
        return donationService.addDonationAndAssignToCompaignAndAssignToUser(donation, id_comp,id_user);
    }

    @Autowired


    @GetMapping("/Donations-ByCompaign")
    public ResponseEntity<List<Map<String, Object>>> GetDonationsByCompaign() {
        Map<Compaign, Long> stats = donationService.GetDonationsByCompaign();

        List<Map<String, Object>> data = new ArrayList<>();
        for (Compaign compaign : stats.keySet()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("label", compaign.getTitle_compaign());
            entry.put("data", stats.get(compaign));
            data.add(entry);
        }

        return ResponseEntity.ok().body(data);
    }

    /*
    @GetMapping("/monthly-donations/{month}")
    public List<Double> MonthlyDonationStats(@PathVariable("month") int month) {
        return donationService.MonthlyDonationStats(month);
    }*/
}
