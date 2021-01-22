<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/static.css">
</head>
<body>
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
    
</body>
</html>