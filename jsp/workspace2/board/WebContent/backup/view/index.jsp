<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/static.css">
</head>
<body>
<!-- todo : login 기능에서 request를 session으로 대체 -->
    <div class="header">
    <c:if test="${empty id}">
    	<a href="/member/login" class="btn">로그인</a>
    </c:if>
    <c:if test="${not empty id}">
    	<span>${id}</span>
    	<a href="/member/logout" class="btn">로그아웃</a>
    </c:if>    
        <a href="/member/signUp" class="btn">
            회원가입
        </a>
        <a href="/board/list" class="btn">
            게시판
        </a>
    </div>
    
</body>
</html>