<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="account.bean.*"%>

<%
	// SE L'UTENTE E' GIA' AUTENTICATO
	Utente u = (Utente) session.getAttribute("utente");
	if(u != null) {
		response.sendRedirect(request.getContextPath() + "/Login");
	}
%>

<!DOCTYPE html>
<html>
<head>
<title>Accesso</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Account/css/login.css">

<!-- JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/Account/js/login.js"></script>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<!-- font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>

<body>
	<header>
		<a id="logo" href="<%=request.getContextPath()%>/index.jsp">FOCUS PROJECT</a>
	</header>
	
	<!-- Stampiamo eventuali notifiche di errore -->
	<%
	String notification = (String) request.getAttribute("notification");
	if (notification != null && !notification.isEmpty()) {
	%>
	<div id="notification" class="notification">
		<span><%=notification%></span>
		<button onclick="closeNotification()">Chiudi</button>
	</div>
	<%
	}
	%>
	
	<form id="log_in" action="<%=request.getContextPath()%>/Login" method="POST" onsubmit="return validateForm()">
		<div id="loginBox">
			<p class="lead text-center">
				<strong>ACCEDI</strong>
			</p>
			<hr>
			<div class="container">
				<div class="row text-center">
					<div class="col-sm-12">
						<!-- Campo in cui inserire l'E-mail -->
						<input class="email1" type="text" placeholder="email" name="email" required>
						<p class="error-message" id="errorMessageEmail"></p>
					</div>
					<div class="col-sm-12">
						<!-- Campo in cui inserire la Password -->
						<input id="passwordInput" type="password" placeholder="Password" name="password" required>
						<button id="showPass" type="button" onclick="togglePasswordVisibility()">Mostra password</button>
						<p class="error-message" id="errorMessagePassword"></p>
					</div>
					<div class="col-sm-12">
						<hr>
						<br>
						<input type="submit" value="Accedi" class="btns">
					</div>
					<div class="col-sm">
						<br>
						<p class="lead">
							Non hai ancora un account?
							<a href="<%=request.getContextPath()%>/Account/signin.jsp" target="_self">Registrati</a>
						</p>
					</div>
				</div>
			</div>
			<br></br>
		</div>
	</form>

	<footer class="footer">
		<div class="container">
			<nav>
				<p class="copyright">
					(Copyright
					<script>
						document.write(new Date().getFullYear())
					</script>) <a href="">Focus Project.</a> Tutti i diritti sono riservati.
				</p>
			</nav>
		</div>
	</footer>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
