<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메세지 목록</title>
	<style>
		th, td { padding: 3px; }
	</style>
</head>
<body style="margin: 50px;">
	<h1>메세지 목록
	<button style="margin-left: 180px;" onclick="location.href='/jw/ch07/msg/insert'">메세지 추가</button>
	</h1>
	<hr>
	<table border="1">
		<tr><th>순번</th><th>내용</th><th>작성자</th>
			<th>작성시간</th><th>수정</th><th>삭제</th></tr>
		<c:forEach var="msg" items="${list}">				<!-- for (City city: list) -->
			<tr>
				<td>${msg.mid}</td>		<!-- City class의 member 변수 이름과 동일해야 함 -->
				<td>${msg.content}</td>
				<td>${msg.writer}</td>
				<td>${msg.modTime}</td>
				<td>
					<a href="/jw/ch07/msg/update?mid=${msg.mid}">수정</a>
				</td>
				<td>
					<a href="/jw/ch07/msg/delete?mid=${msg.mid}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html></html>