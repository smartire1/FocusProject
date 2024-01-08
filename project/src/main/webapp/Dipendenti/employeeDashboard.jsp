<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard Dipendenti</title>

	<!-- Bootstrap -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		crossorigin="anonymous">

	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="css/employeeDashboard.css">

	<!-- JavaScript -->
	<script type="text/javascript" src="js/employeeDashboard.js"></script>
	
	<!-- font -->
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="../homePage.jsp">FOCUS PROJECT</a>
	
			<a id="PareaBtn"href="../Account/userArea.jsp" class="btns">Area Personale</a>			
		
			<div class="buttons">
				<a id="logoutButton" href="<%= request.getContextPath()%>/Logout">Esci</a>
			</div>
		
	</header>
	<div class="container">
		<div class="row">
			<div class="container-fluid">
				<!-- Sidebar con pulsanti -->
				<div class="col-md-3 col-lg-2 sidebar">
					<button id="showAddEmployee" class="button"
						onclick="showContent('content1')">Aggiungi Dipendente</button>
					<button id="showRemoveEmployee" class="button"
						onclick="showContent('content2')">Rimuovi Dipendente</button>
					<button id="showAddShift" class="button"
						onclick="showContent('content3')">Aggiungi Turni</button>
					<button id="showRemoveShift" class="button"
						onclick="showContent('content4')">Rimuovi Turni</button>
				</div>
	
				<!-- Contenitore del testo dinamico -->
				<div class="col-md-9 col-lg-10">
					<div id="contentContainer">
						<!-- Contenuto dei div associati ai pulsanti -->
						<div id="content1" class="active">
							<form id="addEmployeeForm">
								<label for="nome">Nome:</label> <input type="text" id="nome"
									name="nome" required> <label for="cognome">Cognome:</label>
								<input type="text" id="cognome" name="cognome" required>
	
								<label for="email">Email:</label> <input type="email" id="email"
									name="email" required> <label>Ruolo:</label> <label
									for="responsabile"> <input type="radio"
									id="responsabile" name="ruolo" value="responsabile">
									Responsabile
								</label> <label for="subordinato"> <input type="radio"
									id="subordinato" name="ruolo" value="subordinato">
									Subordinato
								</label>
	
								<button type="submit">Aggiungi Dipendente</button>
							</form>
						</div>
	
						<div id="content2" class="hidden">
							<p>Rimuovi dipendente</p>
	
						</div>
						<div id="content3" class="hidden">
							<p>Aggiungi turno</p>
	
						</div>
						<div id="content4" class="hidden">
							<p>Rimuovi turno</p>
						</div>
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