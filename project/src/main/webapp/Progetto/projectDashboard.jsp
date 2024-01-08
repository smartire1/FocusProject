<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard Progetti</title>

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="../css/style.css">
<link type="text/css" rel="stylesheet" href="css/projectDashboard.css">

<!-- JavaScript -->
<script type="text/javascript" src="js/projectDashboard.js"></script>

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
		<a id="logo" href="../index.jsp">FOCUS PROJECT</a>
		<div class="buttons">
			<a id="logoutButton" href="<%=request.getContextPath()%>/Logout">Esci</a>
		</div>
	</header>

	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar con pulsanti -->
			<div class="col-md-12 text-center">
				<button id="showAddProject" class="button larger-button"
					onclick="showContent('content1')">Aggiungi Progetto</button>
				<button id="showOngoingProject" class="button larger-button"
					onclick="showContent('content2')">Progetti in corso</button>
				<button id="showCompletedProjects" class="button larger-button"
					onclick="showContent('content3')">Progetti conclusi</button>
			</div>

			
			<div class="col-md-12">
				<div id="contentContainer">
					<div id="content1" class="active">
			   			<form id="addProjectForm">
			   				<h5> Aggiungi un nuovo Progetto</h5>
							<label for="nome">Nome Progetto:</label> 
								<input type="text" id="nome" name="nome" required>
							<label for="cognome">Responsabile:</label>
								<input type="text" id="responsabile" name="responsabile" required>
							<label for="email">Descrizione:</label> 
								<input type="text" id="descrizione" name="descrizione" required>
							<label for="email">Obiettivi:</label> 
								<input type="text" id="obiettivi" name="obiettivi" required>
								<button type="submit">Aggiungi Progetto</button>
						</form>
					</div>
					
					<div id="content2" class="hidden">
						<h5>Progetti in corso</h5>
				</div>
					<div id="content3" class="hidden">
						<h5>Progetti conclusi</h5>
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