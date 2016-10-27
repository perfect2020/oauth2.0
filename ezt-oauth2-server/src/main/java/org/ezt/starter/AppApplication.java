package org.ezt.starter;

import org.ezt.starter.config.DataBaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by wangwr on 2016.3.18.
 */
@Configuration
@EnableAutoConfiguration
//@EnableAspectJAutoProxy
@ComponentScan({"org.ezt.controller","org.ezt.service","org.ezt.handler"})
@ImportResource({"classpath:spring-context.xml"})
public class AppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebMvcAdapter.class,HttpMessageConverterConfigs.class, ContentNegotiationConfigs.class);
    }

    public static void main(String[] args){
        SpringApplication.run(AppApplication.class,args);
    }

}
