package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
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

//	@Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//	public User1 로그인(User1 user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
