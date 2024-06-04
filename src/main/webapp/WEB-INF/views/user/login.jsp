<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>
<%
String msg = (String) request.getAttribute("msg");
Cookie cookies[] = request.getCookies();
Cookie c;
String s = "";
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if ("ssafy_id".equals(cookie.getName())) {
	c = cookie;
	s = c.getValue();
	break;
		}
	}
}
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>구해줘 홈즈!</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Varela+Round"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
</head>
<header>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
</header>
<body id="page-top">
	<section class="about-section text-center">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-8">
					<h2 class="text-black mb-4">Login</h2>
					<form method="post" action="${root }/user/login">
						<a
							class="text-danger"><%=msg != null ? msg : ""%></a><br>
						<div class="form-group">
							<label for="userId" class="fs-5 text-black">ID</label> <input
								type="text" id="userId" name="userId" value="<%=s%>"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label for="userPwd" class="fs-5 text-black">Password</label> <input
								type="password" id="userPwd" name="userPwd" class="form-control"
								required>
						</div>
						<div>
							<button type="submit" class="btn btn-secondary btn-sm mb-4">로그인</button>
							<button type="reset" class="btn btn-secondary btn-sm mb-4">초기화</button>
						</div>
						<label for="saveid">아이디 기억하기 </label> <input type="checkbox"
							value="ok" id="saveid" name="saveid" />
					</form>
					<a href="${root }/user/join">회원가입</a>
					<a href="${root }/user/findpassword">비밀번호를 모르겠습니다</a>
				</div>
			</div>
		</div>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script src="https://smtpjs.com/v3/smtp.js"></script>
	</section>
	<!-- Footer-->
	<!-- Footer-->
	<footer class="footer small text-center text-black-50"
		style="background-color: white">
		<div class="container px-4 px-lg-5">Copyright &copy;
			SSAFY_서울_17반_김준혁_정종희</div>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

</body>

</html>