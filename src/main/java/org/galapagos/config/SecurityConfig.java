package org.galapagos.config;

import javax.sql.DataSource;

import org.galapagos.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource; // RootConfig에 있는 히카리 
	
	@Bean
	public PasswordEncoder passwordEncoder() { // 이거 설정되면 비밀번호 암호화 된거 아니면 작동 안함
		return new BCryptPasswordEncoder();
		}
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.authorizeRequests()
			 .antMatchers("/security/profile").authenticated();
		
		http.formLogin()
			.loginPage("/security/login?error=login_required") // 로그인 안했으면 이 url로 리다이렉트 해줌, ?error=login_required < 여기로 온 이유가 로그인이 안되어서 그렇다는 뜻
			.loginProcessingUrl("/security/login") // form에 제시할 url
			.defaultSuccessUrl("/")
			.failureUrl("/security/login?error=true"); // el : param.error, //로그인 실패할 경우 리다이렉트하는 url
	
		http.logout()							// 로그아웃 설정 시작
			.logoutUrl("/security/logout") // POST: 로그아웃 호출 url
			.invalidateHttpSession(true) // 세션 invalidate
			.deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
			.logoutSuccessUrl("/"); // 로그아웃 이후 이동할 페이지, 보통 /로 이동함, redirect url(get)
		
		http.rememberMe()
			.key("Galapagos")
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(7*24*60*60); // 7일
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(datasource);
		
		return repo;

	}
	
		
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//				.withUser("admin")
//				.password("$2a$10$tAIRnt9PK088WQ.ouPVsWuEVsTYJ9WRjg6/HtJ./Ylp71uYYVjyje")
//				.roles("ADMIN");
//		
//		auth.inMemoryAuthentication()
//				.withUser("member")
////				.password("{noop}1234")
//				.password("$2a$10$tAIRnt9PK088WQ.ouPVsWuEVsTYJ9WRjg6/HtJ./Ylp71uYYVjyje")
//				.roles("MEMBER");  // 밑에 auth 설정으로 대체
		
		auth
				.userDetailsService(customUserService())
				.passwordEncoder(passwordEncoder());

	} 
	

}
