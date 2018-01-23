package com.packt.util;

import com.packt.Interceptor.PerformanceMonitorInterceptor;
import com.packt.domain.Product;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    /*
     * Configure ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.TEXT_HTML);
    }


    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(jsonViewResolver());
        resolvers.add(jspViewResolver());
        return resolver;
    }
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10240000);
        return resolver;
    }


    @Override
    public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer){

        configurer.enable();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper pathHelper = new UrlPathHelper();
        //Enable matrix variable
        pathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(pathHelper);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/classes/");
    }

    @Bean
    PerformanceMonitorInterceptor localInterceptor() {
        return new PerformanceMonitorInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor());
    }

}
