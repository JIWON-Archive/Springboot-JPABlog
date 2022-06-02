package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Exception이 발생하면 이 클래스로 들어온다.
@RestController
// value와 파라미터를 Exception으로 하면 모든 Exception이 들어온다.
public class GlobalExceptionHandler {
	@ExceptionHandler(value = IllegalArgumentException.class) // IllegalArgumentException이 발생하면 에러를 메소드에 스프링이 전달해준다.
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
