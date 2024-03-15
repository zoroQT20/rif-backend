package com.rif.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/api/**") // Apply CORS to all endpoints under /api
                            .allowedOrigins("http://localhost:5173", "http://localhost:8080") // Allow requests from these origins
                            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified HTTP methods
                            .allowCredentials(true); // Allow sending cookies
                }
            };
        }
}
