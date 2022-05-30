package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // @Entity는 제일 밑에 붙여주는 것이 좋다. 데이터베이스에 매핑하는 클래스라는 것 명시!
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨. 크기가 엄청 커짐

	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	@JoinColumn(name = "userId") // 필드값은 userId
	private User1 user; // User1 객체를 참조. 자동으로 FK가 만들어진다.
	// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	// 충돌이 발생한다.

	@CreationTimestamp // 데이터가 insert, update 될 때 자동으로 현재 시간이 들어간다.
	private Timestamp createDate;
}
