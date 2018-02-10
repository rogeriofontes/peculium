package br.com.rft.peculium.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rft.peculium.interceptions.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/**", "classpath:/public/**" };

	private PasswordEncoder encoder;

	@Autowired
	private UserDetailsService customUserDetailsService;

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().
	 * antMatchers("/register").permitAll()
	 * .antMatchers("/register/save").permitAll().antMatchers("/recover").
	 * permitAll() .antMatchers("/restaurant-types/**").permitAll().antMatchers(
	 * "/restaurants/**").permitAll()
	 * .antMatchers("/plates/**").permitAll().antMatchers("/**").permitAll()
	 * .antMatchers("/register/recover").permitAll().anyRequest().authenticated(
	 * ).and().formLogin()
	 * .loginPage("/login").permitAll().defaultSuccessUrl("/admin").failureUrl(
	 * "/login").and().logout()
	 * .logoutUrl("/logout").deleteCookies("remember-me").logoutSuccessUrl("/").
	 * permitAll().and().rememberMe(); http.httpBasic(); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception { // .antMatchers(HttpMethod.POST,
																	// "/login").permitAll()
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/authenticate").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests() .antMatchers("/").permitAll()
	 * .antMatchers(HttpMethod.POST, "/login").permitAll()
	 * .anyRequest().authenticated() .and() // We filter the api/login requests
	 * .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	 * UsernamePasswordAuthenticationFilter.class) // And filter other requests
	 * to check the presence of JWT in header .addFilterBefore(new
	 * JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); }
	 */

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (encoder == null) {
			encoder = new BCryptPasswordEncoder();
		}

		return encoder;
	}
}