<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="/admin/board/edit" method="post" enctype="multipart/form-data">
		<a class="js_file" download id="file_1" href="/upload/캡처.PNG" style="">캡처.PNG</a>
		<a class="js_file" download id="file_2" href="/upload/코딩일기.TXT" style="">코딩일기.TXT</a>
		<button type="submit" onclick="hello()">수정</button>
	</form>
	
	<script type="text/javascript">
		const files = document.querySelectorAll(".js_file");
		let temp = "?ufile=";
		for(let i=0; i<files.length; i++)
		{
			temp += (files[i].innerText + ",");
		}
		console.log(temp);
		
	</script>
</body>
</html>