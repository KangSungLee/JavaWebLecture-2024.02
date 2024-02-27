<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>디지털 시계</title>
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

                if (timeDifference > 0) {
                    const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
                    const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
                    const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
                
                    timeStr = '남은 시간: ' + days + '일 ' + hours + '시간 ' + minutes + '분 ' + seconds + '초';
                } else {
                    timeStr = '지정된 시간이 이미 지났습니다.';
                }

                document.getElementById('time').innerHTML = timeStr;

            }, 1000);
        }
    </script>
</head>

<body style="margin: 50px;">
    <h1 id="time"></h1>
    <h1>테스트</h1>
    <input type="hidden" id="modTime" value="${modTime}">
</body>
</html>