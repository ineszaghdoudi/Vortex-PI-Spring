package pi.vortex.rescuethestray.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.entities.AnimalProfile;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.interfaces.IAdoptionPostService;
import pi.vortex.rescuethestray.repositories.AdoptionPostRepo;
import pi.vortex.rescuethestray.repositories.AnimalProfileRepo;
import pi.vortex.rescuethestray.repositories.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptionPostService implements IAdoptionPostService {

    @Autowired
    AdoptionPostRepo adoptionPostRepo;
    @Autowired
    AnimalProfileRepo animalProfileRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    EmailService emailService;

    @Override
    public List<AdoptionPost> retriveAllAdoptionPost(){
        return (List<AdoptionPost>) adoptionPostRepo.findAll();
    }

    @Override
    public Optional<AdoptionPost> retriveAdoptionPost(Long id_adoptionpost){
        return adoptionPostRepo.findById(id_adoptionpost);
    }
    /*
    @Override
    public AdoptionPost addAdoptionPost(AdoptionPost adoptionPost){
        return adoptionPostRepo.save(adoptionPost);
    }
    */
    @Override
    public AdoptionPost updateAdoptionPost(AdoptionPost adoptionPost) {
        return adoptionPostRepo.save(adoptionPost);
    }

    public void removeAdoptionPost (Long id_adoptionpost) {
        adoptionPostRepo.deleteById(id_adoptionpost);
    }

    @Override
    public AdoptionPost addAdoptionPostAndAssignToAnimalProfile(AdoptionPost adoptionPost, Long id_animal){
        AnimalProfile animalProfile = animalProfileRepo.findById(id_animal).orElse(null);
        adoptionPost.setAnimalProfile(animalProfile);
        adoptionPost.setPhotoUrl_adoptionpost(animalProfile.getPhotoUrl_animal());
        return adoptionPostRepo.save(adoptionPost);
    }

    public void sendAdoptionCheckInEmail(AdoptionPost adoptionPost) {
        LocalDate adoptionDate = adoptionPost.getAdoptionDate();
        if (adoptionDate != null) {
            LocalDate now = LocalDate.now();
            long daysSinceAdoption = ChronoUnit.DAYS.between(adoptionDate, now);
            if (daysSinceAdoption == 0) { //15
                Long id = adoptionPost.getAdopter_id();
                User adopter = userRepo.findById(id).orElse(null);
                String recipientEmail = adopter.getEmail();
                String subject = "Check-in on your adopted animal";
                String body = "Dear " + adopter.getFullName() + ",\n\n" +
                        "We hope you and your adopted animal are doing well. It has been 15 " +
                        "days since the adoption date. We would like to check in and see how everything is going.\n\n" +
                        "If you have any questions or concerns, please do not hesitate to contact us.\n\n" +
                        "Best regards,\nThe Adoption Team";
                emailService.sendEmail(recipientEmail, subject, body);
                System.out.println("mail sent");
            }
        }
    }


}
