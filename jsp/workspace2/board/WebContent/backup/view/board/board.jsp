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
    <title>게시판</title>
    <link rel="stylesheet" href="/css/board.css">
    <link rel="stylesheet" href="/css/static.css">
</head>
<body>
    <div class="align_container">
        <div class="container">
            <table class="board_table">
                <thead class="board_title">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                    
                </thead>
                <tbody class="board_content">
                	<c:forEach var="n" items="${list}">
	                	<tr>
	                		<td>${n.id}</td>
	                		<td><a href="/board/detail?id=${n.id}">${n.title}</a></td>
	                		<td>${n.writer_id}</td>
	                		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate}"/></td>
	                		<td>${n.hit}</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:set var="page" value="${(empty param.p) ? 1 : param.p}"/>
            <c:set var="startNum" value="${page-(page-1)%5}"/>
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
			
            <div class="board_page_status">
                <span>${page} / ${lastNum} pages</span>
            </div>

            <div class="board_page">
            	
            	<!-- page 이전 버튼 -->
                <c:if test="${startNum>1}">
                	<a href="?p=${startNum-5}" class="btn_page btn_prev">이전</a>
                </c:if>
                <c:if test="${startNum<=1}">
                	<a onclick="alert('이전 페이지가 없습니다.');" class="btn_page btn_prev">이전</a>
                </c:if>
                
                <!-- 데이터 레코드 수에 따른 pager 출력 -->
                <c:forEach var="i" begin="0" end="4">
	                <c:if test="${(startNum+i)<=lastNum}">
	                	<a href="?p=${startNum+i}" style="${(page==startNum+i) ? 'color:orange;' : ''}">${startNum+i}</a>
	                </c:if>
                </c:forEach>
                
                <!-- page 다음 버튼 -->
                <c:if test="${startNum+4<lastNum}">
                	<a href="?p=${startNum+5}" class="btn_page btn_next">다음</a>
                </c:if>
                <c:if test="${startNum+4>=lastNum}">
                	<a onclick="alert('다음 페이지가 없습니다.');" class="btn_page btn_next">다음</a>
                </c:if>
            </div>
        </div>
        
        <div class="board_btn">
            <a href="/board/reg" class="btn">작성</a>
            <a href="/index" class="btn">돌아가기</a>
        </div>
    </div>
    
    
</body>
</html>