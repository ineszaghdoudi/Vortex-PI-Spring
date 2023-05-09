package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.*;
import pi.vortex.rescuethestray.interfaces.IAdoptionApplicationService;
import pi.vortex.rescuethestray.repositories.AdoptionApplicationRepo;
import pi.vortex.rescuethestray.repositories.AdoptionPostRepo;
import pi.vortex.rescuethestray.repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdoptionApplicationService implements IAdoptionApplicationService {

    @Autowired
    AdoptionApplicationRepo adoptionApplicationRepo;

    @Autowired
    AdoptionPostRepo adoptionPostRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired EmailService emailService;

    @Override
    public List<AdoptionApplication> retriveAllAdoptionApplication(){
        return (List<AdoptionApplication>) adoptionApplicationRepo.findAll();
    }


    public List<AdoptionApplication> retriveByAdoptionPost(Long id_adoptionpost) {
        List<AdoptionApplication> listad= new ArrayList<>();
        List<AdoptionApplication> adoptionApplications = adoptionApplicationRepo.findAll();
        for(AdoptionApplication adoptionApplication : adoptionApplications){
            AdoptionPost adoptionPost = adoptionApplication.getAdoptionPost();
            Long id = adoptionPost.getId_adoptionpost();
            if(id == id_adoptionpost) {
                listad.add(adoptionApplication);
            }
        }
       return listad  ;
    }
    @Override
    public Optional<AdoptionApplication> retriveAdoptionApplication(Long id_adoptionapp){
        return adoptionApplicationRepo.findById(id_adoptionapp);
    }
    /*
    @Override
    public AdoptionApplication addAdoptionApplication(AdoptionApplication adoptionApplication){
        return adoptionApplicationRepo.save(adoptionApplication);
    }
    */
    @Override
    public AdoptionApplication updateAdoptionApplication(AdoptionApplication adoptionApplication) {

        return adoptionApplicationRepo.save(adoptionApplication);
    }

    public void removeAdoptionApplication (Long id_adoptionapp) {
        adoptionApplicationRepo.deleteById(id_adoptionapp);
    }

    @Override
    public AdoptionApplication addAdoptionApplicaionAndAssignToAdoptionPostAndUser(AdoptionApplication adoptionApplication, Long id_adoptionpost, Long id){
        AdoptionPost adoptionPost = adoptionPostRepo.findById(id_adoptionpost).orElse(null);
        User user = userRepo.findById(id).orElse(null);
        adoptionApplication.setAdoptionPost(adoptionPost);
        adoptionApplication.setUser(user);
        return adoptionApplicationRepo.save(adoptionApplication);
    }
    public void Send(){

        String recipientEmail = "mohamed.mrabet@esprit.tn";
        String subject = "Adoption application approved";
        String body = "ija hezz lkalb mteek ";

        emailService.sendEmail(recipientEmail, subject, body);
    }

    public Map<TypeAnimal, Long> statsApplicationsByAnimalType() {
        List<AdoptionApplication> applications = adoptionApplicationRepo.findAll();
        return applications.stream()
                .map(aa -> aa.getAdoptionPost().getAnimalProfile().getType_animal())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Integer aproveAdoptionApplication(Long id_adoptionapp) {
        AdoptionApplication application = adoptionApplicationRepo.findById(id_adoptionapp)
                .orElseThrow(null);

        List<AdoptionApplication> adoptionApplications= adoptionApplicationRepo.findAll();

        application.setStatus_adoptionapp(ApplicationStatus.APPROVED);
        AdoptionPost adoptionPost =application.getAdoptionPost();
        adoptionPost.setStatus_adoptionpost(AdoptionStatus.ADOPTED);
        //adoptionPost.setAdopter_id(application.getUser().getId());
        adoptionPost.setAdoptionDate(LocalDate.now());

/*
        for (AdoptionApplication adoptionApplication : adoptionApplications) {
            if (adoptionApplication.getId_adoptionapp()!= id_adoptionapp){
                adoptionApplication.setStatus_adoptionapp(ApplicationStatus.REJECTED);
                String recipientEmail2 = adoptionApplication.getUser().getEmail();
                String subject2 = "Adoption application denied";
                String body2 = "Dear " + adoptionApplication.getUser().getFullName() + ",\n\n" +
                        "We regret to inform you that your adoption application for " + adoptionApplication.getAdoptionPost().getAnimalProfile().getName_animal() +
                        " has been denied by the owner. If you have any questions or concerns, please contact us.\n\n" +
                        "Best regards,\nThe Adoption Team";
                emailService.sendEmail(recipientEmail2, subject2, body2);
            }
        }

*/
        adoptionApplicationRepo.save(application);
        adoptionPostRepo.save(adoptionPost);
        /*

        // Send email notification the one requesting the adoption
        String recipientEmail = application.getUser().getEmail();
        String subject = "Adoption application approved";
        String body = "Dear " + application.getUser().getFullName() + ",\n\n" +
                "We are pleased to inform you that your adoption application for " + application.getAdoptionPost().getAnimalProfile().getName_animal() +
                " has been approved by the owner. Please contact us to proceed with the adoption process.\n\n" +
                "Best regards,\nThe Adoption Team";

        emailService.sendEmail(recipientEmail, subject, body);

        //Send email notification to the one who posted the adoption
        String recipientEmail1 = application.getAdoptionPost().getAnimalProfile().getUser().getEmail();
        String subject1 = "Adoption application approved";
        String body1 = "Dear " + application.getAdoptionPost().getAnimalProfile().getUser().getFullName() + ",\n\n" +
                "We are pleased to inform you that " + application.getUser().getFullName() + " has been notified of your approval to adopt " +
                application.getAdoptionPost().getAnimalProfile().getName_animal() + " from your adoption post. Please contact us to proceed with the adoption process.\n\n" +
                "Best regards,\nThe Adoption Team";
        emailService.sendEmail(recipientEmail1, subject1, body1);
        */
        return 0;
    }
    public Integer denyAdoptionApplication(Long id_adoptionapp) {
        AdoptionApplication application = adoptionApplicationRepo.findById(id_adoptionapp)
                .orElse(null);
        application.setStatus_adoptionapp(ApplicationStatus.REJECTED);
        adoptionApplicationRepo.save(application);
        /*
        // Send email notification to the one requesting the adoption
        String recipientEmail = application.getUser().getEmail();
        String subject = "Adoption application denied";
        String body = "Dear " + application.getUser().getFullName() + ",\n\n" +
                "We regret to inform you that your adoption application for " + application.getAdoptionPost().getAnimalProfile().getName_animal() +
                " has been denied by the owner. If you have any questions or concerns, please contact us.\n\n" +
                "Best regards,\nThe Adoption Team";
       emailService.sendEmail(recipientEmail, subject, body);
        */
        return 0;
    }


    public List<AdoptionApplication> getBestAdoptionApplications(Long id_adoptionpost){

        List<AdoptionApplication> applications = adoptionApplicationRepo.findAll();

        applications = applications.stream()
                .filter(app -> app.getAdoptionPost().getId_adoptionpost().equals(id_adoptionpost))
                .collect(Collectors.toList());
        return applications.stream()
                .sorted(Comparator.comparingInt(app -> {
                    int score = 0;
                    if (app.getHasExperience()) {
                        score += 10;
                    }
                    if (app.getOtherPets()) {
                        score += 10;
                    }
                    if (app.getAdoptionPost().getAnimalProfile().getMedicalNeeds_animal() && app.getApplicantOccupation().equals("Vet")) {
                        score += 10;
                    }
                    switch (app.getAdoptionPost().getAnimalProfile().getSize_animal()){
                        case BIG:
                            switch (app.getApplicantHouseholdSize()){
                                case VERYBIG:
                                    score += 4;
                                    break;
                                case BIG:
                                    score += 3;
                                    break;
                                case MEDIUM:
                                    score += 2;
                                    break;
                                case SMALL:
                                    score -= 2;
                                    break;
                            }
                        case SMALL:
                            switch (app.getApplicantHouseholdSize()){
                                case VERYBIG:
                                    score += 1;
                                    break;
                                case BIG:
                                    score += 3;
                                    break;
                                case MEDIUM:
                                    score += 4;
                                    break;
                                case SMALL:
                                    score += 4;
                                    break;
                            }
                        default:
                            switch (app.getApplicantHouseholdSize()){
                                case VERYBIG:
                                    score += 4;
                                    break;
                                case BIG:
                                    score += 3;
                                    break;
                                case MEDIUM:
                                    score += 2;
                                    break;
                            }

                    }

                    if (app.getAdoptionPost().getAnimalProfile().getType_animal().equals(TypeAnimal.DOG)) {
                        if(app.getApplicantHousingType().equals("COUNTRYHOUSE")){
                            score += 5;
                        }
                    }

                    return -score; // make the score negative to sort in descending order

                })).limit(3).collect(Collectors.toList());

    }

}
