<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>구해줘 홈즈!</title>
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
	<%@ include file="/WEB-INF/views/nav.jsp" %>
</header>
<body id="page-top">
	<!-- Masthead-->
	<header class="masthead" style="background: linear-gradient(to bottom, rgba(0, 0, 0, 0.1) 100%, rgba(0, 0, 0, 0.1) 100%, #000 100%), url(${root}/assets/css/bg-masthead.jpg)">
		<div
			class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
			<div class="d-flex justify-content-center">
				<div class="text-center">
					<h1 class="mx-auto my-0 text-uppercase">구해줘 홈즈!</h1>
					<h2 class="text-white-50 mx-auto mt-2 mb-5">아파트 실거래가 정보 조회</h2>
					<a class="btn btn-primary" href="${root}/apt/list"
						style="border: 2px solid rgb(128, 128, 128); width: 80%; text-align: left;">
						<img src="${root}/assets/css/search.png">
					</a>
				</div>
			</div>
		</div>
	</header>

	<!-- Footer-->
	<footer class="footer small text-center text-black-50"
		style="background-color: white">
		<div class="container px-4 px-lg-5">Copyright &copy;
			SSAFY_서울_17반_김준혁_정종희</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

</body>
</html>
