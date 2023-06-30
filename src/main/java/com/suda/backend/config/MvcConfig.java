package com.suda.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* spring framework 에서 Bean 에서 하던 설정을 java로 하게 되며 설정을 이 곳에서 한다. */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /* 실행 시, defaultInterceptor를 가져와 주입하여 사용 */
    

    /* 인터셉터 추가하는 메소드 */
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(defaulInterceptor) // 인터셉터 추가
    //             .addPathPatterns("/*.do") // 적용할 패턴 추가
    //             .addPathPatterns("/**/*.do") // 적용할 패턴 추가
    //             .addPathPatterns("/**/**/*.do") // 적용할 패턴 추가
    //             .excludePathPatterns("/js/**", "/css/**"); // 제외할 패턴 추가
    // }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/board/**").setViewName("forward:/");
    }

    /* 정적 리소스 파일 경로 지정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**")
                .addResourceLocations("/static/");
        registry.addResourceHandler("error/**")
                .addResourceLocations("/error/");
    }   

}
