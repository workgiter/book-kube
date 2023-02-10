package work.worker.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * choses what to protect with secuirty.
     * @param http
     * @return http secuirty
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http)
    throws Exception {
        //NOTE: disabling csrf makes the app vurable to Cross-site
        //request forgery, a web security vulnerability that allows
        //an attacker to induce users to perform actions that they
        //do not intend to perform
        http
            .cors().and()
            .authorizeHttpRequests()
            .requestMatchers(
                "/books/",
                "/books/list",
                "/books/steal/*",
                "/h2-console/",
                "/",
                "/error",
                "/books/cover/*"
            ).permitAll()
            .and()
            .csrf().disable().headers().frameOptions().disable();
		return http.build();
    }
}
