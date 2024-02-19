<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>노래 차트</title>
	<style>
		th, td { padding: 3px; }
	</style>
</head>
<body style="margin: 50px;">
	<h1>노래 차트
	<button style="margin-left: 400px;" onclick="location.href='/jw/ch07/kpop/insertArtist'">걸그룹 추가</button>
	<button onclick="location.href='/jw/ch07/kpop/insertSong'">노래 추가</button>
	</h1>
	<hr>
	<table border="1">
		<tr><th>아이디</th><th>걸 그룹명</th><th>데뷔일자</th>
			<th>히트송 제목</th><th>히트송 가사</th><th>삭제</th></tr>
		<c:forEach var="arti" items="${list}">				<!-- for (City city: list) -->
			<tr>
				<td>${arti.aid}</td>		<!-- City class의 member 변수 이름과 동일해야 함 -->
				<td><a href="/jw/ch07/kpop/updateArtist?aid=${arti.aid}">${arti.name}</a></td>
				<td>${arti.debut}</td>
				<td><a href="/jw/ch07/kpop/updateSong?sid=${arti.sid}">${arti.title}</a></td>
				<td>${arti.lyrics}</td>
				<td>
					<a href="/jw/ch07/kpop/deleteArtist?aid=${arti.aid}">아티스트</a>
					<a href="/jw/ch07/kpop/deleteSong?sid=${arti.sid}">노래</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>