<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<label for="title" class="title"><b>제목</b></label> 
						<span name="title" class="board_detail_value">${post.title}</span><br>
						
						<label for="writer_id" class="title"><b>작성자</b></label>
						<span name="writer_id" class="board_detail_value">${post.writer_id}</span>
						<br>
						
						<label for="regdate" class="title"><b>작성일</b></label>
						<span name="regdate" class="board_detail_value">${post.regdate}</span>
						<br>
						 
						<label for="files" class="title"><b>첨부파일</b></label>
						<span name="files" class="board_detail_value">${post.files}</span>
						<br>
						
						<label for="content" class="title"><b>내용</b></label>
						<textarea name="content" class="board_detail_value" readonly>${post.content}</textarea>
						<br>

						<div class="board_btn">
							<a href="#" class="btn">수정</a>
							<a href="#" class="btn">삭제</a>
							<a href="/admin/index" class="btn">돌아가기</a>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>