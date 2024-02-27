<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>디지털 시계</title>
</head>
<body style="margin: 50px;">
    <div>
	    <form action="/jw/mini/aInsert" method="post">
	    	<table>
		    <%--	<input type="hidden" name="equipment_id" value="${sessUid}">  --%>
		    	<input type="hidden" name="equipment_id" value="james">
		    		<tr>
		    			<td>구매물품</td>
		    			<td><input type="text" name="title" placeholder="제목"></td>
		    		</tr>
		    		<tr>
		    			<td>시작가격</td>
		    			<td><input type="text" name="start_price" placeholder="시작가격"></td>
		    		</tr>
		    		<tr>
		    			<td colspan="2"><textarea name="content" rows="20" cols="50" placeholder="희망사항을 적어주세요."></textarea></td>
		    		</tr>
		    		<tr>
		              	<td colspan="2">
		                   <button class="btn btn-primary" type="submit">확인</button>
		                   <button class="btn btn-secondary" type="reset">취소</button>
		              	</td>
					</tr>
	    	</table>
	    </form>
    </div>
</body>
</html>