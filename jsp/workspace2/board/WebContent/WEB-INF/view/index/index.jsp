<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
@import url("/css/index/index.css");
@import url("/css/index/static.css");
</style>

</head>
<body>

	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="content">
		<!-- side -->
		<jsp:include page="side.jsp"></jsp:include>
		<!-- main -->
		<jsp:include page="main.jsp"></jsp:include>
	</div>

	


</body>
</html>