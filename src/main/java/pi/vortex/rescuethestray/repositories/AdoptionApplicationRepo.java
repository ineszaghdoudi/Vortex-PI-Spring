package pi.vortex.rescuethestray.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.AdoptionApplication;

import java.util.List;

@Repository
public interface AdoptionApplicationRepo extends JpaRepository<AdoptionApplication, Long> {
    /*
    @Query(value= "SELECT app FROM AdoptionApplication app WHERE app.adoptionPost.id_adoptionpost =:adoptionpostid")
    List<AdoptionApplication> findByAdoptionPostId(@Param("adoptionPostId")Long adoptionpostid);
    */
    //List<AdoptionApplication> findByAdoptionPostId_Adoptionpost(Long adoptionpostid);
}
