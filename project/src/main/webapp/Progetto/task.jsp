<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List, progetto.bean.*, account.bean.*, java.util.*"%>

<%
	Utente user = (Utente) session.getAttribute("utente");

	if(user == null) {
	    response.sendRedirect(request.getContextPath() + "LoadTask");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Task</title>
	
	<!-- CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"  crossorigin="anonymous">
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css">	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/Progetto/css/task.css">			  

	<!-- JavaScript -->
	<script type="text/javascript" src="<%= request.getContextPath()%>/Progetto/js/task.js"></script>
	
	<!-- font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="homePage.jsp">FOCUS PROJECT</a>
	</header>

	<jsp:include page="../navbar.jsp" />

	<br>

	<!-- codice qui ... -->
	<%
		String ruolo = user.getRuolo();	
		int progettoId = (int) request.getAttribute("progettoId");
		boolean isFinish = (boolean) request.getAttribute("isFinish");
		List<?> subordinati = (List<?>) request.getAttribute("subordinati");
		Map<String, List<Task>> taskProgetto = (Map<String, List<Task>>) request.getAttribute("taskProgetto");
	%>
	<div class="container text-center">
		<button onclick="window.location.href='<%=request.getContextPath()%>/LoadProjects?id=<%= progettoId%>'" class="btn btn-danger">Torna al progetto</button>
	</div>
	<br>
	<%
		if(subordinati != null && !subordinati.isEmpty()) {
			for (Object o : subordinati) {
				if (o != null && o instanceof Utente) {
				Utente u = (Utente) o;
				if (u.getEmail().equals(user.getEmail()) || !ruolo.equals("subordinato")) {
	%>
					<div class="container">
				
						<div class="subordinato-container">
							<p class="subordinato-nome">
								Nome: <%= u.getNome()%></p>
					
							<!-- Pulsante Apri Form -->	
							<%if(!isFinish) {
								if(ruolo.equals("responsabile")){
							%>
							
							<div class="form-buttons">
								<button class="apri-form" type="button" onclick="mostraForm('<%=u.getEmail()%>_form')">Apri Form</button>
							</div>
							<%}} %>
					
							<!-- Form per Aggiungere Task (inizialmente nascosto) -->
							<form id="<%=u.getEmail()%>_form"
								action="<%=request.getContextPath()%>/AddTask" method="post" style="display: none;">
								<input type="hidden" name="progettoId" value="<%=progettoId%>">
								<input type="hidden" name="utenteId" value="<%=u.getEmail()%>">
								<label for="<%=u.getEmail()%>_descrizione">Descrizione:</label>
								<input type="text" id="<%=u.getEmail()%>_descrizione" name="descrizione" required>
								<div class="form-buttons">
									<button class="aggiungi-task" type="submit">Aggiungi Task</button>
								</div>
							</form>
					
							<!-- Visualizzazione dei task associati a questo subordinato -->
							<ul class="task-list">
								<%
									List<Task> taskUtente = taskProgetto.get(u.getEmail());
									if (taskUtente != null) {
										for (Task task : taskUtente) {
								%>
										<li><%=task.getDescrizione()%> <!-- Pulsante Rimuovi Task -->
											<form class="form-buttons" action="<%=request.getContextPath()%>/RemoveTask" method="post" style="display: inline;">
												<input type="hidden" name="progettoId" value="<%=progettoId%>">
												<input type="hidden" name="taskId" value="<%=task.getIdTask()%>">
												<input type="hidden" name="utenteId" value="<%=u.getEmail()%>">
												<%if(!task.isStato()) {
													if(ruolo.equals("responsabile")){
												%>
													<button id="rimuovi-task" name="action" value="rimuoviTask" type="submit">Rimuovi Task</button>
												<%	} else if(ruolo.equals("subordinato"))	{%>
													<button id="completa-task" name="action" value="completaTask" type="submit">Completa Task</button>
												 <%  }
												  }else {%>		
													<h4>Task Completato</h4>
												<%}%>				
											</form>
										</li>
								<%
										}
									}
								%>
							</ul>
						</div>
					</div>
	<%
					}
				}
			}
		}
		else {
		%>
		<p>Il progetto non ha subordinati assegnati</p>
		<%
		}
	%>

	<br>
	<br>	
	<br>
		
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	
</body>
</html>
