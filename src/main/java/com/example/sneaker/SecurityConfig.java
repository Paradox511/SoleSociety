package com.example.sneaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.sneaker.security.JwtAuthFilter;
import com.example.sneaker.services.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter; // Your JWT filter
    private final MyUserDetailsService userDetailsService; // your userdetails service

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, MyUserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sessions
            .authorizeHttpRequests(authorize -> authorize
            		.requestMatchers("/",
                            "/**",
                            "/api/users/createUser",
                            "/api/users/login",
                            "/api/users/logout",
                            "/api/users/profile",
                            "/api/products/getAll", // Add this line
                            "/api/products/get/**", // Add this line if you have specific product routes
                            "/images/**",
                            "/css/**",
                            "/js/**",
                            "/fragments/**",
                            "/login-signup.html", //if login-signup is in static folder
                            "/user-login" //if login-signup is in template folder
                            ).permitAll()
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )
            .logout(logout -> logout.disable()) // Disable Spring Security's default logout
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before UsernamePasswordAuthenticationFilter
            .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}