package pi.vortex.rescuethestray.repositories;


import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.vortex.rescuethestray.entities.AdoptionPost;

@Repository
public interface AdoptionPostRepo extends JpaRepository<AdoptionPost, Long> {
}
