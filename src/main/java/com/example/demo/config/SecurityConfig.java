
// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                     "/",
//                     "/**",
//                     "/swagger-ui/**",
//                     "/swagger-ui.html",
//                     "/v3/api-docs/**",
//                     "/auth/**",
//                     "/skills/**",
//                     "/student-profiles/**",
//                     "/assessments/**",
//                     "/recommendations/**",
//                     "/h2-console/**"
//                 ).permitAll()
//                 .anyRequest().permitAll()
//             )
//             .httpBasic(basic -> basic.disable())
//             .formLogin(form -> form.disable())
//             .headers(headers -> headers.frameOptions().disable());

//         return http.build();
//     }
// }



package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers(
                        "/api/auth/register",
                        "/api/auth/login",
                        "/api/auth/users",
                        "/api/auth/user/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**"
                ).permitAll()

                // Protected APIs
                .requestMatchers("/api/auth/deactivate/**").authenticated()

                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}