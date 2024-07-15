package com.example.lms.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        (requests) -> requests.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .requestMatchers("/", "/home", "/api/login").permitAll().anyRequest().authenticated())
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .logout((logout) -> logout.permitAll());

    return http.build();
  }


  @Bean
  UserDetailsManager userDetailsManager() {
    UserDetailsManager user = new CrudUserDetailsManager();
    return user;
  }
}
