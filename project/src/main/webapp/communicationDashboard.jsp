<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard Comunicazioni</title>

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="communicationDashboard.css">

<!-- JavaScript -->
<script type="text/javascript" src="communicationDashboard.js"></script>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<!-- font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">

</head>
<body>
	<header>
		<a id="logo" href="index.jsp">FOCUS PROJECT</a>
		<div class="buttons">
			<a id="logoutButton" href="<%=request.getContextPath()%>/Logout">Esci</a>
		</div>
	</header>

	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar con pulsanti -->
			<div class="col-md-3 sidebar">
				<button id="showAddNews" class="button"
					onclick="showContent('content1')">Inserire News</button>
				<button id="showPermissionManagement" class="button"
					onclick="showContent('content2')">Gestione Permessi</button>
			</div>

			<!-- Contenitore del testo dinamico -->
			<div class="col-md-9">
				<div id="contentContainer">
					<!-- Contenuto dei div associati ai pulsanti -->
					<div id="content1" class="active">
						<form id="addNewsForm">
							<label for="nome">Titolo:</label> 
							<input type="text" id="nome" name="nome" required>
							 <label for="cognome">Testo:</label>
							<input type="text" id="cognome" name="cognome" required>

							<button type="submit">Invia</button>
						</form>
					</div>

					<div id="content2" class="hidden">
						<p>Gestione Permessi</p>

				</div>
				</div>
			</div>
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