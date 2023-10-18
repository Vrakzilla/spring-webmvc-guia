package com.cibertec.edu.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
	public static BCryptPasswordEncoder encriptadorPassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder passEncoder = encriptadorPassword();
		UserBuilder usuarios = User.builder().passwordEncoder(pass -> {
			return passEncoder.encode(pass);
		});
		
		builder.inMemoryAuthentication()
		.withUser(usuarios.username("administrator").password("123456").roles("ADMIN", "USER"))
		.withUser(usuarios.username("jose").password("123465").roles("USER"));
	}

}
