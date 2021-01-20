<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="main_content">
	<div class="main_header">
		<h2>게시판</h2>
	</div>
	<div class="board_search">
		<select name="field" id="field">
			<option value="title">제목</option>
			<option value="wrtier_id">작성자</option>
		</select> <input type="text"> <a href="#" class="board_search">검색</a>
	</div>
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
			<tr class="board_row">
				<td>1</td>
				<td>제목1</td>
				<td>김용환</td>
				<td>2020.01.19</td>
				<td>3</td>
				<td><input type="checkbox"></td>
			</tr>
			<tr class="board_row">
				<td>1</td>
				<td>제목1</td>
				<td>김용환</td>
				<td>2020.01.19</td>
				<td>3</td>
				<td><input type="checkbox"></td>
			</tr>
		</tbody>
	</table>
	<div class="board_btn">
		<a href="#" class="btn">글쓰기</a> <a href="#" class="btn">일괄공개</a> <a
			href="#" class="btn">일괄삭제</a>
	</div>
	<div class="board_page">
		<a href="#">&lt;</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a>
		<a href="#">4</a> <a href="#">5</a> <a href="#">&gt;</a>
	</div>
</div>