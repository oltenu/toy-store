package toystore.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toystore.data.RoleRepository;
import toystore.model.Role;
import toystore.model.User;
import toystore.service.RoleService;
import toystore.service.UserService;

import java.util.Arrays;

@Controller
public class AuthenticationController {

    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("register")
    public String showRegisterForm(Model model){
        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        model.addAttribute("headerName", "home");

        return "register";
    }

    @PostMapping("register/save")
    public String registration(@Valid @ModelAttribute User user, BindingResult result, Model model){
        User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null){
            result.rejectValue("email", null,
                    "There is already an account with the same email!");
        }

        if(result.hasErrors()){
            model.addAttribute(new User());
            model.addAttribute("headerName", "home");
            model.addAttribute("errors", "There is already an account with the same email!");

            return "register";
        }

        Role role = roleService.findByName("ROLE_CUSTOMER");
        user.setRoles(Arrays.asList(role));

        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("headerName", "home");

        return "login";
    }
}
