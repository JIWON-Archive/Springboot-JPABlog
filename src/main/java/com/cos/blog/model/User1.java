package com.cos.blog.model;

//테이블을 생성하기 위한 모델
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
// ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity // User클래스가 MySQL(H2)에 테이블이 생성이 된다. ORM 클래스다!
// @DynamicInsert // insert시 null인 값을 제외시켜준다.
public class User1 { // H2 DB에서는 USER 테이블 생성X(예약어라서 에러남)
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디

	@Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;

//	@ColumnDefault("'user'") // 문자라는 것을 알려주기 위해 "'문자'"
//	private String role;// Enum을 쓰는 게 좋다.
	// admin, user, manager(권한을 줄 수 있다. 타입이 String이면 managerrr 이런식으로 데이터베이스에 오타 들어갈 수 있다.)
	// Enum을 쓰면 데이터의 도메인을 만들 수 있다. 도메인은 어떤 범위 성별 : 도메인 남녀 / 학년 : 도메인 초등학생 1-6 고등학생 1-3
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING) // 해당 EnumType이 String
	private RoleType role; // 타입을 강제할 수 있다. ADMIN, USER
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
