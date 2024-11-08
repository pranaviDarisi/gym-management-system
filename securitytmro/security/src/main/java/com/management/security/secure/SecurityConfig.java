package com.management.security.secure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.management.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/register", "/register/**","/admin/register/**", "/logurl", "/login","/WEB-INF/jsp/**").permitAll()  // Allow all access to these endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN")  
                .requestMatchers("/user/**").hasAnyRole("USER")  
                .anyRequest().permitAll()  
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") 
                .loginProcessingUrl("/logurl") 
                .defaultSuccessUrl("/home", true) 
                .permitAll()
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
            	    .accessDeniedPage("/deniedAccess") 
            	)

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true") 
                .permitAll()
            )
            .userDetailsService(service);
   

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}





