package com.taxah.springdz7_2.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/",LOGIN,"/logout").permitAll()
                .antMatchers("/public-data").authenticated()
                .antMatchers("/private-data").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage(LOGIN)
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl(LOGIN)
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage(LOGIN);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCrypt().encode("user"))
                .roles("USER").build());
        manager.createUser(
                User.withUsername("admin")
                .password(bCrypt().encode("admin"))
                .roles("ADMIN").build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCrypt() {
        return new BCryptPasswordEncoder();
    }
}
