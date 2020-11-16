<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!-- ------------------------------------------------------------>
<!-- View (HTML) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("aa","hello aa");
	pageContext.setAttribute("result","page result value");
%>
</head>
<body>
  	<%= request.getAttribute("result") %>입니다.<br>
	${result}<br> <!-- string 값 호출 -->
	${names[0]}<br> <!-- list 값 호출 -->
	${notice.title}<br> <!-- map의 value값 호출 -->
	${aa}<br>
	
	page객체의 result 호출 : ${pageScope.result}<br> <!-- page 객체의 result 호출 --><br>
	request객체의 result 호출 : ${requestScope.result}<br> <!-- request 객체의 result 호출-->
	

	param.n : ${param.n}<br>
	header. : ${header.accept}<br>
	
	param.n > 3 : ${param.n gt 3}<br>
	param.n > 3 : ${param.n > 3}<br>
	<!-- jsp에서 ">" 기호 보다 gt와 같이 사용하는 이유
		html에서 "<",">"와 같은 꺽음쇠 기호를 쓰는 것이 바람직하지 않기 때문이다.
		따라서 gt와 같이 사용하는 것을 권장함 
		그리고 xml 기반의 문서를 만들때 EL 표현식에서 ">"와 같은 꺽음쇠를 허용하지
		않을 수 있다.-->
		
	empty param.n : ${empty param.n}<br><!-- n이 null 또는 빈문자열로 오면 참이된다.-->
	empty param.n : ${param.n==null || param.n==''}<br>
	<!-- 해당 식은 위와 같다. 그러나 가독성을 위해서 위와 같이 쓰는 것을 권장 -->
	
	not empty param.n : ${not empty param.n}<br> 
	<!-- param.n이 비어있지 않은지 확인, 비어있지 않으면 true, 비어있으면 false 반환 -->
	
	상항 연산자 사용하여 empty check : ${empty param.n ? "값이 비어있습니다." : param.n}<br> 
	<!-- ?:(삼항 연산자)를 사용하여 param.n이 비어있으면 값이 비어있습니다. 
		n 파라미터 값이 존재하는 경우 param.n 값 출력-->
		
	param.n/2 : ${param.n/2}<br> <!-- n값을 2로 나눈다. 반환값은 실수이다. -->
	
</body>
</html>