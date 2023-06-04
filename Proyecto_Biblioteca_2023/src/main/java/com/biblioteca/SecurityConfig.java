






package com.biblioteca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.biblioteca.security.Security;




//archivo de ocnfiguracion
@Configuration
//habilitar seguridad

@EnableWebSecurity
//habilitar metodos para validar clave
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()'
			)
			.formLogin((form) -> form
				.loginPage("/validar/usuario")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());*/
		
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/validar/**","/resources/js/**","/resources/css/**","resources/img/**",
				"/resources/datepicker/**","/resources/**alertifyjs/**","/RegUsuario/**").
		permitAll().and().authorizeHttpRequests().requestMatchers("/CrudUsuario/**","/libro/**").authenticated().and().
		formLogin().loginPage("/validar/usuario").defaultSuccessUrl("/validar/intranet");
		
		

		return http.build();
	}

	
	@Bean
	public UserDetailsService userDetailsService() {
		/*UserDetails usuario1 = User.withUsername("sebastian").password("{noop}123").roles("ADMIN").build();
		UserDetails usuario2 = User.withUsername("alicia").password("{noop}456").roles("USER").build();	*/

		return  new Security();
	}
	
	@Bean
	public AuthenticationProvider AuthenticationProvider(){
		
		DaoAuthenticationProvider dao= new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(password());
		
		
		return dao;
	}
	
	
	@Bean
	public BCryptPasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
}
