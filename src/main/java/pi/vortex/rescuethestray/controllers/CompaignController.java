package pi.vortex.rescuethestray.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.Compaign;
import pi.vortex.rescuethestray.interfaces.ICompaignService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Compaign")
public class CompaignController {
    ICompaignService compaignService;

    @GetMapping("/retrieve-all-compaigns")
    public List<Compaign> retrieveAllCompaigns(){
        return compaignService.retrieveAllCompaigns();
    }
    @GetMapping("/retrieve-compaign/{id_Comp}")
    public Compaign retrieveCompaign(@PathVariable("id_Comp")long id_compaign){
        return compaignService.retrieveCompaign(id_compaign);
    }
    @DeleteMapping("/remove-compaign/{id_Comp}")
    void removeCompaign(@PathVariable("id_Comp") long id_compaign){
        compaignService.removeCompaign(id_compaign);
    }
    @PostMapping("/add-compaign")
    public Compaign addCompaign(@RequestBody Compaign compaign){
        return compaignService.addOrUpdateCompaign(compaign);
    }
    @PutMapping("/update-compaign")
    public Compaign UpdateCompaign(@RequestBody Compaign compaign){
        return compaignService.addOrUpdateCompaign(compaign);
    }
}
