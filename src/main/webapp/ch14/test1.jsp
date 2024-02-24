<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>카운트다운</title>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function() {
        const countDownTimer = function(id, date) {
            let _vDate = new Date(date); // 전달 받은 일자
            let _second = 1000;
            let _minute = _second * 60;
            let _hour = _minute * 60;
            let _day = _hour * 24;
            let timer;

            function showRemaining() {
                let now = new Date();
                let distDt = _vDate - now;

                if (distDt < 0) {
                    clearInterval(timer);
                    $('#' + id).text('경매가 종료 되었습니다!');
                    return;
                }

                let days = Math.floor(distDt / _day);
                let hours = Math.floor((distDt % _day) / _hour);
                let minutes = Math.floor((distDt % _hour) / _minute);
                let seconds = Math.floor((distDt % _minute) / _second);
                $('#' + id).text(days + '일 ' + hours + '시간 ' + minutes + '분 ' + seconds + '초');
                	$('#secondsDisplay').val(seconds); // seconds 값을 표시
                	
            }

            timer = setInterval(showRemaining, 1000);
        }

       	let dateObj = new Date();
        dateObj.setDate(dateObj.getDate() + 1);
        <%--      let dateObj = '2024/02/24 18:27' --%>

        countDownTimer('countdown', dateObj); 
    });
	
	function submitForm() {
	    // 폼 생성
	    let form = $("<form>")
	                .attr("method", "post")
	                .attr("action", "/jw/test1/tes1/item");

	    // hidden input 요소를 폼에 추가
	    let hiddenInput = $("<input>")
	                        .attr("type", "hidden")
	                        .attr("name", "countdownEnd")
	                        .val($("#countdownEnd").val());
	    
	    // 폼에 hidden input 요소 추가
	    form.append(hiddenInput);

	    // 폼을 body에 추가하고 제출
	    $("body").append(form);
	    form.submit();
	}
	</script>
</head>
<body>
	<h1>Sample : 카운트다운</h1>
    <h2 id="countdown"></h2>
    <input type="text" id="secondsDisplay" name="countdownEnd" >
</body>
</html>