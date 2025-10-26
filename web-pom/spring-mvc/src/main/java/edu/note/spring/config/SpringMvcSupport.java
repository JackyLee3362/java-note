package edu.note.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugin/**").addResourceLocations("/plugin/");
    }

    // 这里注释主要和 WebMvcConfigure 有区别，都可以加拦截器，区别是什么呢？
    // TODO WebMvcConfigurationSupport VS WebMvcConfigure
    // @Autowired
    // private ProjectInterceptor projectInterceptor;
    // @Override
    // protected void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(projectInterceptor).addPathPatterns("/games","/games/", "/games/*"); // 不能写成/games
    // }

}
