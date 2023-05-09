package pi.vortex.rescuethestray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.repositories.AdoptionPostRepo;
import pi.vortex.rescuethestray.services.AdoptionPostService;

import java.util.List;

@EnableScheduling
@SpringBootApplication
public class RescueTheStrayApplication {

    @Autowired
    AdoptionPostRepo adoptionPostRepo;
    @Autowired
    AdoptionPostService adoptionPostService;
    public static void main(String[] args) {
        SpringApplication.run(RescueTheStrayApplication.class, args);
    }
    /*
    @Scheduled(fixedRate = 60000) // run every 60 seconds : 60000 ----- run every 24 hours :86400000
    public void sendAdoptionCheckInEmails() {
        List<AdoptionPost> adoptionPosts = adoptionPostRepo.findAll();
        for (AdoptionPost adoptionPost : adoptionPosts) {
           adoptionPostService.sendAdoptionCheckInEmail(adoptionPost);
        }
    }

     */
}
