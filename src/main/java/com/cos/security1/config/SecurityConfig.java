package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
public class SecurityConfig { 
	
	@Bean //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/user/**").authenticated() //인증만되면 들어갈 수 있는 주소!
				.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //해당 권한이 있는 사람만 접근하게끔 할거야
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") 
				.anyRequest().permitAll() //위 세가지 주소가 아니면 누구나 들어갈 수 있다		
				.and()
				.formLogin()
				.loginPage("/loginForm") // /user, /admin, /manager치면 다 /login으로 가 => 로그인페이지 만들자
				.loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다=>컨트롤러에 /login 안 만들어도 돼
				.defaultSuccessUrl("/");  // /loginForm 페이지로 와서 로그인을 하게되면 내가 /로 보내줄건데 네가 어떤 특정페이지를 요청해서 로그인하게되면 그 페이지를 열어줄게
		return http.build();
	}
}
