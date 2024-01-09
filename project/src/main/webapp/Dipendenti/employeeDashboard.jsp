<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="account.bean.*"%>
<%@ page import="java.util.Collection"%>

<%
Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
Collection<?> subordinati = (Collection<?>) request.getAttribute("subordinati");

if (responsabili == null || subordinati == null) {
	response.sendRedirect(request.getContextPath() + "/LoadEmployees");
	return;
}
%>

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
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/Dipendenti/css/employeeDashboard.css">

<!-- JavaScript -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Dipendenti/js/employeeDashboard.js"></script>

<!-- font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="../homePage.jsp">FOCUS PROJECT</a>
	</header>

	<jsp:include page="../navbar.jsp" />

	<div class="container">
		<div class="row">
			<div class="container-fluid1">
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
							<h3>Rimuovi dipendente</h3>
							<%
							try {
								if (responsabili != null) {
									if (!responsabili.isEmpty()) {
								for (Object obj : responsabili) {
									Utente responsabile = (Utente) obj;
							%>

							<form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
								<input type="hidden" name="email"
									value="<%=responsabile.getEmail()%>">
								<p>
									Responsabile:
									<%=responsabile.getNome()%>
									<%=responsabile.getCognome()%>
									-
									<%=responsabile.getEmail()%>
									<button type="submit">Rimuovi</button>
								</p>
							</form>

							<%
							}
							}
							}
							%>

							<%
							if (subordinati != null) {
								if (!subordinati.isEmpty()) {
									for (Object obj : subordinati) {
								Utente subordinato = (Utente) obj;
							%>

							<form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
								<input type="hidden" name="email"
									value="<%=subordinato.getEmail()%>">
								<p>
									Subordinato:
									<%=subordinato.getNome()%>
									<%=subordinato.getCognome()%>
									-
									<%=subordinato.getEmail()%>
									<button type="submit">Rimuovi</button>
								</p>
							</form>

							<%
							}
							}
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
						</div>
						<div id="content3" class="hidden">
							<h3>Aggiungi turno</h3>

						</div>
						<div id="content4" class="hidden">
							<h3>Rimuovi turno</h3>
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