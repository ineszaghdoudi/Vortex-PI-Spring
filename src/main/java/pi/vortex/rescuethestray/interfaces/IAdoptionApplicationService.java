package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.AdoptionApplication;
import pi.vortex.rescuethestray.entities.TypeAnimal;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IAdoptionApplicationService {
    /*
    AdoptionApplication addAdoptionApplication(AdoptionApplication adoptionApplication);
    */
    List<AdoptionApplication> retriveAllAdoptionApplication();

    List<AdoptionApplication> retriveByAdoptionPost(Long id_adoptionpost);

    Optional<AdoptionApplication> retriveAdoptionApplication(Long id_adoptionapp);

    AdoptionApplication updateAdoptionApplication(AdoptionApplication adoptionApplication);

    void removeAdoptionApplication(Long id_adoptionapp);

    AdoptionApplication addAdoptionApplicaionAndAssignToAdoptionPostAndUser(AdoptionApplication adoptionApplication, Long id_adoptionpost, Long id);

    void Send();

    Map<TypeAnimal, Long> statsApplicationsByAnimalType();

    public Integer aproveAdoptionApplication(Long id_adoptionapp);
    public Integer denyAdoptionApplication(Long id_adoptionapp);

    public List<AdoptionApplication> getBestAdoptionApplications(Long id_adoptionpost);


}
