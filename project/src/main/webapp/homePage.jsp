<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<!-- CSS -->
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/homePage.css">

<!-- font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="index.jsp">FOCUS PROJECT</a>
		<div class="buttons">
			<a id="logoutButton" href="<%= request.getContextPath()%>/Logout">Esci</a>
		</div>
	</header>
	
	<div id="menuContainer">
		<div id="buttonsContainer">
			<a href="Account/userArea.jsp" class="button">Area Personale</a>
			<a href="Dipendenti/employeeDashboard.jsp" class="button">Dashboard Dipendenti</a>
			<a href="" class="button">Dashboard Progetti</a>
			<a href="" class="button">Statistiche</a>
			<a href="" class="button">Comunicazioni</a>
		</div>
	</div>
	
	<footer class="footer">
		<div class="container">
			<nav>
				<p class="copyright">
					(Copyright
					<script>
						document.write(new Date().getFullYear())
					</script>
					) <a href="">Focus Project.</a> Tutti i diritti sono riservati.
				</p>
			</nav>
		</div>
	</footer>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>

</body>
</html>