package com.pzd.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pzd.service.UserService;
import com.pzd.serviceImpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class SecurityConfiguration {

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private CustomSessionAuthenticationStrategy customSessionAuthenticationStrategy;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider()).authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/fonts/**", "/gif/**", "/images/**", "/scss/**").permitAll()
				.antMatchers("/login", "/logout","/getRegistrationPage" , "/registration").permitAll()
				.antMatchers("/user/**").authenticated()
				.antMatchers("/cart/**").authenticated()
				.antMatchers("/home").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/Dologin").usernameParameter("email").defaultSuccessUrl("/home", true)
				.and()
				.oauth2Login().loginPage("/login").userInfoEndpoint().userService(userServiceImpl)
				.and()
//				.deleteCookies("JSESSIONID")
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						CustomUser oauthUser = (CustomUser) authentication.getPrincipal();
						
						userService.processOAuthPostLogin(oauthUser, authentication);

						/*
						 * below code will used to get the conatct list saved int he people's email
						 * OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
						 * authentication; String providerName =
						 * oauthToken.getAuthorizedClientRegistrationId(); OAuth2AuthorizedClient
						 * authorizedClient =
						 * authorizedClientRepository.loadAuthorizedClient(providerName,
						 * oauthToken,request); String accessToken =
						 * authorizedClient.getAccessToken().getTokenValue();
						 * 
						 * // Fetch the user's contacts from the Google Contacts API using the access
						 * token RestTemplate restTemplate = new RestTemplate(); HttpHeaders headers =
						 * new HttpHeaders(); headers.setBearerAuth(accessToken); HttpEntity<String>
						 * entity = new HttpEntity<>(headers); ResponseEntity<String> responseEntity =
						 * restTemplate.exchange(
						 * "https://people.googleapis.com/v1/people/me/connections?personFields=names,emailAddresses,phoneNumbers",
						 * HttpMethod.GET, entity, String.class); String contactsJson =
						 * responseEntity.getBody();
						 */
						response.sendRedirect("/home");
					}
				})
				
				.and()
				.sessionManagement()
                .sessionAuthenticationStrategy(customSessionAuthenticationStrategy)
                .and().csrf().disable();
		
//		http
//        .sessionManagement()
//        .sessionFixation().migrateSession()
//        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//        .invalidSessionUrl("/login?expired")
//        .maximumSessions(1)
//            .sessionRegistry(sessionRegistry())
//            .and()
//        .and()
//        .exceptionHandling()
//        .accessDeniedPage("/403");
		
		return http.build();
	}
	 @Bean
	    public SessionRegistry sessionRegistry() {
	        return new SessionRegistryImpl();
	    }
//	 @Bean
//	    public OAuth2UserService<OAuth2UserRequest, OAuth2User> googleOAuth2UserService() {
//	        return new GoogleOAuth2UserService();
//	    }

	@Bean
	public UserDetailsService getUserDeatilsservice() {
		return userServiceImpl;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
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
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
