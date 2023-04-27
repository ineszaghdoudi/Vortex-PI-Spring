package pi.vortex.rescuethestray.interfaces;


import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.entities.TypeResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDonationService {
    List<Donation> retrieveAllDonations();
    Donation retrieveDonation(Long id_donation);
    Donation addOrUpdateDonation(Donation donation);
    void removeDonation(long id_donation);
    Donation addDonationAndAssignToCompaignAndAssignToUser(Donation donation, Long id_comp, Long id_user);

    void succeedDonation(Long id_donation) ;

        //public List<Double> MonthlyDonationStats(int month);
}
