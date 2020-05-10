package com.example.demo.config;

import com.example.demo.filters.JwtRequestFilter;
import com.example.demo.service.CustomUserDetailsService;
import com.netflix.loadbalancer.NoOpPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception{

        // autorizacija
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/","/user/single/**", "/user/naziv-role/**", "/strip/sviPoId").permitAll()
                .antMatchers(HttpMethod.POST,"/user/sign-in", "/user/sign-up", "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST,"/rating/dodaj-rating","/strip/sviPoId","/katalog/novi").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/rating/**","/strip/trazi-autor*","/strip/trazi-zanr*",
                        "/strip/trazi-izdavac*","/strip/trazi-naziv*","/katalog/**","/zanr/svi","/izdavac/svi","/autor/svi").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/katalog/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/katalog/dodavanje-stripa", "/user/update-rating").hasRole("USER")
                .antMatchers("/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow eureka client to be accessed without authentication
        web.ignoring().antMatchers("/*/")
                .antMatchers("/eureka/**")
                .antMatchers("/error")
                .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
