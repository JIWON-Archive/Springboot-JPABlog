<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <div class="form-group">
            <label for="title">Title</label>
            <h3>${board.title}</h3>
        </div>

        <div class="form-group">
            <label for="content">Content</label>
            <div>${board.content}</div>
        </div>

        <button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
    </form>
</div>
<script>
    $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
