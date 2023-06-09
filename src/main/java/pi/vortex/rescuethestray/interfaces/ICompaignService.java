package pi.vortex.rescuethestray.interfaces;

import pi.vortex.rescuethestray.entities.Compaign;

import java.util.HashMap;
import java.util.List;

public interface ICompaignService {
    List<Compaign> retrieveAllCompaigns();
    Compaign retrieveCompaign(Long id_compaign);
    Compaign addOrUpdateCompaign (Compaign compaign);
    void removeCompaign(long id_compaign);

    HashMap<String,Double> CompaignStatistics(Long id_compaign) ;
    double predictLikelihoodOfReachingTarget(Long compaignId) ;

}
