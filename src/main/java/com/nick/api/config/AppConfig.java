package com.nick.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("rohit.desai@gmail.com").password(passwordEncoder().encode("Rohit@12")).roles("Manager").build();
        UserDetails user2 = User.builder().username("naresh.patro@gmail.com").password(passwordEncoder().encode("Naresh@12")).roles("Manager").build();
        UserDetails user3 = User.builder().username("nick.naitik@gmail.com").password(passwordEncoder().encode("Nick@12")).roles("Developer").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
    	return builder.getAuthenticationManager();
    	
    }
    
}
