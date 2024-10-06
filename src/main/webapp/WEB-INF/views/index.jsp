<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>

<div class="container">
    <c:forEach var="board" items="${boards.content}">

    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
    </c:forEach>

    <ul class="pagination">
        <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>

    <%@ include file="layout/footer.jsp" %>
