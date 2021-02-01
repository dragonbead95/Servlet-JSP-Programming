<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
@import "/css/index/index.css";

@import "/css/index/static.css";

@import "/css/board/board_detail.css";
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/index/header.jsp"></jsp:include>
	<div class="content">
		<!-- side -->
		<jsp:include page="/WEB-INF/view/index/side.jsp"></jsp:include>
		<!-- main -->
		<div class="main">
			<div class="main_content">
				<h1>게시글 수정</h1>
				<div class="container_align">
					<div class="container">
						<form action="/admin/board/edit" method="post" enctype="multipart/form-data">
						<label for="title" class="title"><b>제목</b></label> 
						<input type="text" name="title" class="board_detail_value" value="${post.title}"/><br>
						
						<label for="writer_id" class="title"><b>작성자</b></label>
						<span name="writer_id" class="board_detail_value">${post.writer_id}</span>
						<br>
						
						<label for="regdate" class="title"><b>작성일</b></label>
						<input type="text" name="regdate" id="regdate" class="board_detail_value" readonly>
						<br>
						 
						<label for="file" class="title"><b>첨부파일</b></label>
						<span name="file" class="board_detail_value">
							<input type="file" name="file" id="file">
							<c:forTokens var="fileName" items="${post.files}" delims="," varStatus="st">
								<c:set var="style" value="" />
								<c:if test="${fn:endsWith(fileName,'.zip') }">
									<c:set var="style" value="font-weight:bold; color:red"/>
								</c:if>
								<a download href="/upload/${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
								<c:if test="${not st.last }">
									/
								</c:if>
							</c:forTokens>
						</span>
						<br>
						
						<label for="content" class="title"><b>내용</b></label>
						<textarea name="content" class="board_detail_value">${post.content}</textarea>
						<br>

						<div class="board_btn">
							<button type="submit" class="btn">수정</button>
							<a href="#" class="btn">삭제</a>
							<a href="/admin/index" class="btn">돌아가기</a>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script>
		const d = new Date();
		const year = d.getFullYear();
		const month = d.getMonth() + 1;
		const date = d.getDate();
		let regdate = document.querySelector("#regdate");
		regdate.value = year + "." + month + "." + date;
	</script>
</body>
</html>