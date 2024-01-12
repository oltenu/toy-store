package toystore.helper;

import org.springframework.security.core.Authentication;
import toystore.model.Role;
import toystore.model.User;
import toystore.service.UserService;

public class Helper {

    public static String getHeaderName(Authentication authentication, UserService userService){
        if (authentication == null) {
            return "home";
        } else {
            String email = authentication.getName();
            User user = userService.findUserByEmail(email);
            Role role = user.getRoles().get(0);

            if (role.getName().equals("ROLE_ADMIN")) {
                return "admin";
            } else if (role.getName().equals("ROLE_EMPLOYEE")) {
                return "employee";
            } else {
                return "customer";
            }
        }
    }
}
