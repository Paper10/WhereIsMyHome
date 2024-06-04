<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ssafy.member.model.MemberDto, java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
List<MemberDto> list = (List<MemberDto>) request.getAttribute("lists");
%>
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
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37e54fadcf61dad2a530215fd5209ca6&libraries=services,clusterer,drawing"></script>
</head>
<header>
	<%@ include file="/WEB-INF/views/nav.jsp"%>
</header>

<!-- Masthead-->
<body class="page-top">
	<div
		class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
		<div class="d-flex justify-content-center">
			<div class="col-md-12">
				<div class="alert mt-3 text-center fw-bold" role="alert"></div>
				<div class="alert alert-success mt-3 text-center fw-bold" role="alert">회원 정보</div>
				<form method="post" action="${root}/user/userlist" name="userlist">
					<div class="row col-md-12 justify-content-center mb-2">
						<input type="text" name="searchText" id="searchText">
						<div class="form-group col-md-2" style="justify-content: right;">
							<button type="submit" id="list-btn" class="btn btn-outline-primary">검색</button>
						</div>
					</div>
				</form>

				<table class="table table-hover text-center">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
					</tr>
					<tbody id="userlist">
						<%
						if (list != null) {
							for (MemberDto dto : list) {
						%>
						<tr class="text-center">
							<td><%=dto.getUserId()%></td>
							<td><%=dto.getUserName()%></td>
							<td><%=dto.getEmailDomain()%></td>
							<td><%=dto.getUserPwd()%></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</script>

</html>