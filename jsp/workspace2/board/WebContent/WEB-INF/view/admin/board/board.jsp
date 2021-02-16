<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main_content">
	<div class="main_header">
		<h2>게시판</h2>
	</div>
	<div class="board_search">
		<form action="/admin/index" method="get">
			<fieldset>
				<select name="field" id="field">
					<option value="title">제목</option>
					<option value="writer_id">작성자</option>
				</select>
				<input type="text" name="query" value=""/> <!-- 검색어 --> 
				<input class="board_search" type="submit" value="검색"/>
			</fieldset>
		</form>
	</div>
	<form action="/admin/board/pub" method="post">
	<table class="board_table">
		<thead class="board_title">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>구분</th>
			</tr>
		</thead>
		<tbody class="board_content">
			<c:set var="ids" value=""/>
			<c:forEach var="n" items="${list}">
				<c:set var="ids" value="${ids} ${n.id}"/>
				<tr>
					<td>${n.id}</td>
					<td><a href="/admin/board/detail?id=${n.id}">${n.title}</a></td>
					<td>${n.writer_id}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate}" /></td>
					<td>${n.hit}</td>
					
					<td><input type="checkbox" ${n.pub==true ? 'checked' : ''} name="open-id" value="${n.id}"/></td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="board_btn">
		
		<a href="/admin/board/reg" class="btn">글쓰기</a>
		<input type="hidden" name="ids" value="${ids}"> <!-- 일괄공개를 위한 임시 변수 -->
		<button type="submit" name="cmd" class="btn" value="일괄공개">일괄공개</button>
		<button type="submit" name="cmd" class="btn" value="일괄삭제">일괄삭제</button>
	</div>
	</form>
	
	<c:set var="page" value="${(empty param.p) ? 1 : param.p}"/>
	<c:set var="startNum" value="${page-(page-1)%5}"/>
	<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
	
	<div class="board_page">
		<!-- page 이전 버튼 -->
		<c:if test="${startNum>1}">
			<a href="/admin/index?p=${startNum-5}" class="btn_page btn_prev">&lt;</a>
		</c:if>

		<!-- 데이터 레코드 수에 따른 pager 출력 -->
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${(startNum+i)<=lastNum}">
				<a href="/admin/index?p=${startNum+i}"
					style="${(page==startNum+i) ? 'color:orange;' : ''}">${startNum+i}</a>
			</c:if>
		</c:forEach>

		<!-- page 다음 버튼 -->
		<c:if test="${startNum+4<lastNum}">
			<a href="/admin/index?p=${startNum+5}" class="btn_page">&gt;</a>
		</c:if>
		<c:if test="${startNum+4>=lastNum}">
			<a onclick="alert('다음 페이지가 없습니다.');" class="btn_page">&gt;</a>
		</c:if>
	</div>
</div>