<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 수정</title>
</head>
<body style="margin:50px;">
   <h1>메세지 수정</h1>
   <hr>
   <form action="/jw/ch07/msg/update" method="post">
   	  <input type="hidden" name="mid" value="${message.mid}">	
   	  <input type="text" value="${message.mid}" disabled><br><br>
      <input type="text" name ="content" value="${message.content}"><br><br>
      <input type="text" name ="writer" value="${message.writer}"><br><br>
      <input type="text" name= "modTime" value="${message.modTime}"><br><br>
      <input type="submit" value="수정">
   </form>
</body>
</html>