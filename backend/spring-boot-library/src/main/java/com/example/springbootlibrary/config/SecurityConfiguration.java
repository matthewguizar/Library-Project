package com.example.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //disable cross site request forgery
        http.csrf().disable();

        //protect endpoints at /api/<type>/secure
        http.authorizeHttpRequests(configurer ->
                configurer
                        .antMatchers ("/api/books/secure/**")
                        .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);


        //add cors
        http.cors();

        //add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());
        http.oauth2Login();

        //force a non-empty response body for 401's
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
}
