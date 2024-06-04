<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="com.ssafy.member.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
session = request.getSession();
MemberDto member = (MemberDto) session.getAttribute("userinfo");
%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
<link href="${root}/assets/css/styles.css" rel="stylesheet" />
</head>

<body id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="${root}/">구해줘 홈즈!</a>
			<% if(member != null) {%>
			<span>안녕하세요! <%=member.getUserName()%> 님</span>
			<% } %>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<%
			if (member != null) {
			%>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/profile">프로필</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/board/mvboard">게시판</a></li>
						<%
						if (member.getIsAdmin().equals("1")) {
						%>
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/userlist">검색</a></li>
						<%} %>
				</ul>
			</div>
			<%
			} else {
			%>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/join">회원가입</a></li>
				</ul>
			</div>
			<%
			}
			%>
		</div>
	</nav>
</body>