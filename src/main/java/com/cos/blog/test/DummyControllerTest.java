package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User1;
import com.cos.blog.repository.UserRepository;

// html 파일이 아니라 data를 리턴해주는 controller = RestController
@RestController 
public class DummyControllerTest {

//	@PostMapping("/dummy/join")
	// @RequestParam("username") String u -> @RequestParam("key 값") 변수에 키캆이 들어온다.
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	// join 메소드의 파라미터에 쏙들어온다.
//	public String join(String username, String password, String email) { // key=value(약속된 규칙) 형태로 받아준다.
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		System.out.println("email : " + email);
//		return "회원가입이 완료되었습니다.";
//	}
	// 스프링이 @RestCotroller 어노테이션을 읽어서 DummyControllerTest를 메모리에 띄워줄때 UserRepository null
	// 스프링이 컨포넌트 스캔할 때 UserRepository 메모리에 띄워줘서 들고와서 사용하기만 하면 된다.
	// 의존성 주입(DI)
	@Autowired // @Autowired 붙여주면 DummyControllerTest를 메모리에 띄워줄때 UserRepository도 같이 메모리에 띄워준다.
	private UserRepository userRepository; // @Autowired는 UserRepository타입으로 스프링이 관리하는 객체를 쏙 넣어주는 것

	// email, password
	@PutMapping("/dummy/user/{id}")
	public User1 updateUser(@PathVariable int id, @RequestBody User1 requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		return null;
	}

	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User1> list() {
		return userRepository.findAll(); // User1 전체 조회
	}

	// 한페이지당 2건 데이터를 리턴 받아 볼 예정 sort는 id로 최신순
	@GetMapping("/dummy/user")
	public List<User1> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User1> pagingUser = userRepository.findAll(pageable);

		List<User1> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}") // 3이 id에 쏙들어온다.
	public User1 detail(@PathVariable int id) { // id에 매핑되서 쏙들어온다.
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴이 되잖아 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User1객체를 감싸서 가져올테니 null인지 판단해서 return 해!!
//		User1 user = userRepository.findById(id).get();	// get() User1 객체를 뽑아서 줌. 값이 없을 수 있으므로 위험.

//		User1 user = userRepository.findById(id).orElseGet(new Supplier<User1>() {
//			@Override // 인터페이스의 메소드 오버라이딩하면 객체 생성이 가능하다.
//			public User1 get() {
//				// TODO Auto-generated method stub
//				return new User1();
//			}
//		});
//		return user;

		User1 user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		// 스프링부트 = MessageConverter 라는 애가 응답 시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}

	// 람다식
//	User1 user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 사용자는 없습니다");
//	});
//	return user;
//}

	@PostMapping("/dummy/join")
	public String join(User1 user) { // key=value(약속된 규칙) 형태로 받아준다.
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER); // 디폴트 값을 회원가입할 때 미리 넣으면 된다.
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
