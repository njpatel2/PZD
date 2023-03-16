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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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
        .antMatchers("/css/**", "/js/**","/fonts/**","/gif/**","/images/**","/scss/**").permitAll()
            .antMatchers("/home").permitAll()
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/index").authenticated()
            .antMatchers("/**").hasRole("ADMIN")
            .and()
        .formLogin()
            .loginPage("/home")
            .loginProcessingUrl("/Dologin")
            .defaultSuccessUrl("/index",true)
            .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/home")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        .csrf().disable();
    	
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
