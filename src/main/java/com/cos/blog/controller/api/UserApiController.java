package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User1;
import com.cos.blog.service.UserService;


@RestController // data 만 리턴해줄 것
public class UserApiController {

	@Autowired // DI
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

// 전통적인 로그인 방법
//	@Autowired
//	private HttpSession session; // 세션을 매개변수로 받아도 되고 DI 해도 된다.

	@PostMapping("/auth/joinProc")
//	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User1 user) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 된다.
	
//		int result = userService.회원가입(user); // DI 받은 것 사용
		userService.회원가입(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), result); // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User1 user) {	// RequestBody가 없으면 json 데이터를 받을 수 없다. -> key=value, x-www-form-urlencoded
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 세션 값을 변경해줘야한다.
		/* 강제로 세션 값 변경 */
		// 세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
// 전통적인 로그인 방식
//	@PostMapping("/api/user/login")
////	public ResponseDto<Integer> login(@RequestBody User1 user, HttpSession session) {
//	public ResponseDto<Integer> login(@RequestBody User1 user) {
//		System.out.println("UserApiController : login 호출됨");
//
//		User1 principal = userService.로그인(user); // principal(접근주체)
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

}
