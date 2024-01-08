<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home Page</title>
	
	<!-- CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"  crossorigin="anonymous">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/homePage.css">
	
	<!-- font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="index.jsp">FOCUS PROJECT</a>					
	</header>
	
	<jsp:include page="navbar.jsp" />
	
	<div id="menuContainer">
		<div id="buttonsContainer">

			<a href="Dipendenti/employeeDashboard.jsp" class="button">Dashboard Dipendenti</a>
			<a href="Progetto/projectDashboard.jsp" class="button">Dashboard Progetti</a>
			<a href="" class="button">Statistiche</a>
			<a href="Comunicazioni/communicationDashboard.jsp" class="button">Comunicazioni</a>
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