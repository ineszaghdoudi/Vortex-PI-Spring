package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.interfaces.IDonationService;
import pi.vortex.rescuethestray.repositories.DonationRepo;

import java.util.List;

@Service
public class DonationService implements IDonationService {
    @Autowired
    DonationRepo donationRepo;

    @Override
    public List<Donation> retrieveAllDonations(){
        return donationRepo.findAll();
    }

    @Override
    public Donation retrieveDonation(Long id_donation){
        return donationRepo.findById(id_donation).orElse(null);
    }
    @Override
    public Donation addOrUpdateDonation(Donation donation){
        return donationRepo.save(donation);
    }
    @Override
    public void removeDonation(long id_donation){
        donationRepo.deleteById(id_donation);
    }
}
