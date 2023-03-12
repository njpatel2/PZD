package com.pzd.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	System.out.println("in web conf::::::::::::::::::::::::::::::::::::::");
    	http
    		.authenticationProvider(authenticationProvider())
    		.authorizeRequests()
    		.antMatchers("/home").permitAll()
    		.antMatchers("/admin/**")
    		.hasRole("ADMIN")
    		.antMatchers("/user/**")
    		.hasRole("USER")
    		.and()
    		.formLogin()
        	.loginPage("/home")
        	.loginProcessingUrl("/Dologin")
        	.defaultSuccessUrl("/successurl")
        	.and()
        	.logout()
        	.permitAll()
        	.and()
        	.csrf().disable();
		
		return http.build();
    }
    
    @Bean
    public UserDetailsService getUserDeatilsservice()
    {
    	return detailsServiceImpl;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(getUserDeatilsservice());
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	
    	return daoAuthenticationProvider;
    }
    
//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth)
//    {
//    	auth.authenticationProvider(authenticationProvider());
//    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception
    {
    	return configuration.getAuthenticationManager();
    }
    
    
    
}
