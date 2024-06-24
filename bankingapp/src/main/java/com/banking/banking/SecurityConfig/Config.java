package com.banking.banking.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailService();
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }






    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/user/update/{email}").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/user/user/deleteUser/{email}").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/user/getAllUser").hasAuthority("admin")
                .and()
                .authorizeHttpRequests().requestMatchers("/account/createAccount/{email}").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/account/customer/**").hasAuthority("customer")
                .and()
                .authorizeHttpRequests().requestMatchers("/account/getAccount/{email}").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("account/getBankAccountfromBank/*").hasAuthority("admin")
                .anyRequest().permitAll()

                .and()
                .httpBasic();
        httpSecurity.cors().disable();
        return httpSecurity.build();
    }
}
