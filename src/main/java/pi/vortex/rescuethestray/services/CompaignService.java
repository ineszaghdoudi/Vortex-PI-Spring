package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.interfaces.ICompaignService;
import pi.vortex.rescuethestray.repositories.CompaignRepo;

import java.util.List;

@Service
public class CompaignService implements ICompaignService {
    @Autowired
    CompaignRepo compaignRepo;

    @Override
    public List<Compaign> retrieveAllCompaigns() {
        return compaignRepo.findAll();
    }

    @Override
    public Compaign addOrUpdateCompaign(Compaign compaign){
        return compaignRepo.save(compaign);
    }
    @Override
    public  Compaign retrieveCompaign(Long id_compaign){
        return compaignRepo.findById(id_compaign).orElse(null);
    }

    @Override
    public void removeCompaign(long id_compaign){
        compaignRepo.deleteById(id_compaign);
    }

}
