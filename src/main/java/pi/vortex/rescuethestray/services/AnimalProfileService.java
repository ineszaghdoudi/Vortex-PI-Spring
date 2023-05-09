package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.entities.AnimalProfile;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.interfaces.IAnimalProfileService;
import pi.vortex.rescuethestray.repositories.AnimalProfileRepo;

import pi.vortex.rescuethestray.repositories.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class AnimalProfileService implements IAnimalProfileService {

    @Autowired
    AnimalProfileRepo animalProfileRepo;

    @Autowired
    UserRepository userRepo;
    @Override
    public List<AnimalProfile> retriveAllAnimalProfile(){

        return animalProfileRepo.findAll();
    }

    @Override
    public Optional<AnimalProfile> retriveAnimalProfile(Long id_animal){
        return animalProfileRepo.findById(id_animal);
    }

    @Override
    public AnimalProfile addAnimalProfile(AnimalProfile animalProfile){
        return animalProfileRepo.save(animalProfile);
    }

    @Override
    public AnimalProfile updateAnimalProfile(AnimalProfile animalProfile) {
        return animalProfileRepo.save(animalProfile);
    }

    @Override
    public void removeAnimalProfile (Long id_animal) {
        animalProfileRepo.deleteById(id_animal);
    }

}
