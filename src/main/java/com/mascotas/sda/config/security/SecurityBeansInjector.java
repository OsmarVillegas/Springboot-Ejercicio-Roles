package com.mascotas.sda.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mascotas.sda.persistencia.repository.UsuarioRepository;
import com.mascotas.sda.exception.ObjectNotFoundException;

@Configuration
public class SecurityBeansInjector {

    @Autowired
    private UsuarioRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider autheticationStrategy = new DaoAuthenticationProvider();
        autheticationStrategy.setPasswordEncoder(passwordEncoder());
        autheticationStrategy.setUserDetailsService(userDetailsService());

        return autheticationStrategy;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> { 
            return userRepository.findByUsername(username)
            .orElseThrow(() -> new ObjectNotFoundException("Error: Este usuario no existe: " + username));
        };
    }
}
