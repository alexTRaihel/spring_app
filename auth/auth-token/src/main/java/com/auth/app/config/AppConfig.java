package com.auth.app.config;

import com.auth.utils.jwt.JWTUtil;
import com.auth.utils.model.UserAuthModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.nio.file.Path;
import java.util.function.Function;

@Configuration
public class AppConfig implements WebFluxConfigurer {

    @Value("${jwt.private.key.name}")
    public String privateKeyFileName;

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000") // any host or put domain(s) here
                .allowedMethods("GET, POST") // put the http verbs you want allow
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    private Path getFilePath(String filename) throws Exception{
        return ResourceUtils.getFile("classpath:" + filename).toPath();
    }
}
