<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/_head.jspf" %>
</head>
<body>
	<%@ include file="../../common/_top.jspf" %>
	
	<div class="container" style="margin-top:80px">
  	  	<div class="row">
    	 	<%@ include file="../../common/_aside.jspf" %>
    	 
    	 	<div class="col-9">
    	 		<h3><strong class="me-5">메세지 추가</strong></h3>
    	 		<hr>
    	 		<div class="row">
		        	<div class="col-3"></div>
		        	<div class="col-6">
		        	<form action="/jw/ch07/msg/insert" method="post">
		        		<table class="table table-borderless">
			               	<tr>
				                <td style="width: 30%;"><label class="col-form-label">작성자</label></td>
				                <td style="width: 70%;"><input type="text" name="writer" class="form-control" value="${sessUname}" disabled></td>
			               	</tr>
							<tr>
								<td><label class="col-form-label">메세지 내용</label></td>
								<td><input type="text" name="content" class="form-control"></td>
							</tr>
							<tr>
								<td colspan="2">
									<button class="btn btn-primary" type="submit">확인</button>
									<button class="btn btn-secondary" type="reset">취소</button>
								</td>
							</tr>
			      	</form>
		        	</div>
	   	 			<div class="col-3"></div>
    	 		</div>
    	 	</div>
    	</div>
   	</div>
</body>
</html>