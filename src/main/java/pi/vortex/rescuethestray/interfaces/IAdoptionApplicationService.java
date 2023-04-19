package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.AdoptionApplication;

import java.util.List;
import java.util.Optional;

public interface IAdoptionApplicationService {

    AdoptionApplication addAdoptionApplication(AdoptionApplication adoptionApplication);

    List<AdoptionApplication> retriveAllAdoptionApplication();

    Optional<AdoptionApplication> retriveAdoptionApplication(Long id_adoptionapp);

    AdoptionApplication updateAdoptionApplication(AdoptionApplication adoptionApplication);

    void removeAdoptionApplication(Long id_adoptionapp);

}
