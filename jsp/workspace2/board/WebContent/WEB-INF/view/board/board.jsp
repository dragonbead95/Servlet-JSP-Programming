<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	                		<td>${n.title}</td>
	                		<td>${n.writer_id}</td>
	                		<td>${n.regdate }</td>
	                		<td>${n.hit}</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="board_page_status">
                <span>1 / 5 pages</span>
            </div>

            <div class="board_page">
                <a href="#" class="btn_page btn_prev">이전</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#" class="btn_page btn_next">다음</a>
            </div>
        </div>
        
        <div class="board_btn">
            <a href="#" class="btn">작성</a>
            <a href="/index" class="btn">돌아가기</a>
        </div>
    </div>
    
    
</body>
</html>