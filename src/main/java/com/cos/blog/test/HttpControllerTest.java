package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답 (HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";

	// localhost:8080/http/lombok -> localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "hueka", "1234", "hueka@nate.com");
		Member m = Member.builder().username("kai").password("1234").email("kai@nate.com").build();
		System.out.println(TAG + "getter : " + m.getId());
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setId(5000);
		m.setUsername("휴닝카이");
		System.out.println(TAG + "setter : " + m.getId());
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombok test 완료";
	}
	// 인터넷 브라우저는 무조건 get 요청 밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	// MessageConverter(쿼리스트링 자동으로 파싱해서 오브젝트에 넣어준다. -> 스프링부트)
	@GetMapping("/http/get")
	public String getTest(Member m) { // http://localhost:8080/http/get?id=1&username=hueka&password=0814&email=hueka@nate.com
		return "get 요청 : " + +m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}

	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}

	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}