package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	Userservice userDetailsService;
	
	@Autowired
	public  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/test/**").permitAll()
			.antMatchers("/test2/**").hasAuthority("user")			
			.antMatchers("/admin/**").hasAuthority("admin")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll();
		
		
		
	}
	
//	 @Override
//	    public void configure(WebSecurity web) throws Exception {
//	        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**"); 
//	    }
}
