package pi.vortex.rescuethestray.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.LearningResource;
import pi.vortex.rescuethestray.entities.TypeLRInterest;
import pi.vortex.rescuethestray.entities.TypeResource;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.interfaces.ILearningResourceService;
import pi.vortex.rescuethestray.repositories.ILearningResourceRepo;
import pi.vortex.rescuethestray.repositories.UserRepository;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LearningResourceService implements ILearningResourceService {

    @Autowired
    ILearningResourceRepo iLearningResourceRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;


    @Override
    public List<LearningResource> retreiveAllLearningResources() {
        return (List<LearningResource>) iLearningResourceRepo.findAll();
    }

    @Override
    public Optional<LearningResource> retrieveLearningResource(Long idLearningResource) {
        return iLearningResourceRepo.findById(idLearningResource);
    }

    @Override
    public LearningResource addLearningResource(LearningResource learningResource) {
        iLearningResourceRepo.save(learningResource);
        //sendmail(learningResource);
        return learningResource;
    }

    @Override
    public LearningResource updateLearningResource(LearningResource learningResource) {
        return iLearningResourceRepo.save(learningResource);
    }

    public void removeLearningResource(Long idLearningResource) {

        iLearningResourceRepo.deleteById(idLearningResource);
    }

    public Map<TypeResource, Long> statsResourcesByType() {
        List<LearningResource> resources = iLearningResourceRepo.findAll();
        return resources.stream()
                .map(LearningResource::getType_learningr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<TypeLRInterest, Long> statsResourcesByTheme() {
        List<LearningResource> resources = iLearningResourceRepo.findAll();
        return resources.stream()
                .map(LearningResource::getTheme)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


    //   @Scheduled(fixedRate = 86400000) // run every 24 hours
    @Scheduled(cron = "0 55 18 * * *") // Runs the task at 6:38 PM every day
    public void sendLearningResourceEmails() {
        List<LearningResource> learningResources = iLearningResourceRepo.findAll();
        LocalDate today = LocalDate.now();
        for (LearningResource learningResource : learningResources) {
            LocalDate date = learningResource.getCreationdate_learningr();
            if (today.compareTo(date) == 0) {
                sendmail(learningResource);
            }
        }
    }

    public void sendmail(LearningResource lr) {

        List<User> interestedUsers = userRepository.findByInterestsIn(Collections.singletonList(lr.getTheme()));

        for (User user : interestedUsers) {
            String recipientEmail = user.getEmail();
            String subject = "New learning resource available";
            String body = "Dear " + user.getFullName() + ",\n\nA new learning resource is available:\n\n" +
                    "Title: " + lr.getTitle_learningr() + "\n" +
                    "Description: " + lr.getDesc_learningr() + "\n" +
                    "Type: " + lr.getType_learningr() + "\n" +
                    "URL: " + lr.getUrl_learningr() + "\n\n" +
                    "You might be interested in it, it's about one of your interests "+user.getInterests()+"\n\n"+
                    "Best regards,\nThe Learning Resources team";

            emailService.sendEmail(recipientEmail, subject, body);
        }
    }



}
