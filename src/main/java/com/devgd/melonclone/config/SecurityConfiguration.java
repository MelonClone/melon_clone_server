package com.devgd.melonclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		//Allow access to all static resources without authentication
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	/**
	 * Define the security that applies to the proxy
	*/
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/produce/**").hasRole("ADMIN")
			.antMatchers("/artist/**").hasRole("MEMBER")
			.antMatchers("/playlist/**").hasRole("MEMBER")
			.antMatchers("/album/**").hasRole("MEMBER")
			.antMatchers("/music/**").hasRole("MEMBER")
			.antMatchers("/search/**").permitAll()
			.antMatchers("/user/**").permitAll()
		.and().formLogin()
		.and().logout()
		.and().csrf().disable();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin")
				// .password("admin123")
				.password(passwordEncoder().encode("admin123"))
				.roles("ADMIN")
				// .authorities("ACCESS_TEST1", "ACCESS_TEST2")
			.and()
				.withUser("user")
				.password(passwordEncoder().encode("user123"))
				.roles("USER")
			.and()
				.withUser("manager")
				.password(passwordEncoder().encode("manager123"))
				.roles("MANAGER").authorities("ACCESS_TEST1");
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}