package com.maryanto.dimas.example.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
//        template.setMessageConverters(
//                Arrays.asList(
//                        new org.springframework.http.converter.HttpMessageConverter<?>[]{
//                                new ByteArrayHttpMessageConverter(),
//                                new StringHttpMessageConverter(),
//                                new ResourceHttpMessageConverter(),
//                                new SourceHttpMessageConverter()
//                        }
//                )
//        );
        return template;
    }

}
