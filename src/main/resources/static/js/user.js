let index = {
	// *this
	init: function() {
		// function(){}, ()=>{} this를 바인딩하기 위해서 화살표 함수 사용!!
		$("#btn-save").on("click", () => {	
			this.save(); // *this는 같음 function사용하면 this가 window객체를 가르킴
		});
	},

	save: function() {
		// alert('user의 save 함수 호출됨');
		// id 값이 들고 있는 값을 data 오브젝트에 넣는다. 변수에 값을 바인딩해둔다.
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);
		
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환!
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			// 회원가입 수행 요청(100초 요청)
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8",	// body 데이터가 어떤 타입인지(MIME)
			dataType : "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트로 변경해준다.
		}).done(function(resp){
			// 응답의 결과가 정상이면 done 실행
			alert("회원가입이 완료되었습니다.");
			alert(resp);
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			// 실패하면 fail 실행
			alert(JSON.stringify(error));
		});	
		
		// 1 실행
		// 2 실행
		// 3 -> 100초가 지나고 회원가입 수행 요청이 와서 성공하면 3 실행 중 done 실행 -> 비동기
	}
}

index.init();