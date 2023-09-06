package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 

@Configuration // 빈등록(IoC 관리)
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다. 시큐리티 필터 추가 = 스프링 시큐리티가 활성화 되어있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean // IoC가 되요 함수가 리턴하는 값을 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	// csrf 토큰 비활성화(테스트 시 걸어두는 게 좋음)
			.authorizeRequests() 	// 요청이 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")// /auth/**로 
				.permitAll()
				.anyRequest()		// 아닌 모든 요청은
				.authenticated() 	// 인증이 되야한다.
			.and()	//인증이 필요한 페이지로 요청이 오면
				.formLogin()	
				.loginPage("/auth/loginForm");	// customizing한 페이지로 간다.
	}
}
