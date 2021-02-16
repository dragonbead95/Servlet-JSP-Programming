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
				<h1>게시글 상세</h1>
				<div class="container_align">
					<div class="container">
						<form action="/admin/board/edit" method="get" enctype="multipart/form-data">
						
						<input name="id" type="hidden" value="${post.id}"/>
						<label for="title" class="title"><b>제목</b></label> 
						<span name="title" class="board_detail_value">${post.title}</span><br>
						
						<label for="writer_id" class="title"><b>작성자</b></label>
						<span name="writer_id" class="board_detail_value">${post.writer_id}</span>
						<br>
						
						<label for="regdate" class="title"><b>작성일</b></label>
						<span name="regdate" class="board_detail_value">${post.regdate}</span>
						<br>
						 
						<label for="file" class="title"><b>첨부파일</b></label>
						<span name="file" class="board_detail_value">
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
						<textarea name="content" class="board_detail_value" readonly>${post.content}</textarea>
						<br>

						<div class="board_btn">
							<c:if test="${id==post.writer_id}">
								<button type="submit" class="btn" name="cmd" value="수정">수정</button>
								<button type="submit" class="btn" name="cmd" value="삭제">삭제</button>
							</c:if>
							<a href="/admin/index" class="btn">돌아가기</a>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>