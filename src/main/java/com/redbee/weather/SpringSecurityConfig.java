package com.redbee.weather;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.redbee.weather.filter.JWTAuthenticationFilter;
import com.redbee.weather.filter.JWTAuthorizationFilter;
import com.redbee.weather.service.JWTService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.cors()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.inMemoryAuthentication()
        .withUser("jetchart").password("$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG").roles("USER", "ADMIN")
        .and()
        .passwordEncoder(passwordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//	    return source;
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
   }

}
