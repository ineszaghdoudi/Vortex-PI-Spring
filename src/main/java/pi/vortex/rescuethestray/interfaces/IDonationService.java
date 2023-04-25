package pi.vortex.rescuethestray.interfaces;


import pi.vortex.rescuethestray.entities.Donation;

import java.util.List;

public interface IDonationService {
    List<Donation> retrieveAllDonations();
    Donation retrieveDonation(Long id_donation);
    Donation addOrUpdateDonation(Donation donation);
    void removeDonation(long id_donation);
}
