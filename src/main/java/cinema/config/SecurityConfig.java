package cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder,
                          UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/available/**",
                        "/orders/**").permitAll()
                .antMatchers(HttpMethod.POST, "/registration/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/shopping-carts/by-user/**",
                        "/users/by-email").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/orders/complete/**",
                        "/shopping-carts/movie-sessions/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
