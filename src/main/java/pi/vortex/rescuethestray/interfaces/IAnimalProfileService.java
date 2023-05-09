package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.AnimalProfile;

import java.util.List;
import java.util.Optional;

public interface IAnimalProfileService {

    AnimalProfile addAnimalProfile(AnimalProfile animalProfile);

    List<AnimalProfile> retriveAllAnimalProfile();

    Optional<AnimalProfile> retriveAnimalProfile(Long id_animal);

    AnimalProfile updateAnimalProfile(AnimalProfile animalProfile);

    void removeAnimalProfile(Long id_animal);
    /*
    AnimalProfile addAnimalProfileAndAssignToUser(AnimalProfile animalProfile, Long id);
*/
}
