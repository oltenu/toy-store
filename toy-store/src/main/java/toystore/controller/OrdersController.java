package toystore.controller;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toystore.model.Order;
import toystore.model.User;
import toystore.service.OrderService;
import toystore.service.ToyService;
import toystore.service.UserService;

import static toystore.helper.Helper.getHeaderName;

@Controller
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;
    private final ToyService toyService;

    public OrdersController(OrderService orderService, UserService userService, ToyService toyService){
        this.orderService = orderService;
        this.userService = userService;
        this.toyService = toyService;
    }

    @GetMapping("place")
    public String renderPlaceOrderForm(Model model, Authentication authentication){
        model.addAttribute("title", "Place Order");
        model.addAttribute(new Order());
        model.addAttribute("headerName", getHeaderName(authentication, userService));
        model.addAttribute("toys", toyService.findAll());

        return "orders/place_order";
    }

    @PostMapping("place/submit")
    public String placeOrder(@Valid @ModelAttribute Order order, Model model, Errors errors, Authentication authentication){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Place Order");
            model.addAttribute(new Order());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute("toys", toyService.findAll());


            return "orders/place_order";
        }

        User user = userService.findUserByEmail(authentication.getName());
        order.setCustomer(user);

        boolean result = orderService.placeOrder(order);

        if(!result){
            model.addAttribute("title", "Place Order");
            model.addAttribute(new Order());
            model.addAttribute("headerName", getHeaderName(authentication, userService));
            model.addAttribute("errors", "Invalid quantity!");
            model.addAttribute("toys", toyService.findAll());


            return "orders/place_order";
        }

        return "redirect:/";
    }
}
