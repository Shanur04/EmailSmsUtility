package gov.cdac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfigJWT {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	private UrlAuthenticationSuccessHandler urlAuthenticationSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.headers(headers -> headers
	        .frameOptions(frameOptions -> frameOptions.sameOrigin())
	        //.addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self'"))
	        .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'"))
	        .addHeaderWriter(new StaticHeadersWriter("X-Download-Options", "noopen"))
	        .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(31536000)));

	    http.authorizeHttpRequests(authorize -> authorize
	        .requestMatchers("/emailSmsLogin", "/signin", "/resetcaptcha", "/registeruser", "/resources/**",
	            "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/assets/**")
	        .permitAll()
	        .anyRequest().authenticated());

	    // Updated form login configuration: Custom authentication handler
	    http.formLogin(login -> login
	        .loginPage("/emailSmsLogin")
	        .loginProcessingUrl("/emailSmsLogin")  // Form action URL
	        .failureUrl("/emailSmsLogin?error=true")
	        .defaultSuccessUrl("/emailOrSms", true) // Redirect to /emailOrSms after successful login
	        .successHandler(urlAuthenticationSuccessHandler));

	    http.logout(logout -> logout
	        .logoutUrl("/logout")
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID", "jwt")
	        .logoutSuccessUrl("/emailSmsLogin"));

	    http.sessionManagement(session -> session
	        .invalidSessionUrl("/emailSmsLogin")
	        .enableSessionUrlRewriting(false)
	        .sessionFixation().newSession()
	        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        .maximumSessions(1)
	        .expiredUrl("/emailSmsLogin"));

	    // Disable CSRF for development - Enable in production
	    http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            );

	    // Add JWT filter
	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}



	@Bean
	AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

}
