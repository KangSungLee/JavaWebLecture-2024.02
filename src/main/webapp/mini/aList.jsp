<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script>
	function twoDigit(num) {
        return (num < 10) ? '0' + num : String(num);          
    }                                                       

    function myDateTime(date) {
        return '' + date.getFullYear() + '-' + twoDigit(date.getMonth() + 1) + '-' + twoDigit(date.getDate()) + ' ' +
            twoDigit(date.getHours()) + ':' + twoDigit(date.getMinutes()) + ':' + twoDigit(date.getSeconds());
    }

    window.onload = function () {
        setInterval(function () {
            const modTimes = document.querySelectorAll('.time_display');
            modTimes.forEach(function (modTime, index) {
                const targetDateTime_ = modTime.previousElementSibling.value.replace("T", " ");
                const targetDateTime = targetDateTime_.replace("-", "/");
                const now = new Date();
    
                const timeDifference = new Date(targetDateTime).getTime() - now.getTime();
                let timeStr = '';
    
                if (timeDifference > 0) {
                    const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
                    const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
                    const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
    
                    timeStr = days + '일' + twoDigit(hours) + ':' + twoDigit(minutes) + ':' + twoDigit(seconds);
                } else {
                    timeStr = '지정된 시간이 이미 지났습니다.';
                }
    
                modTime.innerHTML = timeStr;
            });
        }, 1000);
    }; 
    </script>
</head>
<body>
	<table class="table" border="1" style="width: 800px">
		<tr>
			<th style="width: 8%">번호</th>
			<th style="width: 18%">경매기간</th>
			<th style="width: 41%">제목</th>
			<th style="width: 18%">현재가</th>
			<th style="width: 15%">유저명</th>
		</tr>
		<c:forEach var="auctions" items="${auctionsList}" varStatus="loop">
		<tr>
			<td>${auctions.auction_id}</td>
			<input type="hidden" id="modTime_${loop.index}" value="${auctions.end_time}">
			<td class="time_display" id="time_${loop.index}"></td>
			<td>
				<a href="/jw/mini/aDetail?auction_id=${auctions.auction_id}">${auctions.title}</a>
			</td>
			<td>${auctions.current_price}</td>
			<td>${auctions.equipment_id}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>