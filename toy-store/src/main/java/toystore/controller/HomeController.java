package toystore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toystore.service.UserService;

import static toystore.helper.Helper.getHeaderName;


@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, Authentication authentication) {
        model.addAttribute("title", "Toy store");
        model.addAttribute("headerName", getHeaderName(authentication, userService));

        return "index";
    }
}
