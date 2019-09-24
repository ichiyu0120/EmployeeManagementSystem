package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource dataSource;
	
	private static final String EMP_SQL =
			 "SELECT"
			 + " emp_id,"
			 + " emp_pass,"
			 + " enable "
			 + "FROM"
			 + " emp "
			 + "WHERE"
			 + " emp_id = ?";
	
	private static final String ROLE_SQL =
			 "SELECT"
			 + " emp_id,"
			 + " role "
			 + "FROM"
			 + " emp "
			 + "WHERE"
			 + " emp_id = ?";

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/webjars/**","/css/**","/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//直リンク設定 静的ファイルとindex以外直リンク禁止
		http
			.authorizeRequests()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/index").permitAll()
			.anyRequest().authenticated();
		
		//ログイン処理
		http
			.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.failureUrl("/index")
			.usernameParameter("empId")
			.passwordParameter("empPass")
			.defaultSuccessUrl("/list",true);
		
		//ログアウト処理
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")
			.logoutSuccessUrl("/index");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(EMP_SQL)
		.authoritiesByUsernameQuery(ROLE_SQL)
		.passwordEncoder(passwordEncoder());
		
	}

	
}
