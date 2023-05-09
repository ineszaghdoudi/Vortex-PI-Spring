package pi.vortex.rescuethestray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.repositories.AdoptionPostRepo;
import pi.vortex.rescuethestray.services.AdoptionPostService;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;

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

}
