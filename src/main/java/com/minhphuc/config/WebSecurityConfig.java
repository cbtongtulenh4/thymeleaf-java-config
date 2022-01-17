package com.minhphuc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// this class supported by spring-security-config


// EnableWebSecurity will ensure our CSRF Token is included in our forms automatically
// when using Thymeleaf2.1 + or Spring MVC taglibs...
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    // AuthenticationManagerBuilder is a SecurityBuilder used to create an AuthenticationManager.
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // inMemoryAuthentication() method return a InMemoryUserDetailsManagerConfigurer
        // to allow customization of the in memory authentication
        auth.inMemoryAuthentication().passwordEncoder(encoder())
                .withUser("user").password(encoder().encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(encoder().encode("123456")).roles("ADMIN", "USER");
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/home").permitAll()
                .antMatchers("/admin").access("hasRole('ADMIN')")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
            .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?incorrectAccount")
            .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
            .permitAll()
                .and().httpBasic();
//            .sessionManagement()
//                .invalidSessionUrl("/login?sessionTimeout")
//                .maximumSessions(1);
        // @formatter:on
    }


    // bean
    @Bean
    public DaoAuthenticationProvider authProvider(){
        return null;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(11);
    }

}
