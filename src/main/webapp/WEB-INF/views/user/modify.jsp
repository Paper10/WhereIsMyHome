<%@page import="com.ssafy.member.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
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
    <section class="about-section text-center">
        <div class="container mt-5">
            <h2 class="text-black mb-4">Modify</h2>
            <form id="registerForm" method="post" action="${root }/user/modify">
                <div class="form-group">
                    <label for="userId" class="fs-5 text-black">ID:</label>
                    <input type="text" id="userId" name="userId" value=<%=member.getUserId()%> class="form-control" readonly />
                </div>
                <div class="form-group">
                    <label for="userName" class="fs-5 text-black">Name:</label>
                    <input type="text" id="userName" name="userName" value=<%=member.getUserName()%> class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="emailDomain" class="fs-5 text-black">Email:</label>
                    <input type="text" id="emailDomain" name="emailDomain" value=<%=member.getEmailDomain()%> class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="userPwd" class="fs-5 text-black">Password:</label>
                    <input type="password" id="userPwd" name="userPwd" value=<%=member.getUserPwd()%> class="form-control" required>
                </div>
                <button type="submit" class="btn btn-secondary btn-sm mb-4">정보수정</button>
            </form>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        
    </section>
    <!-- Footer-->
    <footer class="footer small text-center text-black-50" style="background-color:white">
        <div class="container px-4 px-lg-5">Copyright &copy; SSAFY_서울_17반_김준혁_정종희 </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- * *                               SB Forms JS                               * *-->
    <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>

</html>