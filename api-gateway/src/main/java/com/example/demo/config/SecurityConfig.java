package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import com.netflix.loadbalancer.NoOpPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/sign-in", "/user/sign-up").permitAll()
                .antMatchers(HttpMethod.POST,"/rating/dodaj-rating","/strip/noviStrip","/strip/sviPoId","/katalog/novi").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/rating/**", "/strip/svi","/strip","/strip/trazi-autor","/strip/trazi-zanr",
                        "/strip/trazi-izdavac","/strip/trazi-naziv","/katalog/**","/zanr/svi","/izdavac/svi","/autor/svi").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/katalog/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/katalog/dodavanje-stripa").hasRole("USER")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
