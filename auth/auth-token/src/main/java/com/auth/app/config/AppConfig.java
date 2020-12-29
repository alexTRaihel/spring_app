package com.auth.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class AppConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000") // any host or put domain(s) here
                .allowedMethods("GET, POST") // put the http verbs you want allow
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
