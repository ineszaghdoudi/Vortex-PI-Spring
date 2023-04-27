package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.interfaces.IDonationService;
import pi.vortex.rescuethestray.repositories.CompaignRepo;
import pi.vortex.rescuethestray.repositories.DonationRepo;
import pi.vortex.rescuethestray.repositories.UserRepository;

import java.util.List;

@Service
public class DonationService implements IDonationService {
    @Autowired
    DonationRepo donationRepo;

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Autowired
    CompaignRepo compaignRepo;
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

    @Override
    public Donation addDonationAndAssignToCompaignAndAssignToUser(Donation donation, Long id_comp, Long id_user){
        Compaign compaign = compaignRepo.findById(id_comp).orElse(null);
        User user = userRepository.findById(id_user).orElse(null);
        donation.setCompaign(compaign);
        donation.setUser(user);
        donationRepo.save(donation);
        succeedDonation(donation.getId_donation());
        return donation;
    }

    @Override
    public void succeedDonation(Long id_donation) {
        Donation donation = donationRepo.findById(id_donation).orElse(null);
        // Send email notification to the user
        String recipientEmail = donation.getUser().getEmail();
        String subject = "Donation succeed";
        String body = "Dear " + donation.getUser().getUsername() + ",\n" +
                "We are pleased to inform you that your donation for " + donation.getCompaign().getTitle_compaign() +
                " has been succeed. Thank you for your help improving the life of animals in need.\n\n" +
                "Best regards,\nThe 'Rescue The Stray' Team";

        emailService.sendEmail(recipientEmail, subject, body);

    }
    /*
    @Override
    public List<Double> MonthlyDonationStats(int month) {
        //LocalDate start = LocalDate.now().withDayOfMonth(1).withMonth(month);
        //LocalDate end = start.plusMonths(1);
        LocalDate start = LocalDate.of(2022,1,1);
        LocalDate end = LocalDate.of(2022 , 3 , 2);
        List<Donation> donations = donationRepo.findByDate_donationGreaterThanAndDate_donationLessThan(start,end);
        List<Double> monthlyStats = new ArrayList<>();
        for (Donation donation : donations) {
            int numDonors = (int) donations.stream().map(Donation::getUser_id).distinct().count();
            double totalAmount = donations.stream().mapToDouble(Donation::getAmount_donation).sum();
            monthlyStats.add(numDonors, totalAmount);
        }
        return monthlyStats;
    }
    */
}
