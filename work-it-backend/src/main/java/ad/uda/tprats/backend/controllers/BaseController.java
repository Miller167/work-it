package ad.uda.tprats.backend.controllers;

import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController {

    protected static final String MESSAGE_ERROR = "messageError";
    protected static final String MESSAGE_WARNING = "messageWarning";
    protected static final String MESSAGE_SUCCESS = "messageSuccess";

}
