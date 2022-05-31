package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User1;

// JpaRepository는 User1 테이블을 관리, PK는 Integer
// 기본적인 CRUD 모든 함수를 들고 있다.
// DAO
// 자동으로 bean 등록이 된다.
//@Repository 생략이 가능한다.
public interface UserRepository extends JpaRepository<User1, Integer> {

}
