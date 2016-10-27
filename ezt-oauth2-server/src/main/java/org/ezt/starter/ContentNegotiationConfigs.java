package org.ezt.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Created by wangwr on 2016.3.22.
 */
@Configuration
public class ContentNegotiationConfigs {

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        ContentNegotiationManagerFactoryBean managerFactoryBean = new ContentNegotiationManagerFactoryBean();
        managerFactoryBean.setDefaultContentType(MediaType.APPLICATION_JSON_UTF8);
        managerFactoryBean.setFavorPathExtension(Boolean.FALSE);
        resolver.setContentNegotiationManager(managerFactoryBean.getObject());
        return resolver;
    }
}
