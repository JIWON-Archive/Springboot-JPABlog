package com.cos.blog.service;


import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User1;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다. 메모리에 대신 띄워준다.
@Service
public class BoardService {

	@Autowired // 객체가 쏙들어온다.
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User1 user) {	// title, content
		board.setCount(0);
		boardRepository.save(board);
	}

	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
}
