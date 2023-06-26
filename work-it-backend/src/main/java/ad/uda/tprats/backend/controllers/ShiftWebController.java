package ad.uda.tprats.backend.controllers;
import ad.uda.tprats.backend.services.ShiftWebService;
import ad.uda.tprats.common.Constants;
import ad.uda.tprats.workitdata.entities.Shift;
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
import java.util.Date;

@Controller
@RequestMapping("/secure/shift")
public class ShiftWebController {

    @Autowired
    private ShiftWebService shiftWebService;
    
    @GetMapping("")
    public ModelAndView index(Model model) {
        return new ModelAndView("secure/shift/list");
    }

    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public @ResponseBody
    DataTablesOutput<Shift> getlist(@Valid DataTablesInput input) {
        return shiftWebService.findAll(input);

    }

    /*@RequestMapping(value = "/getListByDate", method = RequestMethod.GET)
    public @ResponseBody
    DataTablesOutput<Shift> getlistByDate(@Valid DataTablesInput input, Date date) {
        return shiftWebService.findAll(input, date);

    }*/

    @GetMapping("/detall/{id}")
    public String index(@PathVariable("id") Long id, Model model) {

        Shift shift;
        if (id == 0L) {
            shift = new Shift();
        } else {
            shift = shiftWebService.findById(id);
        }
        model.addAttribute("shift", shift);
        model = getModelForm(shift, model);
        return "secure/shift/detall";
    }

    public Model getModelForm(Shift shift, Model model) {


        return model;
    }

    @PostMapping("/detall/{id}")
    public String guardarShift(@Valid Shift shift, BindingResult result, Model model, RedirectAttributes redirecttributes, HttpServletRequest request, HttpSession session) {



        if (result.hasErrors()) {
            model = this.getModelForm(shift, model);
            redirecttributes.addFlashAttribute(Constants.MESSAGE_ERROR, Constants.ERROR_GUARDAR);
            return "secure/shift/detall";
        }
        shiftWebService.saveShift(shift);

        redirecttributes.addFlashAttribute(Constants.MESSAGE_SUCCESS, Constants.GUARDAT_CORRECTAMENT);
        return "redirect:/secure/shift/detall/" + shift.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteShift(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        Shift shift = shiftWebService.findById(id);

        if (shift != null) {

            boolean canBeDeleted = true; //TODO: fer per normativa

            if (canBeDeleted) {

                boolean resultat = shiftWebService.deleteShift(id);

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

        return "redirect:/secure/shift";
    }
    



}
