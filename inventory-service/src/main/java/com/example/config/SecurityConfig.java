package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("saiman").password("saiman").roles("USER").and().
		withUser("testman").password("testman").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder getPass(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			     .antMatchers("/ims-api/aproduct/**").hasRole("ADMIN")
			     .antMatchers("/ims-api/product/**").hasAnyRole("ADMIN","USER")
			     .antMatchers("/").permitAll()
			     .and()
			     .httpBasic();
		
		http.csrf().disable();
	}
	
	
	
}
