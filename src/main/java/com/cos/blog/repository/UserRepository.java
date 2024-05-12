package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User1;

import java.util.Optional;

// JpaRepository는 User1 테이블을 관리, PK는 Integer
// 기본적인 CRUD 모든 함수를 들고 있다.
// jsp로 치면 DAO
// 자동으로 bean 등록이 된다. 스프링이 메모리에 띄워준다.
//@Repository 생략이 가능한다.
public interface UserRepository extends JpaRepository<User1, Integer> {
    // SELECT * FROM user WHERE username = 1? (첫번째 파라미터);
    Optional<User1> findByUsername(String username);
}
// 로그인 시 사용 1번
// JPA Naming 쿼리 전략
// SELECT * FROM user WHERE username = ? 1 AND password = ? 2;
//	User1 findByUsernameAndPassword(String username, String password);

// 2번
//	@Query(value = "SELECT * FROM user WHERE username = ? AND password= ?", nativeQuery = true)
//	User1 login(String username, String password);
