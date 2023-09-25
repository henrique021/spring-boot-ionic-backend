package com.hrgonzaga.cursomc.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private Environment env;
	
	
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/produtos/**",
			"/categorias/**",
			"/clientes/**"
	};
	
	

	 
	 @Bean 
	 public SecurityFilterChain web(HttpSecurity http) throws Exception {
		 
		 if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			 http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
		 }
		 
	  http.cors(cors -> cors.disable()); 
	  http.csrf
	  
	  
	  (csrf -> csrf.disable());
	  
	  http.authorizeHttpRequests((authorize) -> authorize
			  .requestMatchers(antMatcher(HttpMethod.GET,"/produtos/**"))
			  .permitAll()
			  .requestMatchers(antMatcher(HttpMethod.GET,"/categorias/**"))
			  .permitAll()
			  .requestMatchers(antMatcher(HttpMethod.GET,"/clientes/**"))
			  .permitAll()
			  .requestMatchers(antMatcher("/h2-console/**"))
			  .permitAll()
			  .anyRequest()
			  .authenticated());

	  
	  http.sessionManagement((session) -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	          
	  
	  
	  	return http.build();
	  }
	  
	  
	  @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		  final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		  source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		  return source;
	  }
	  
	  
	  @Bean
	  public BCryptPasswordEncoder bCryptPasswordencoder() {
		  return new BCryptPasswordEncoder();
	  }
	

}
