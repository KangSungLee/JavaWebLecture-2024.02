<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 추가</title>
</head>
<body style="margin:50px;">
   <h1>아티스트 추가</h1>
   <hr>
   <form action="/jw/ch07/kpop/insertArtist" method="post">
      <input type="text" name ="name" placeholder="걸그룹 이름"><br><br>
      <input type="text" name ="debut" placeholder="데뷔"><br><br>
      <input type="text" name= "hitSongId" placeholder="히트송 ID"><br><br>
      <input type="submit" value="등록">
   </form>

</body>
</html>