package ad.uda.tprats.backend.controllers;
import ad.uda.tprats.backend.services.ProjectWebService;
import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/secure/statistics")
public class StatisticsWebController {

    @Autowired
    private ProjectWebService projectWebService;

    @Autowired
    private EventService eventService;
    
    @GetMapping("")
    public ModelAndView index(Model model) {
        return new ModelAndView("secure/statistics/stats");
    }

    @ResponseBody
    @GetMapping(value = "/getData")
    public List<Project> getlist() {
        List<Project> projects = projectWebService.findAll();
        List<String> titles = new ArrayList<>();

        for (Project p : projects){
            titles.add(p.getTitle());
        }
        return projects;
    }

    @ResponseBody
    @GetMapping(value = "/getData2")
    public List<Map> getlist2() {
        Project project = projectWebService.findById(1L);
        List<Event> events = eventService.getEventsByProject(project);
        List<Map> data = new ArrayList<>();
        Map<String, Integer> auxiliar = new HashMap<>();
        for (Event e : events){
            String user = e.getUser().getUsername();
            if(auxiliar.containsKey(user)){
                int counter = auxiliar.get(user) + 1;
                auxiliar.put(user, counter);
            }else{
                auxiliar.putIfAbsent(user, 1);
            }
        }
        for (String k : auxiliar.keySet()){
            int counter = auxiliar.get(k);
            Map m = new HashMap<>();
            m.put("user", k);
            m.put("count", counter);
            data.add(m);
        }
        return data;
    }

   /* @GetMapping("/detall/{id}")
    public String index(@PathVariable("id") Long id, Model model) {

        Statistics statistics;
        if (id == 0L) {
            statistics = new Statistics();
        } else {
            statistics = statisticsWebService.findById(id);
        }
        model.addAttribute("statistics", statistics);
        model = getModelForm(statistics, model);
        return "secure/statistics/detall";
    }*/

    /*public Model getModelForm(Statistics statistics, Model model) {


        return model;
    }
*/
    /*@PostMapping("/detall/{id}")
    public String guardarStatistics(@Valid Statistics statistics, BindingResult result, Model model, RedirectAttributes redirecttributes, HttpServletRequest request, HttpSession session) {



        if (result.hasErrors()) {
            model = this.getModelForm(statistics, model);
            redirecttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_GUARDAR);
            return "secure/statistics/detall";
        }
        statisticsWebService.saveStatistics(statistics);

        redirecttributes.addFlashAttribute(Constants.MESSAGE_SUCCESS, Constants.GUARDAT_CORRECTAMENT);
        return "redirect:/secure/statistics/detall/" + statistics.getId();
    }*/

    /*@GetMapping("/delete/{id}")
    public String deleteStatistics(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        Statistics statistics = statisticsWebService.findById(id);

        if (statistics != null) {

            boolean canBeDeleted = true; //TODO: fer per normativa

            if (canBeDeleted) {

                boolean resultat = statisticsWebService.deleteStatistics(id);

                if (resultat) {
                    redirectAttributes.addFlashAttribute(Constants.MESSAGE_SUCCESS, Constants.ELIMINAT_CORRECTAMENT);
                } else {
                    redirectAttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_ELIMINAR);
                }
            } else {
                redirectAttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_REGISTRES_ASSOCIATS);
            }
        } else {
            redirectAttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_ELIMINAR);
        }

        return "redirect:/secure/statistics";
    }*/
    



}
