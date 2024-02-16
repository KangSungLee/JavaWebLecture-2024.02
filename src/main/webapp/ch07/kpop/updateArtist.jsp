<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 수정</title>
</head>
<body style="margin:50px;">
   <h1>아티스트 수정</h1>
   <hr>
   <form action="/jw/ch07/kpop/updateArtist" method="post">
   	  <input type="hidden" name="aid" value="${artist.aid}">	
   	  <input type="text" value="${artist.aid}" disabled><br><br>
      <input type="text" name ="name" value="${artist.name}"><br><br>
      <input type="text" name ="debut" value="${artist.debut}"><br><br>
      <input type="text" name= "hitSongId" value="${artist.hitSongId}"><br><br>
      <input type="submit" value="수정">
   </form>
</body>
</html>