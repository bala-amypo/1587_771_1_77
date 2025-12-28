
// package com.example.demo.config;

// import org.springframework.context.annotation.*;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**", "/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                 .anyRequest().authenticated()
//             );
//         return http.build();
//     }
// }


//  2 package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(Customizer.withDefaults())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                         "/auth/**",
//                         "/users/**",
//                         "/swagger-ui/**",
//                         "/v3/api-docs/**",
//                         "/swagger-ui.html"
//                 ).permitAll()
//                 .anyRequest().authenticated()
//             )
//             .httpBasic(Customizer.withDefaults());

//         return http.build();
//     }
// }


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/auth/**",
                    "/skills/**",
                    "/student-profiles/**",
                    "/assessments/**",
                    "/recommendations/**",
                    "/h2-console/**"
                ).permitAll()
                .anyRequest().permitAll()
            )
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())
            .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}
