package toystore.controller;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import toystore.model.Toy;
import toystore.service.ToyService;
import toystore.service.UserService;

import static toystore.helper.Helper.getHeaderName;

@Controller
@RequestMapping("toys")
public class ToysController {

    private final UserService userService;
    private final ToyService toyService;

    public ToysController(UserService userService, ToyService toyService) {
        this.userService = userService;
        this.toyService = toyService;
    }

    @GetMapping
    public String renderToysList(Model model, Authentication authentication){
        model.addAttribute("title", "Toys");
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("toys", toyService.findAll());

        return "toys/toys";
    }

    @GetMapping("create")
    public String renderCreateToyForm(Model model, Authentication authentication) {
        model.addAttribute("title", "Add Toy");
        model.addAttribute(new Toy());
        model.addAttribute("headerName", getHeaderName(authentication, userService));

        return "toys/create_toy";
    }

    @GetMapping("details")
    public String showToyDetails(@RequestParam(required = false) Long toyId, Model model,
                                 Authentication authentication){
        if(toyId == null){
            return "redirect:";
        } else {
            Toy toy = toyService.findById(toyId);

            model.addAttribute("title", "Toy: " + toy.getName());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute(toy);

            return "toys/toy_details";
        }
    }

    @PostMapping("create/save")
    public String addToy(@Valid @ModelAttribute Toy toy, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add toy");

            return "toys/create";
        }

        toyService.saveToy(toy);

        return "redirect:/toys";
    }

    @GetMapping("show")
    public String renderToysForCustomer(Model model, Authentication authentication){
        model.addAttribute("title", "Available toys");
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("toys", toyService.findAll());

        return "toys/customer_toys";
    }

    @GetMapping("delete")
    public String renderDeleteForm(Model model, Authentication authentication){
        model.addAttribute("title", "Delete Toy");
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("toys", toyService.findAll());

        return "toys/delete_toy";
    }

    @PostMapping("delete/submit")
    public String deleteToys(@RequestParam(required = false) int[] toyIds){
        if(toyIds != null){
            for(long id : toyIds){
                toyService.deleteToy(id);
            }
        }

        return "redirect:/toys/delete";
    }

    @PostMapping("update")
    public String updateToy(@Valid @ModelAttribute Toy toy, @RequestParam(required = false) long toyId, Model model, Errors errors, Authentication authentication){
        if(errors.hasErrors()){
            model.addAttribute("title", "Toy: " + toy.getName());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute(toy);

            return "toys/toy_details";
        }

        Toy dbToy = toyService.findById(toyId);
        dbToy.setQuantity(toy.getQuantity());
        dbToy.setName(toy.getName());
        dbToy.setDescription(toy.getDescription());
        toyService.saveToy(dbToy);

        return "redirect:/toys";
    }
}
