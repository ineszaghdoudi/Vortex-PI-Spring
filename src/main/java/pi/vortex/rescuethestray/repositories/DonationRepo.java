package pi.vortex.rescuethestray.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.Donation;

@Repository
public interface DonationRepo extends JpaRepository<Donation,Long> {
}
