package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User1;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다. 메모리에 대신 띄워준다.
@Service
public class UserService {

	@Autowired // 객체가 쏙들어온다.
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

//	@Transactional // 회원가입 하나의 서비스가 트랜잭션으로 묶이게 되고 성공하면 커밋 실패하면 롤백
//	public int 회원가입(User1 user) {
//		try {
//			userRepository.save(user);
//			return 1; // 정상
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : 회원가입() : " + e.getMessage());
//		}
//		return -1; // 비정상
//	}
	@Transactional
	public void 회원가입(User1 user) {
		String rawPassword = user.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	@Transactional
	public void 회원수정(User1 user) {	// 외부로 부터 받은 user
		// 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		User1 persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		// 사용자로 부터 password를 받는다.
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		// password 수정
		persistance.setPassword(encPassword);
		// email 수정
		persistance.setEmail(user.getEmail());

		// 회원 수정 함수 종료 시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}

//	@Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//	public User1 로그인(User1 user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
