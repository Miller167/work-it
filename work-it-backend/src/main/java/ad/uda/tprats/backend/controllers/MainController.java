package ad.uda.tprats.workit.backend.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class MainController extends BaseController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping({"/login"})
    public String loginUser(Model model, Locale loc) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
            return "redirect:/";
        return "login";
    }

}