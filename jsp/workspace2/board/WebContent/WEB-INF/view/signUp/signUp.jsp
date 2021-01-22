<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SignUp</title>
<style>
@import "/css/index/header.css";
@import "/css/signUp/signUp.css";
@import "/css/index/static.css";
</style>
<script type="text/javascript" src="/js/signUp.js">
</script>
</head>
<body>
	<jsp:include page="../index/header.jsp"></jsp:include>
	<form action="/member/signUp" method="POST"
		onsubmit="return confirm_password()">
		<div class="align_container">
			<div class="container">
				<h1>회원가입</h1>
				<label for="user_id">ID</label> 
				<input type="text" placeholder="ID를 입력해주세요." name="user_id" id="user_id" required>
				
				<label for="password">비밀번호</label> 
				<input type="password" placeholder="비밀번호를 입력해주세요" name="password" id="password" required>

				<label for="re_password">비밀번호 확인</label> 
				<input type="password" placeholder="비밀번호를 입력해주세요" name="re_password" id="re_password" required>

				<div class="register_btn_container">
					<button type="submit" class="btn">회원가입</button>
					<a href="/index" class="btn a_btn">취소</a>
				</div>

				<div class="signin">
					<p>
						이미 회원이신가요? <a href="/member/login">로그인</a>
					</p>
				</div>

			</div>
		</div>
	</form>

	<c:if test="${isSignUp==0}">
		<script>alert("이미 존재하는 ID입니다.");</script>
	</c:if>

	<script type="text/javascript" src="/js/signUp.js"></script>
</body>
</html>