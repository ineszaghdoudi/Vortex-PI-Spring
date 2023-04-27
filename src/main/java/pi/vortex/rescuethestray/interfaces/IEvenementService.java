package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.Evenement;

import java.util.List;
import java.util.Optional;

public interface IEvenementService {

    Evenement addEvenement(Evenement evenement);

    List<Evenement> retreiveAllEvenements();

    Optional<Evenement> retrieveEvenement(Long idEvent);

    Evenement updateEvenement(Evenement evenement);

    void removeEvenement(Long idEvent);
}
