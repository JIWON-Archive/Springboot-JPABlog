let index = {
    // *this
    init: function () {
        // function(){}, ()=>{} this를 바인딩하기 위해서 화살표 함수 사용!!
        $("#btn-save").on("click", () => {
            this.save(); // *this는 같음 function사용하면 this가 window객체를 가르킴
        });
        $("#btn-delete").on("click", () => {
            this.deleteById(); // delete는 예약어
        });
        $("#btn-update").on("click", () => {
            this.update();
        });

//		$("#btn-login").on("click", () => {
//			this.login();
//		});
    },

    save: function () {
        // alert('user의 save 함수 호출됨');
        // id 값이 들고 있는 값을 data 오브젝트에 넣는다. 변수에 값을 바인딩해둔다.
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            // 응답의 결과가 정상이면 done 실행
            alert("글쓰기가 완료되었습니다.");
            alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            // 실패하면 fail 실행
            alert(JSON.stringify(error));
        });
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function (resp) {
            // 응답의 결과가 정상이면 done 실행
            alert("삭제가 완료되었습니다.");
            alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            // 실패하면 fail 실행
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();
        console.log(id);
        // alert('user의 save 함수 호출됨');
        // id 값이 들고 있는 값을 data 오브젝트에 넣는다. 변수에 값을 바인딩해둔다.
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            // 응답의 결과가 정상이면 done 실행
            alert("글 수정이 완료되었습니다.");
        //    alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            // 실패하면 fail 실행
            alert(JSON.stringify(error));
        });
    },
}

index.init();

