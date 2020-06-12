package com.devgd.melonclone.global.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;
	
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
			.antMatchers("/admin/**").hasRole(Role.ADMIN.name())
			.antMatchers("/produce/**").hasRole(Role.ADMIN.name())
			.antMatchers("/v1/artist/**").hasRole(Role.MEMBER.name())
			.antMatchers("/playlist/**").hasRole(Role.MEMBER.name())
			.antMatchers("/album/**").hasRole(Role.MEMBER.name())
			.antMatchers("/music/**").hasRole(Role.MEMBER.name())
			.antMatchers("/search/**").permitAll()	
			.antMatchers("/user/**").permitAll()
			// .anyRequest().authenticated()
		.and().formLogin().disable()
			.cors().disable()
			.csrf().disable()
		.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		
	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	auth
	// 		.inMemoryAuthentication()
	// 			.withUser("admin")
	// 			// .password("admin123")
	// 			.password(passwordEncoder().encode("admin123"))
	// 			.roles("ADMIN")
	// 			// .authorities("ACCESS_TEST1", "ACCESS_TEST2")
	// 		.and()
	// 			.withUser("user")
	// 			.password(passwordEncoder().encode("user123"))
	// 			.roles("USER")
	// 		.and()
	// 			.withUser("manager")
	// 			.password(passwordEncoder().encode("manager123"))
	// 			.roles("MANAGER").authorities("ACCESS_TEST1");
	// }
	
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}