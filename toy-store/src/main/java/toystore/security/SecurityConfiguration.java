package toystore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/orders/place-order").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/create-toy").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/customer-toys").hasAnyRole("CUSTOMER")
                .requestMatchers("/toys/delete-toy").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/toy-details").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/users/create-user").hasRole("ADMIN")
                .requestMatchers("/users/delete-user").hasRole("ADMIN")
                .requestMatchers("/users/user-details").hasRole("ADMIN")
                .requestMatchers("/users").hasRole("ADMIN")
        );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
