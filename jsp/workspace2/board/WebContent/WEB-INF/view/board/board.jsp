<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="css/board.css">
    <link rel="stylesheet" href="css/static.css">
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
                <tbody>
                	<tr>
                		<td>1</td>
                		<td>제목1</td>
                		<td>user1</td>
                		<td>2020-12-27</td>
                		<td>0</td>
                	</tr>
                </tbody>
            </table>

            
        </div>
        
        <div class="container">
            <a href="#" class="btn">작성</a>
            <a href="/index" class="btn">취소</a>
        </div>
    </div>
    
    
</body>
</html>