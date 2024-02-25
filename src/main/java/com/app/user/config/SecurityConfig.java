package com.app.user.config;

import com.app.user.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for defining authentication and authorization settings.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Bean for providing the UserDetailsService implementation.
     *
     * @return The UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    /**
     * Bean for providing the password encoder.
     *
     * @return The BCryptPasswordEncoder.
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for providing the authentication provider using DaoAuthenticationProvider.
     *
     * @return The configured DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean for providing the AuthenticationManager.
     *
     * @param config The AuthenticationConfiguration.
     * @return The configured AuthenticationManager.
     * @throws Exception If an error occurs while configuring the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean for configuring the SecurityFilterChain.
     *
     * @param http The HttpSecurity to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring the SecurityFilterChain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // Example: Permit all requests for now, you may want to configure more specific rules
                .requestMatchers("/**")
                .permitAll()
                .and()
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
