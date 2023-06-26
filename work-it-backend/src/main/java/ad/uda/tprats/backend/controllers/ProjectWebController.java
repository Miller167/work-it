package ad.uda.tprats.backend.controllers;
import ad.uda.tprats.backend.services.ProjectWebService;
import ad.uda.tprats.common.Constants;
import ad.uda.tprats.workitdata.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/secure/project")
public class ProjectWebController {

    @Autowired
    private ProjectWebService projectWebService;
    
    @GetMapping("")
    public ModelAndView index(Model model) {
        return new ModelAndView("secure/project/list");
    }

    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public @ResponseBody
    DataTablesOutput<Project> getlist(@Valid DataTablesInput input) {
        return projectWebService.findAll(input);

    }

    @GetMapping("/detall/{id}")
    public String index(@PathVariable("id") Long id, Model model) {

        Project project;
        if (id == 0L) {
            project = new Project();
        } else {
            project = projectWebService.findById(id);
        }
        model.addAttribute("project", project);
        model = getModelForm(project, model);
        return "secure/project/detall";
    }

    public Model getModelForm(Project project, Model model) {


        return model;
    }

    @PostMapping("/detall/{id}")
    public String guardarProject(@Valid Project project, BindingResult result, Model model, RedirectAttributes redirecttributes, HttpServletRequest request, HttpSession session) {



        if (result.hasErrors()) {
            model = this.getModelForm(project, model);
            redirecttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_GUARDAR);
            return "secure/project/detall";
        }
        projectWebService.saveProject(project);

        redirecttributes.addFlashAttribute(Constants.MESSAGE_SUCCESS, Constants.GUARDAT_CORRECTAMENT);
        return "redirect:/secure/project/detall/" + project.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        Project project = projectWebService.findById(id);

        if (project != null) {

            boolean canBeDeleted = true; //TODO: fer per normativa

            if (canBeDeleted) {

                boolean resultat = projectWebService.deleteProject(id);

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

        return "redirect:/secure/project";
    }
    



}
