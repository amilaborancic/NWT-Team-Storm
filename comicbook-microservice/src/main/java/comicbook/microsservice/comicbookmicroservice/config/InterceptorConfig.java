package comicbook.microsservice.comicbookmicroservice.config;

import comicbook.microsservice.comicbookmicroservice.Interceptors.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }
}