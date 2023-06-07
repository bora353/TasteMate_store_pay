package com.tastemate;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

/* 파일업로드 관련 */
  private String resourcePath = "/store/**";
  private String savePath = "file:///C:/upload/";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry){
      registry.addResourceHandler(resourcePath)
              .addResourceLocations(savePath);
  }



/* 로그인 관련 추가하기 !!! */


}
