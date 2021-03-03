package com.tuyano.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tuyano.springboot.service.AccountService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountService accountService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().
			loginPage("/login").
			defaultSuccessUrl("/event/list").
			failureUrl("/login").
			usernameParameter("username").
			passwordParameter("password");
		
		http.authorizeRequests().
			antMatchers("/event/**").hasRole("USER").
			antMatchers("/entry/**").hasRole("USER").
			antMatchers("/**").permitAll();
		
		http.logout().
			logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
			logoutUrl("/login?logout").permitAll();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService).
			passwordEncoder(passwordEncoder());
	}
}
