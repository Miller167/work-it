package ad.uda.tprats.backend.controllers;
import ad.uda.tprats.backend.filter.FilterUser;
import ad.uda.tprats.backend.services.UserWebService;
import ad.uda.tprats.common.Constants;
import ad.uda.tprats.workitdata.entities.User;
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
@RequestMapping("/secure/user")
public class UserWebController {

    @Autowired
    private UserWebService userWebService;
    
    @GetMapping("")
    public ModelAndView index(Model model) {
        return new ModelAndView("secure/user/list");
    }

    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public @ResponseBody
    DataTablesOutput<User> getlist(@Valid DataTablesInput input) {
        return userWebService.findAll(input);

    }

    @GetMapping("/detall/{id}")
    public String index(@PathVariable("id") Long id, Model model) {

        User user;
        if (id == 0L) {
            user = new User();
        } else {
            user = userWebService.findById(id);
        }
        model.addAttribute("user", user);
        model = getModelForm(user, model);
        return "secure/user/detall";
    }

    public Model getModelForm(User user, Model model) {


        return model;
    }

    @PostMapping("/detall/{id}")
    public String guardarUser(@Valid User user, BindingResult result, Model model, RedirectAttributes redirecttributes, HttpServletRequest request, HttpSession session) {

        if (result.hasErrors()) {
            model = this.getModelForm(user, model);
            redirecttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_GUARDAR);
            return "secure/user/detall";
        }
        userWebService.saveUser(user);

        redirecttributes.addFlashAttribute(Constants.MESSAGE_SUCCESS, Constants.GUARDAT_CORRECTAMENT);
        return "redirect:/secure/user/detall/" + user.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        User user = userWebService.findById(id);

        if (user != null) {

            boolean canBeDeleted = true; //TODO: fer per normativa

            if (canBeDeleted) {

                boolean resultat = userWebService.deleteUser(id);

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

        return "redirect:/secure/user";
    }
    



}
