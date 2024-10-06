package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

//	@ColumnDefault("0")
	private int count; // 조회수
	
	// fetch = FetchType.EAGER 기본전략 Board 테이블을 SELECT 하면 User 정보는 바로 조인해서 가져온다.
	// (가져올것 하나)
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One 한명의 유저는 여러개의 게시물을 쓸 수 있다. 여러개의 게시물은 한명의 유저에 의해 쓰일 수 있다.
	@JoinColumn(name = "userId") // 필드값은 userId
	private User1 user; // User1 객체를 참조. 자동으로 FK가 만들어진다.
	// DB는 오브젝트를 저장할 수 없다. FK사용, 자바는 오브젝트를 저장할 수 있다.
	// 충돌이 발생한다.

	// 하나의 게시글은 여러개의 답변을 가질 수 있다.
	// fetch = FetchType.LAZY 기본전략 Board 테이블을 SELECT 할 때 가져올 것이 많다.
	// 필요하면 들고오고 필요하지 않으면 안들고 온다.
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // mappedBy 연관관계의 주인이 아니다.(난 FK가 아니에요.) DB에 컬럼을 만들지 마세요. board를 SELECT 할 때 조인문을 통해서 값을 얻기 위해 필요한 것.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // SELECT 시 바로 가져오기
	// FK가 필요없다. FK는 Reply 테이블(클래스 Object)의 board
	// JOIN 컬럼이 필요없다. JOIN 컬럼이 여러개인 경우 1정규화(데이터베이스의 하나의 컬럼은 원자성을 가진다.)가 깨지므로!
	// Reply 클래스의 private Board board;
	private List<Reply> reply;

	@CreationTimestamp // 데이터가 insert, update 될 때 자동으로 현재 시간이 들어간다.
	private Timestamp createDate;
}
