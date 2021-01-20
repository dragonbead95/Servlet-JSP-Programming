<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 작성</title>
<style>
@import "/css/index/index.css";

@import "/css/index/static.css";

@import "/css/board/board_reg.css";
</style>
</head>
<body>
	<jsp:include page="../index/header.jsp"></jsp:include>
	<div class="content">
		<jsp:include page="../index/side.jsp"></jsp:include>
		<div class="main">
			<div class="main_content">
				<h1>게시글 작성</h1>
				<div class="container_align">
					<div class="container">
						<label for="title" class="title"><b>제목</b></label> <input
							type="text" placeholder="제목" name="title" id="title" required>
						<br> <label for="regdate" class="title"><b>작성일</b></label> <input
							type="text" name="regdate" id="regdate" readonly> <br>

						<label for="content" class="title"><b>내용</b></label> <br>
						<textarea rows="5" cols="50" class="board_content" name="content"></textarea>
						<br>

						<div class="write_container">
							<button type="submit" class="write_btn">작성</button>
							<button type="submit" class="write_btn">취소</button>
						</div>
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