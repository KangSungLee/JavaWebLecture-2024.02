<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 추가</title>
</head>
<body style="margin:50px;">
   <h1>메세지 추가</h1>
   <hr>
   <form action="/jw/ch07/msg/insert" method="post">
      <input type="text" name ="content" placeholder="내용"><br><br>
      <input type="text" name ="writer" placeholder="작성자"><br><br>
      <input type="submit" value="등록">
   </form>

</body>
</html>