<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="account.bean.*"%>
<%@ page import="java.util.Collection"%>

<%
	Collection<?> responsabili = null;
	Collection<?> subordinati = null;
	Utente u = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard Dipendenti</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		crossorigin="anonymous">
	
	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Dipendenti/css/employeeDashboard.css">
	
	<!-- JavaScript -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Dipendenti/js/employeeDashboard.js"></script>
	
	<!-- font -->
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="<%=request.getContextPath()%>/homePage.jsp">FOCUS PROJECT</a>
	</header>

	<jsp:include page="../navbar.jsp" />

	<div class="container">
		<div class="row">
			<div class="container-fluid1">
				<!-- Sidebar con pulsanti -->
				<div class="col-md-3 col-lg-2 sidebar">
					<% if(u.getRuolo().equals("dirigente")) { %>
						<button id="showAddEmployee" class="button"
							onclick="showContent('content1')">Aggiungi Dipendente</button>
						<button id="showRemoveEmployee" class="button"
							onclick="showContent('content2')">Rimuovi Dipendente</button>
					<% } %>
					<button id="showAddShift" class="button"
						onclick="showContent('content3')">Aggiungi Turni</button>
					<button id="showRemoveShift" class="button"
						onclick="showContent('content4')">Rimuovi Turni</button>
				</div>

				<!-- Contenitore del testo dinamico -->
				<div class="col-md-9 col-lg-10">
					<div id="contentContainer">
						<!-- Contenuto dei div associati ai pulsanti -->
						<div id="content1" class="hidden">
							<form id="addEmployeeForm">
								<label for="nome">Nome:</label> 
								<input type="text" id="nome"name="nome" required>
									 
								<label for="cognome">Cognome:</label>
								<input type="text" id="cognome" name="cognome" required>

								<label for="email">Email:</label> 
									<input type="email" id="email" name="email" required> <label>Ruolo:
								</label>
								
								<div class="row">
									<div class="col-2">
										<input type="radio" id="responsabile" name="ruolo" value="responsabile">										
									</div>
									<div class="col-4">			
										<label for="responsabile">Responsabile</label>							 
									</div>									
								</div>
								<div class="row">
									<div class="col-2">	
										<input type="radio" id="subordinato" name="ruolo" value="subordinato">										
									</div>
									<div class="col-4">		
										<label for="subordinato">Subordinato</label>																 
									</div>										
								</div>															 
								<button type="submit">Aggiungi Dipendente</button>
							</form>
						</div>



						<div id="content2" class="hidden">
						    <h3>Rimuovi dipendente</h3>
						
						    <%-- Rimuovi Responsabili --%>
						    <h4>Responsabili:</h4>
						    <% 
						    	responsabili = (Collection<?>) request.getAttribute("responsabili");
						        for (Object utente : responsabili) {
						        Utente responsabile = (Utente) utente;
						        if(responsabile.isStato()) {
						    %>
						        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
						            <input type="hidden" name="email" value="<%=responsabile.getEmail()%>">
						            <p><%=responsabile.getNome()%> <%=responsabile.getCognome()%>:
						                <button type="submit"><%if(!responsabile.isStato()) {%>Riaggiungi<%}else {%>Rimuovi<%} %></button>
						            </p>
						        </form>
						    <%
						        }}
						    %>
						
						    <%-- Rimuovi Subordinati --%>
						    <h4>Subordinati:</h4>
						    <% 
					    		subordinati = (Collection<?>) request.getAttribute("subordinati");						    
						        for (Object utente : subordinati) {
							        Utente subordinato = (Utente) utente;
							    if(subordinato.isStato()) {    
						    %>
						        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
						            <input type="hidden" name="email" value="<%=subordinato.getEmail()%>">
						            <p><%=subordinato.getNome()%> <%=subordinato.getCognome()%>:
						                <button type="submit">Rimuovi</button>
						            </p>
						        </form>
						    <%
						        }}
						    %>
						</div>

						
									
						<div id="content3" class="hidden">
							<h3>Aggiungi turno</h3>
						
						    <%-- Rimuovi Responsabili --%>
						    <% if(u.getRuolo().equals("dirigente")) { %>
							    <h4>Responsabili:</h4>
							    <% 
							    	responsabili = (Collection<?>) request.getAttribute("responsabili");
							        for (Object utente : responsabili) {
							        Utente responsabile = (Utente) utente;
							        if(responsabile.isStato()) {						        
							    %>
							        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
							            <input type="hidden" name="email" value="<%=responsabile.getEmail()%>">
							            <p><%=responsabile.getNome()%> <%=responsabile.getCognome()%>:
							                <button type="submit">Aggiungi turno</button>
							            </p>
							        </form>
							    <%
							        }}
						    }
						    %>
						
						    <%-- Rimuovi Subordinati --%>
						    <% if(u.getRuolo().equals("responsabile")) { %>
							    <h4>Subordinati:</h4>
							    <% 
						    		subordinati = (Collection<?>) request.getAttribute("subordinati");						    
							        for (Object utente : subordinati) {
								        Utente subordinato = (Utente) utente;
								    if(subordinato.isStato()) {  
							    %>
							        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
							            <input type="hidden" name="email" value="<%=subordinato.getEmail()%>">
							            <p><%=subordinato.getNome()%> <%=subordinato.getCognome()%>:
							                <button type="submit">Aggiungi turno</button>
							            </p>
							        </form>
							    <%
							        }}
						    }
						    %>
						</div>
						<div id="content4" class="hidden">
							<h3>Rimuovi turno</h3>
						
						    <%-- Rimuovi turni Responsabili --%>
						    <% if(u.getRuolo().equals("dirigente")) { %>
							    <h4>Responsabili:</h4>
							    <% 
							    	responsabili = (Collection<?>) request.getAttribute("responsabili");
							        for (Object utente : responsabili) {
							        Utente responsabile = (Utente) utente;
							        if(responsabile.isStato()) {						        
							    %>
							        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
							            <input type="hidden" name="email" value="<%=responsabile.getEmail()%>">
							            <p><%=responsabile.getNome()%> <%=responsabile.getCognome()%>:
							                <button type="submit">Rimuovi turno</button>
							            </p>
							        </form>
							    <%
							        }}
						    }
						    %>
						
						    <%-- Rimuovi turni Subordinati --%>
						    <% if(u.getRuolo().equals("responsabile")) { %>
							    <h4>Subordinati:</h4>
							    <% 
						    		subordinati = (Collection<?>) request.getAttribute("subordinati");						    
							        for (Object utente : subordinati) {
								        Utente subordinato = (Utente) utente;
								        if(subordinato.isStato()) {							        
							    %>
							        <form action="<%=request.getContextPath()%>/RemoveEmployee" method="post">
							            <input type="hidden" name="email" value="<%=subordinato.getEmail()%>">
							            <p><%=subordinato.getNome()%> <%=subordinato.getCognome()%>:
							                <button type="submit">Rimuovi turno</button>
							            </p>
							        </form>
							    <%
							        }}
						    }
						    %>							
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
					</script>) 
					<a href="">Focus Project.</a> Tutti i diritti sono riservati.
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