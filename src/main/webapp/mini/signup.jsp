<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입</title>
</head>
<body class="bg-light">
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container-fluid">
      <img src="/jw/img/ck-logo.png" height="60">
      <div class="p-2 bg-dard justify-content-center">
        <img src="https://picsum.photos/1500/180" width="100%">
      </div>
    </div>
  </nav>
  <div class="container" style="margin-top: 220px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title"><h3><strong>회원 가입</strong></h3></div>
            <hr>
            <form action="/jw/auctiondb/user/signup" method="post">
              <table class="table table-borderless">
                <tr>
                  <td style="width: 45%;"><label class="col-form-label">ID:</label></td>
                  <td style="width: 55%;"><input type="text" name="user_id" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">PassWord:</label></td>
                  <td><input type="password" name="password" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">PassWord check:</label></td>
                  <td><input type="password" name="password2" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">이름:</label></td>
                  <td><input type="text" name="uname" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">Email:</label></td>
                  <td><input type="text" name="email" class="form-control"></td>
                </tr>
		 		<tr>
                  <td><label class="col-form-label">전화번호</label></td>
                  <td><input type="text" name="phone_number" class="form-control" required></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">회원가입</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3">
              <span class="me-3">이미 사용자 계정이 있으신가요?</span>
              <a href="/jw/auctiondb/users/login">로그인</a>
            </p>
            <div class="mt-3 mb-3">
              <span class="me-3">소셜 계정으로 가입</span>
              <span>
                <a class="ms-2" href="#"><img src="/jw/img/google-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/github-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/naver-logo.jpg" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/kakao-logo.png" height="32"></a>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  
  <%@ include file="../common/_bottom.jspf" %>
</body>
</html>