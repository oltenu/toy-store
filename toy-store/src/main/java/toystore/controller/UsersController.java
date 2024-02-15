package toystore.controller;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import toystore.model.Role;
import toystore.model.Toy;
import toystore.model.User;
import toystore.service.RoleService;
import toystore.service.UserService;

import java.util.List;

import static toystore.helper.Helper.getHeaderName;

@Controller
@RequestMapping("users")
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;

    public UsersController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String renderUsersList(Model model, Authentication authentication){
        model.addAttribute("title", "Users");
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("users", userService.findAllUsers());

        return "users/users";
    }

    @GetMapping("details")
    public String showUserDetails(@RequestParam(required = false) Long userId, Model model, Authentication authentication){
        if(userId == null){
            return "redirect:/users";
        } else {
            User user = userService.findById(userId);

            model.addAttribute("title", "User: " + user.getFirstName());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute(user);
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);

            return "users/user_details";
        }
    }

    @PostMapping("update")
    public String updateUser(@Valid @ModelAttribute User user, @RequestParam(required = false) long userId, Model model,
                             Errors errors, Authentication authentication){
        /*if(errors.hasErrors()){
            model.addAttribute("title", "Toy: " + user.getFirstName());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute(user);
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);

            return "users/user_details";
        }*/

        /*User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null){
            model.addAttribute("title", "Toy: " + user.getFirstName());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute(user);
            model.addAttribute("errors", "There is already an account with the same email!");
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);

            return "users/user_details";
        }*/

        User dbUser = userService.findById(userId);
        dbUser.setEmail(user.getEmail());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setRoles(user.getRoles());
        dbUser.setPassword(user.getPassword());
        userService.saveUser(dbUser);

        return "redirect:/users";
    }

    @GetMapping("create")
    public String renderCreateUserForm(Model model, Authentication authentication){
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);

        return "users/create_user";
    }

    @PostMapping("create/save")
    public String adduser(@Valid @ModelAttribute User user, Model model, Errors errors, Authentication authentication){
        /*if(errors.hasErrors()){
            model.addAttribute("title", "Add user");
            model.addAttribute(user);
            model.addAttribute("headerName", getHeaderName(authentication, userService));

            return "toys/create_user";
        }*/

        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("delete")
    public String renderDeleteForm(Model model, Authentication authentication){
        model.addAttribute("title", "Delete Users");
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("users", userService.findAllUsers());

        return "users/delete_user";
    }

    @PostMapping("delete/submit")
    public String deleteUsers(@RequestParam(required = false) long[] userIds){
        if(userIds != null){
            for(long id : userIds){
                userService.deleteUser(id);
            }
        }

        return "redirect:/users/delete";
    }
}
