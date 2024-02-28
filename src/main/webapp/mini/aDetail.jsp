<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경매</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://kit.fontawesome.com/1072b7cb5b.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
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
            	const modTime = document.getElementById('modTime').value;
            	const targetDateTime_ = modTime.replace("T", " ");
            	const targetDateTime = targetDateTime_.replace("-", "/");
                const now = new Date();

                const timeDifference = new Date(targetDateTime).getTime() - now.getTime();
                let timeStr = '';

                const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
                const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
                const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
                
                if (days == 0 && timeDifference > 0) {
                	timeStr = twoDigit(hours) + ':' + twoDigit(minutes) + ':' + twoDigit(seconds);
                } else if(timeDifference > 0){
                    timeStr = days + '일' + twoDigit(hours) + ':' + twoDigit(minutes) + ':' + twoDigit(seconds);
                } else {
                	timeStr = '경매 종료';
                }

                document.getElementById('time').innerHTML = timeStr;

            }, 1000);
        }
        
        function openAuctionsModal() {
            const current_price = "${auctions.current_price}";
            const aPrice = document.getElementById('aPrice').value;
            if (parseInt(current_price) <= parseInt(aPrice)) {
                $('#notAuctionsModal').modal('show');
            } else {
                $('#auctionsModal').modal('show');
                $('#current_price').val(aPrice);
            }
        }
    </script>
</head>

<body style="margin: 50px;">
	<div class="container">
		<div class="row">
			<div class="col-9"></div>
			<div class="col-3">
				<h3>
					<span style="font-size:16px">
						<a href="/jw/mini/aList"><i class="fa-solid fa-table-list"></i>목록</a>
						<c:if test="${sessUid eq auctions.auction_id or sessUid eq 'admin'}">	<!-- 본인만 수정/삭제 가능 -->
							<a href="javascript:deleteFunc('${board.bid}')"><i class="fa-solid fa-trash ms-3"></i> 삭제</a>
						</c:if>
						<c:if test="${sessUid ne auctions.auction_id}">	
							<a href="#" class="disabled-link"><i class="fa-solid fa-trash ms-3"></i> 삭제</a>
						</c:if>
					</span>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-5"></div>
			<div class="col-5">
			    <input type="hidden" id="modTime" value="${auctions.end_time}">
			    <table class="table table-borderless">
			    	<tr>
				    	<td colspan="2"><h3 id="time"></h3></td>
				    </tr>
				    <tr>
				    	<td colspan="2"><h3><strong>${auctions.title}</strong></h3></td>
				    </tr>
				    <tr>
				    	<td>${auctions.user_id}</td>
				    </tr>
	                <tr>
						<td style="width: 30%;"><label class="col-form-label">시작가</label></td>
						<td style="width: 70%;"><fmt:formatNumber value="${auctions.start_price}" type="number" pattern="#,##0"></fmt:formatNumber></td>
	                </tr>
	                <tr>
						<td><label class="col-form-label">현재가</label></td>
						<td><fmt:formatNumber value="${auctions.current_price}" type="number" pattern="#,##0"></fmt:formatNumber></td>
	                </tr>
	                <tr>
						<td><label class="col-form-label">할인된 금액</label></td>
						<td><fmt:formatNumber value="${discount}" type="number" pattern="#,##0"></fmt:formatNumber></td>
	                </tr>
	                <c:if test="${secondsInt > 0}">
		                <form action="/jw/mini/auctions" method="post">
			                <tr>
								<td><label class="col-form-label">제시 금액</label></td>
								<td><input type="text" class="form-control" id="aPrice"></td>
			                </tr>
			                <tr>
			                	<td>
			                <%--	<input type="text" name="seller_id" value="${sessUid}">  --%>
			                		<input type="text" name="seller_id" value="maria">
			                	</td>
				                <td colspan="1">
				              		<a href="javascript:openAuctionsModal()"><i class="fa-solid fa-trash ms-3"></i> 경매</a>
				                </td>
			                </tr>
						</form>
						</c:if>
						<c:if test="${secondsInt < 0}">
							<tr>
								<td colspan="2"><h2>경매가 종료 되었습니다</h2></td>
							</tr>
						</c:if>
            	</table>
			</div>
			<div class="col-1"></div>
		</div>
    	<div class="row">
    		<div class="col-2"></div>
    		<div class="col-8">
    			${auctions.content}
    		</div>    		
    		<div class="col-2"></div>
    	</div>
    </div>
    
    <%-- 밑으로는 모달 --%>
    
    <div class="modal" id="auctionsModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">경매</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
			
				<!-- Modal body -->
				<div class="modal-body">
					<strong>경매에 참여하시겠습니까?</strong>
					<div class="text-center mt-5">
						<form action="/jw/mini/auctions" method="post">
				<%-- 		<input type="text" name="seller_id" value="${sessUid}">  --%>
							<input type="text" name="seller_id" value="maria">
							<input type="hidden" id="current_price" name="current_price">
							<input type="text" name="auction_id" value="${auctions.auction_id}">
							<button class="btn btn-danger" type="submit">확인</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="notAuctionsModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">경매</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
			
				<!-- Modal body -->
				<div class="modal-body">
					<strong>금액이 잘못되었습니다.</strong>
					<div class="text-center mt-5">
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>