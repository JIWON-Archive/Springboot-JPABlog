package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//객체지향에서 변수는 private로 만들고 변수의 상태는 메서드에 의해서 변경되게 설계해야한다.
//상태 값을 변경할 수 있게 getter, setter를 만든다.
//@Setter
//@Getter
//@Data
//@AllArgsConstructor
//@NoArgsConstructor // 빈 생성자
//public class Member {
//	private int id;
//	private String username;
//	private String password;
//	private String email;
//
//}

@Data
@NoArgsConstructor // 빈 생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;

	// 빌더 패턴
	// 생성자에 넣는 순서를 지키지 않아도 된다.
	// 필드에 어떤 값이 들어갔는 지 몰라도 된다.
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}

//@RequiredArgsConstructor // final 붙은 변수의 생성자 인자 만들어줌
//public class Member {
// 데이터 베이스에서 가져오므로 데이터가 변경되지 않게 final로 잡아준다. 불변성 유지
//	private final int id;
//	private final String username;
//	private String password;
//	private final String email;
//}
