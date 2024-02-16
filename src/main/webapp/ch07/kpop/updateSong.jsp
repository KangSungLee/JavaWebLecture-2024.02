<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노래 수정</title>
</head>
<body style="margin:50px;">
   <h1>노래 수정</h1>
   <hr>
   <form action="/jw/ch07/kpop/updateSong" method="post">
   	  <input type="hidden" name="sid" value="${song.sid}">	
   	  <input type="text" value="${song.sid}" disabled><br><br>
      <input type="text" name ="title" value="${song.title}"><br><br>
      <input type="text" name ="lyrics" value="${song.lyrics}"><br><br>
      <input type="submit" value="수정">
   </form>
</body>
</html>