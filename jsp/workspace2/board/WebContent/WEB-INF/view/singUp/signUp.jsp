<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/register.css">
    <link rel="stylesheet" href="/css/static.css">
</head>
<body>
  <form action="/member/register" method="POST" onsubmit="return confirm_password()">
    <div class="align_container">
    <div class="container">
      <h1>회원가입</h1>
      <label for="email">이메일</label>
      <input type="text" placeholder="이메일을 입력해주세요" name="email" id="email" required>
      
      <label for="password">비밀번호</label>
      <input type="password" placeholder="비밀번호를 입력해주세요" name="password" id="password" required>

      <label for="re_password">비밀번호 확인</label>
      <input type="password" placeholder="비밀번호를 입력해주세요" name="re_password" id="re_password" required>

      <div class="register_btn_container">
        <button type="submit" class="btn">회원가입</button>
        <a href="/index" class="btn a_btn">취소</a>
      </div>
      
      <div class="signin">
        <p>이미 회원이신가요? <a href="/member/login">로그인</a></p>
      </div>
      
    </div>
  </div>
  </form>


	<script src="js/register.js"></script>
</body>
</html>