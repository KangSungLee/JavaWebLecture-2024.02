<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/_head.jspf" %>
<style>
	th {text-align: center;}
	td {font-size: 13px}
</style>
</head>
<body>
	<%@ include file="../../common/_top.jspf" %>
	
	<div class="container" style="margin-top:80px">
  	  	<div class="row">
    	 	<%@ include file="../../common/_aside.jspf" %>
    	 
    	 	<div class="col-9">
	    		<h3><strong class="me-5">게시판 목록</strong>
		        	<span style="font-size: 16px"><a href="/jw/ch09/user/register"><i class="fa-solid fa-user-plus"></i>사용자 가입</a></span>	    		
	    		</h3>
	    		<hr>
	    		<div class="row">
		    		<div class="col-1"></div>
		    		<div class="col-10">
		    			<table class="table">
			    			<tr><th>순번</th><th>내용</th><th>작성자</th><th>작성시간</th><th>수정</th><th>삭제</th></tr>
			    			<c:forEach var="msg" items="${list}">
							<!-- for (City city: list) -->
							<tr>
								<td>${msg.mid}</td>
								<!-- City class의 member 변수 이름과 동일해야 함 -->
								<td>${msg.content}</td>
								<td>${msg.writer}</td>
								<td>${fn:substring(msg.modTime, 2, 10)}</td>
								<td><a href="/jw/ch07/msg/update?mid=${msg.mid}">수정</a></td>
								<td><a href="/jw/ch07/msg/delete?mid=${msg.mid}">삭제</a></td>
							</tr>
							</c:forEach>
		    			</table>
		    			<div>
						<div>
							<div class="row">
								<div class="col-10">
									<form action="/jw/ch07/msg/listSearch" method="post" style="display: inline;">
										<select name="searchList">
											<option value="writer">작성자</option>
											<option value="content">내용</option>
										</select>
										<input type="text" placeholder="검색 내용" name="search"> 
										<input type="submit" value="검색">
									</form>
								</div>
								<div class="col-2">
									<button onclick="location.href='/jw/ch07/msg/insert'">메세지 추가</button>
								</div>
							</div>
						</div>
		    		</div>
		    		<div class="col-1"></div>
	    		</div>
	    	</div>
    	</div>
   	</div>	
   	</div>
</body>
</html>