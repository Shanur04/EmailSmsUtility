package gov.cdac.emailservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import gov.cdac.emailservice.filter.RequestBodyReader;
import gov.cdac.emailservice.services.UserDetailServiceForAdmin;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Configuration
	@Order(1)
	public  class ConfigurationSeatAllocation extends WebSecurityConfigurerAdapter {

	        public ConfigurationSeatAllocation() {
	            super();
	        }
	        
	        @Bean
	    	public UserDetailsService userDetailsServiceAdmin() {
	    		return new UserDetailServiceForAdmin();
	    	}

	        @Override
	    	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    		auth.userDetailsService(userDetailsServiceAdmin())
	    		.passwordEncoder(passwordEncoder());
	    	}
	        
	        @Override
	    	protected void configure(HttpSecurity http) throws Exception {
	    		http.headers().frameOptions().sameOrigin();
	    		 http
		          .authorizeRequests()
		          .antMatchers("/emailLogin", "/emailRegister", "/registerNewSeatAllocator").permitAll()
		          .anyRequest()
		          .authenticated()

		          .and()
		          .formLogin()
		          .loginPage("/emailLogin")
		          .failureUrl("/emailLogin?error=true")
		          .defaultSuccessUrl("/icg/viewConfigureBulkEmail")
		          
		          .and()
		          .logout()
		          .logoutUrl("/emailLogout")
		          .logoutSuccessUrl("/emailLogin")
		          
		          .and()
		          .exceptionHandling()
		          .accessDeniedPage("/403")
		          
		          .and()
		          .csrf().disable();
		          
	    		http.addFilterBefore(authenticationFilterAdmin(), UsernamePasswordAuthenticationFilter.class);
	 
	    	}
	        
	        @Override
	    	public void configure(WebSecurity web) throws Exception {
	    		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**");
	    	}
	        
	        @Bean 
	    	public CaptchaAuthenticationFilter captchaAuthenticationFilterAdmin() throws Exception { 
	    		  CaptchaAuthenticationFilter filter = new CaptchaAuthenticationFilter("/emailLogin");
	    		  filter.setAuthenticationManager(authenticationManagerBean()); 
	    		  return filter;
	    	  }
	    	 
	    	 @Bean
	    		public RequestBodyReader authenticationFilterAdmin() throws Exception {
	    			RequestBodyReader authFilter = new RequestBodyReader();
	    			authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/emailLogin", "POST"));
	    			authFilter.setAuthenticationManager(authenticationManager());
	    			authFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/icg/viewConfigureBulkEmail"));
	    			authFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/emailLogin?error=true"));	    			
	    			authFilter.setUsernameParameter("username");
	    			authFilter.setPasswordParameter("password");
	    			return authFilter;
	    		}
	    }
}



