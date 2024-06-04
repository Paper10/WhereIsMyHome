<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.vue.apt.model.AptDto, java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
List<AptDto> list = (List<AptDto>) request.getAttribute("lists");
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
	<%@ include file="/WEB-INF/views/nav.jsp" %>
</header>

<!-- Masthead-->
<body class="page-top">
	<div
		class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
		<div class="d-flex justify-content-center">
			<div class="col-md-12">
				<div class="alert mt-3 text-center fw-bold" role="alert"></div>
				<div class="alert alert-success mt-3 text-center fw-bold"
					role="alert">아파트 정보</div>
				<!-- 아파트 매매 정보 검색 start -->
				<form method="post" action="" name="selform">
					<div class="row col-md-12 justify-content-center mb-2">
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="sido" name="sido">
								<option value="">시도선택</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="gugun" name="gugun">
								<option value="">구군선택</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="dong" name="dong">
								<option value="">동선택</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-dark text-light" id="year" name="year"></select>
						</div>
						<div class="form-group col-md-2">
							<select class="form-select bg-dark text-light" id="month" name="month">
								<option value="">매매월선택</option>
							</select>
						</div>
						<div class="form-group col-md-2" style="justify-content: right;">
							<button type="submit" id="list-btn" formaction="${root}/apt/list"
								class="btn btn-outline-primary">검색</button>
						</div>
						<div class="form-group col-md-2" style="justify-content: right;">
							<button type="submit" id="list-btn2" formaction="${root}/apt/recommend"
								class="btn btn-outline-primary">추천</button>
						</div>
						<% if(member != null) {%>
						<div class="form-group col-md-2" style="justify-content: right;">
							<button type="submit" id="list-btn2" formaction="${root}/apt/registprefer"
								class="btn btn-outline-primary">관심등록</button>
						</div>
						<% } %>
					</div>
				</form>
				<!-- kakao map start -->
				<div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
				<!-- kakao map end -->
				
				<table class="table table-hover text-center" >
					<tr>
						<th>아파트이름</th>
						<th>층</th>
						<th>면적</th>
						<th>법정동</th>
						<th>거래금액</th>
					</tr>
					<tbody id="aptlist">
					<% if(list != null) {
						for (AptDto dto : list) {
					%>
					   
	              <tr class="text-center" onclick="view(<%= dto.getLat() %>,<%= dto.getLng() %>)">
	                <td><%= dto.getApartmentName() %></td>
	                <td><%= dto.getFloor() %></td>
	                <td><%= dto.getArea() %></td>
	                <td><%= dto.getDong() %></td>
	                <td><%= dto.getDealAmount() %></td>
	                </tr>
	               <%}
					}%>
					</tbody>
				</table>
				<!-- 아파트 매매 정보 검색 end -->
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
<script type="text/javascript" src="${root}/assets/js/aptjs.js">
	
</script>

</html>