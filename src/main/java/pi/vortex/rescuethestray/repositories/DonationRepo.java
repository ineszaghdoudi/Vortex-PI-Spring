package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.entities.Donation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation,Long> {
    List<Donation> findAllByCompaign(Compaign compaign);
   // List<Donation> findByDate_donationGreaterThanAndDate_donationLessThan(LocalDate startDate,LocalDate endDate);
   // Long countDistinctByUserIn(List<Donation> donations);

}
