package br.com.fiap.myflights.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AuthorizationFilter authorizationFilter;

    @Autowired
    Environment env;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST,"/myflights/api/user/cadastrar").permitAll()
                    .requestMatchers(HttpMethod.POST,"/myflights/api/user/login").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                    //.anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            http.authorizeHttpRequests().anyRequest().permitAll();
        }else{
            http.authorizeHttpRequests().anyRequest().authenticated();
        }

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cofig) throws Exception{
        return cofig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
