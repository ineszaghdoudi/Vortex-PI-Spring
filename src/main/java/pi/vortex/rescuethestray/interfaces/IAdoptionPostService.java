package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.AdoptionPost;

import java.util.List;
import java.util.Optional;

public interface IAdoptionPostService {

    AdoptionPost addAdoptionPost(AdoptionPost adoptionPost);

    List<AdoptionPost> retriveAllAdoptionPost();

    Optional<AdoptionPost> retriveAdoptionPost(Long id_adoptionpost);

    AdoptionPost updateAdoptionPost(AdoptionPost adoptionPost);

    void removeAdoptionPost(Long id_adoptionpost);
}
