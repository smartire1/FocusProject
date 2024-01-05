<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="js/login.js"></script>
	<link type="text/css" rel="stylesheet" href="css/login.css">
	<title>Accesso</title>
</head>

<body>
	<header>
	<div id="logo">
			<img src="imgs/.Logo.png">
		</div>
		</header>
	
		
		<div id="loginBox" class="mx-auto">
			<div class="container">
				<p class="lead text-center"><strong>LOGIN</strong></p>
				<form name="log_in" action="<%=request.getContextPath()%>/LoginServlet" method="POST" onsubmit="return validateForm()">
					<div class="container">
						<div class="row text-center">
							<div class="col-sm-12">
								<input class="email1" type="text" placeholder="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
								<p class="error-message" id="errorMessageEmail"></p>
							</div>
							<div class="col-sm-12">
								<input type="password" placeholder="Password" name="pass"/>
								<p class="error-message" id="errorMessagePassword"></p>
							</div>
							<div class="col-sm-12">
								<br>
								<input type="submit" value="Accedi" class="btn btn-danger btn-sm" />
							</div>
							<div class="col-sm">
								<br>
								<br>
								<p class="lead">Non sei registrato? <a href="signin.jsp" target="_self">Registrati</a></p>
							</div>
						</div>
					</div>
				</form>
				<br></br>
			</div>
		</div>
		
	
	
	
</body>
</html>
