<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
</head>
<body style="margin: 50px;">
	<h1>로그인</h1>
	<hr>
	<form action="/jw/ch06/login" method="post">
		<input type="text" name="uid" placeholder="id를 입력하세요"><br><br>
		<input type="password" name="pwd" placeholder="패스워드를 입력하세요"><br><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>