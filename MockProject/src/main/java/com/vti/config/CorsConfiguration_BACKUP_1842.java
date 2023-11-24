package com.vti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")	
        		.allowedOriginPatterns("*")
                // .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowedHeaders("*")
                //.allowCredentials(true);
                ;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 05e0f3adf855167a4a3462692b9013eceaea1c7b
