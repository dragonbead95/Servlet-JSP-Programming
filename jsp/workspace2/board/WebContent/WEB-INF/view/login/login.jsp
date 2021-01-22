<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
    @import "/css/index/header.css";
    @import "/css/login/login.css";
    @import "/css/index/static.css";
    </style>
</head>
<body>
	<jsp:include page="../index/header.jsp"></jsp:include>
    <form action="/member/login" method="post">
        <div class="align_container">
        <div class="container">
            <h1>로그인</h1>
            <input type="text" placeholder="ID" name="id" id="id" required>
            <input type="password"" placeholder="비밀번호" name="password" id="password" required>
            <button type="submit" class="btn">로그인</button>
            <a href="/index" class="btn">취소</a>
        </div>
    </div>
    </form>
    
    <c:if test="${isLogin==0}">
		<script>alert("로그인에 실패하였습니다.");</script>
	</c:if>
</body>
</html>