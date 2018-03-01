package com.movekeep.api.movekeepapi.config;

import com.movekeep.api.movekeepapi.interceptor.CheckAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.OneToMany;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CheckAuth authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor).excludePathPatterns("/getCategories", "/getRoutinesByCategory/*");
        super.addInterceptors(registry);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/tmp/users/");
    }
}
