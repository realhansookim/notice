package com.realhansookim.notice_board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry){
    //모든 매핑경로에 대해 
    //모든 사용자에 대해
    //get post put delete patch option
    //모든 메서드를 허용한다.
    registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
  }
}
