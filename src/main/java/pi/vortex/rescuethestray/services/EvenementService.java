package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.Evenement;
import pi.vortex.rescuethestray.interfaces.IEvenementService;
import pi.vortex.rescuethestray.repositories.IEvenementRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService implements IEvenementService {

    @Autowired
IEvenementRepo evenementRepo;
    @Override
    public List<Evenement> retreiveAllEvenements() {

        return  evenementRepo.findAll();
    }

    @Override
    public Optional<Evenement> retrieveEvenement(Long idEvent) {
        return evenementRepo.findById(idEvent);
    }

    @Override
    public Evenement addEvenement(Evenement evenement)
    {
        return evenementRepo.save(evenement);
    }

    @Override
    public Evenement updateEvenement(Evenement evenement) {

        return evenementRepo.save(evenement);
    }

    public void removeEvenement(Long idEvent) {

        evenementRepo.deleteById(idEvent);
    }
}
