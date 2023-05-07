package pi.vortex.rescuethestray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;

@SpringBootApplication
public class RescueTheStrayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RescueTheStrayApplication.class, args);

    }

}
