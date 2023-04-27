package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.entities.Donation;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.interfaces.ICompaignService;
import pi.vortex.rescuethestray.repositories.CompaignRepo;
import pi.vortex.rescuethestray.repositories.DonationRepo;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompaignService implements ICompaignService {
    @Autowired
    CompaignRepo compaignRepo;
    @Autowired
    DonationRepo donationRepo;

    @Override
    public List<Compaign> retrieveAllCompaigns() {
        return compaignRepo.findAll();
    }

    @Override
    public Compaign addOrUpdateCompaign(Compaign compaign){
        return compaignRepo.save(compaign);
    }
    @Override
    public  Compaign retrieveCompaign(Long id_compaign){
        return compaignRepo.findById(id_compaign).orElse(null);
    }

    @Override
    public void removeCompaign(long id_compaign){
        compaignRepo.deleteById(id_compaign);
    }

    public Long countDistinctByUserIn(List<Donation> donations) {
        Set<User> distinctUsers = new HashSet<>();
        for (Donation donation : donations) {
            distinctUsers.add(donation.getUser());
        }
        return (long) distinctUsers.size();
    }

    @Override
    public HashMap<String,Double> CompaignStatistics(Long id_compaign) {
        Compaign compaign = compaignRepo.findById(id_compaign).
                orElseThrow(() ->new EntityNotFoundException("Campaign not found with id " + id_compaign));
        List<Donation> donations = donationRepo.findAllByCompaign(compaign);

        double totalDonations = donations.stream().mapToDouble(Donation::getAmount_donation).sum();
        int numDonations = donations.size();
        double avgDonation = totalDonations / numDonations;
        long numDonors = countDistinctByUserIn (donations);
        double percentComplete = (totalDonations / compaign.getTarget_amount_compaign()) * 100.0;

        HashMap<String, Double> statisticsMap = new HashMap<>();
        statisticsMap.put("totalDonations", totalDonations);
        statisticsMap.put("number of Donations", (double) numDonations);
        statisticsMap.put("Number of Donors",(double) numDonors);
        statisticsMap.put("avgDonation", avgDonation);
        statisticsMap.put("percentComplete", percentComplete);

        return statisticsMap;
    }

    public double predictLikelihoodOfReachingTarget(Long compaignId) {
        Compaign compaign = compaignRepo.findById(compaignId)
                    .orElseThrow(() -> new RuntimeException("Compaign not found"));

            LocalDate currentDate = LocalDate.now();
            LocalDate endDate = compaign.getEnd_date_compaign();
            long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);
            Double totalDonations = CompaignStatistics(compaignId).get("totalDonations");
            if (remainingDays <= 0) {
                return totalDonations >= compaign.getTarget_amount_compaign() ? 100 : 0;
            }

            double remainingAmount = compaign.getTarget_amount_compaign() - totalDonations;
            double dailyRate = remainingAmount / remainingDays;
            double avg = CompaignStatistics(compaignId).get("avgDonation");
            double likelihood = avg*0.8/dailyRate;
            if (likelihood> 100)
                likelihood=100;
        // dailyRate * 7 / compaign.getTarget_amount_compaign(); // assuming a week has 7 days

            return likelihood;
    }



}
