package ru.bakhuss.library.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		    .authorizeRequests()
			.antMatchers("/**", "/api/person/*")
    				.permitAll()
			.antMatchers("/v2/api-docs", "/configuration/**")
	    			.permitAll()
			.anyRequest().authenticated()
			.and()
		   .formLogin()
			.and()
		  .logout()
		        	.permitAll();
	}


}
