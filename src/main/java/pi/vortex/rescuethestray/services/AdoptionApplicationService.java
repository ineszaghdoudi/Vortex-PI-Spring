package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.AdoptionApplication;
import pi.vortex.rescuethestray.interfaces.IAdoptionApplicationService;
import pi.vortex.rescuethestray.repositories.AdoptionApplicationRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionApplicationService implements IAdoptionApplicationService {

    @Autowired
    AdoptionApplicationRepo adoptionApplicationRepo;

    @Override
    public List<AdoptionApplication> retriveAllAdoptionApplication(){
        return (List<AdoptionApplication>) adoptionApplicationRepo.findAll();
    }

    @Override
    public Optional<AdoptionApplication> retriveAdoptionApplication(Long id_adoptionapp){
        return adoptionApplicationRepo.findById(id_adoptionapp);
    }

    @Override
    public AdoptionApplication addAdoptionApplication(AdoptionApplication adoptionApplication){
        return adoptionApplicationRepo.save(adoptionApplication);
    }

    @Override
    public AdoptionApplication updateAdoptionApplication(AdoptionApplication adoptionApplication) {
        return adoptionApplicationRepo.save(adoptionApplication);
    }

    public void removeAdoptionApplication (Long id_adoptionapp) {
        adoptionApplicationRepo.deleteById(id_adoptionapp);
    }
}
