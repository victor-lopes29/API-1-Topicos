package com.farmlogistic.farmlogistic.security;



import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1) Disable CSRF, 2) Enable CORS and 3) Set session management to stateless
        http = http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .cors(Customizer.withDefaults()) // Enable CORS
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(
                                (request, response, ex) -> {
                                    response.sendError(
                                            HttpServletResponse.SC_UNAUTHORIZED,
                                            ex.getMessage());
                                }));

        // Set permissions on endpoints
        http.authorizeHttpRequests(authorize -> authorize
                 .requestMatchers("/api-docs/**").permitAll()
                 .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                 .requestMatchers(HttpMethod.GET, "/index.html").permitAll()
                // .requestMatchers(HttpMethod.GET, "/").permitAll()
                 .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                 .requestMatchers(HttpMethod.POST, "/person").permitAll()
                 .requestMatchers( "/animals/**").permitAll()
                // .anyRequest().authenticated());
                .anyRequest().permitAll());

        // Add JWT token filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}

