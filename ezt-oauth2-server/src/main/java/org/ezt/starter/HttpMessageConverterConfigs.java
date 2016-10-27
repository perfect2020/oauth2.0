package org.ezt.starter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.base.runtime.HttpServiceDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.text.DateFormat;

/**
 * Created by wangwr on 2016.3.21.
 */
@Configuration
public class HttpMessageConverterConfigs {

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Autowired
    public DateFormat dateFormat;

    @Bean(name = "dateFormat")
    public DateFormat dateFormatter(){
        return new HttpServiceDateFormat();
    }

    @Bean(name = "jsonObjectMapper")
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper;
    }

    @Bean
    public HttpMessageConverters messageConverterJson(){

        //beannote converter
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setPrettyPrint(Boolean.FALSE);
        jsonMessageConverter.setObjectMapper(jsonObjectMapper);

        //xml converter
        MappingJackson2XmlHttpMessageConverter xmlMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(dateFormat);
        xmlMessageConverter.setPrettyPrint(Boolean.FALSE);
        return new HttpMessageConverters(jsonMessageConverter,xmlMessageConverter);
    }
}
