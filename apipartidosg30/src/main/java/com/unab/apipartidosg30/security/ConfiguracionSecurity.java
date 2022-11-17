package com.unab.apipartidosg30.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.unab.apipartidosg30.services.IUsuarioService;

@EnableWebSecurity
public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter{

    private final IUsuarioService iUsuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public ConfiguracionSecurity(IUsuarioService iUsuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/usuario").permitAll()
        .antMatchers(HttpMethod.GET, "/partido").permitAll()
        .antMatchers(HttpMethod.GET, "/partido/{id}").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(geUsuarioAutenticacion())
        .addFilter(new TokenAutorizacion(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUsuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    public UsuarioAutenticacion geUsuarioAutenticacion() throws Exception{

        final UsuarioAutenticacion usuarioAutenticacion= new UsuarioAutenticacion(authenticationManager());

        usuarioAutenticacion.setFilterProcessesUrl("/usuario/login");

        return usuarioAutenticacion;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration= new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
