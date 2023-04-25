package pi.vortex.rescuethestray.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.AdoptionPost;
import pi.vortex.rescuethestray.interfaces.IAdoptionPostService;
import pi.vortex.rescuethestray.repositories.AdoptionPostRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionPostService implements IAdoptionPostService {

    @Autowired
    AdoptionPostRepo adoptionPostRepo;

    @Override
    public List<AdoptionPost> retriveAllAdoptionPost(){
        return (List<AdoptionPost>) adoptionPostRepo.findAll();
    }

    @Override
    public Optional<AdoptionPost> retriveAdoptionPost(Long id_adoptionpost){
        return adoptionPostRepo.findById(id_adoptionpost);
    }

    @Override
    public AdoptionPost addAdoptionPost(AdoptionPost adoptionPost){
        return adoptionPostRepo.save(adoptionPost);
    }

    @Override
    public AdoptionPost updateAdoptionPost(AdoptionPost adoptionPost) {
        return adoptionPostRepo.save(adoptionPost);
    }

    public void removeAdoptionPost (Long id_adoptionpost) {
        adoptionPostRepo.deleteById(id_adoptionpost);
    }
}
