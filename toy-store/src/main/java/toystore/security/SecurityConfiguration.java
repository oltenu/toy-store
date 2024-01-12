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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/orders/place").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                .requestMatchers("/orders/place/submit").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/create/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/details/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/update").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys/show").hasAnyRole("CUSTOMER")
                .requestMatchers("/toys/delete/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/toys").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/users/create/**").hasRole("ADMIN")
                .requestMatchers("/users/delete/**").hasRole("ADMIN")
                .requestMatchers("/users/update").hasRole("ADMIN")
                .requestMatchers("/users/details/**").hasRole("ADMIN")
                .requestMatchers("/users").hasRole("ADMIN")
        ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
        ).logout(
                logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
        );;

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
