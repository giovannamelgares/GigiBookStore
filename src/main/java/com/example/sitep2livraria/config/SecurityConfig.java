package com.example.sitep2livraria.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(
            "SELECT email, password, true " + "FROM usuario WHERE email = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
            "SELECT u.email, CONCAT('ROLE_', p.cargo) " + "FROM perfil p " +
            "JOIN usuario u " + "ON p.usuarioid = u.id " + "WHERE u.email = ?"
        );

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) 
        throws Exception {
            return http
                .csrf(csrf -> csrf.disable())
                
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/livros").permitAll()
                    .requestMatchers("/livro/*").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/cadastro-usuario").permitAll()
                    .requestMatchers("/cadastro").hasRole("admin")
                    .requestMatchers("/favoritar/**").authenticated()
                    .anyRequest().authenticated()
            )

                .formLogin(form -> form
                    .loginPage("/login").defaultSuccessUrl("/", true).permitAll()
            )

                .logout(logout -> logout
                    .logoutSuccessUrl("/")
            )

            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
