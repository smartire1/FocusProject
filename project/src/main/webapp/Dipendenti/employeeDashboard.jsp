<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="account.bean.*"%>
<%@ page import="dipendenti.bean.*"%>
<%@ page import="java.util.*"%>

<%
	// Dipendenti 
	
	Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
	Collection<?> subordinati = (Collection<?>) request.getAttribute("subordinati");
	Collection<?> mieiSubordinati = (Collection<?>) request.getAttribute("mieiSubordinati");
	
	// Turni
	Collection<?> mieiTurni = (Collection<?>) request.getAttribute("mieiTurni");
	Map<String, Collection<Turno>> turniResponsabili = (Map<String, Collection<Turno>>) request.getAttribute("turniResponsabili");
	Map<String, Collection<Turno>> turniMieiSubordinati = (Map<String, Collection<Turno>>) request.getAttribute("turniMieiSubordinati");
	
	// Altro
	String password = (String) request.getAttribute("password");
	Utente u = (Utente) session.getAttribute("utente");

	// SE L'UTENTE NON E' AUTENTICATO
	if(u == null) {
		response.sendRedirect(request.getContextPath() + "/LoadEmployees");
		return;
	}
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
		
  	<!-- jQuery UI -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>			
	
	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Dipendenti/css/employeeDashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 	
	
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

	<% if(password != null) {%>
	<div class="overlay1" id="Overlay1"></div>
	<div class="container text-center" id="Popup1">
	    <button id="closeBtn1" class="close-btn close-popup-btn1"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Password Generata</h3>
	        <div>
		        	<br>
		        	<br>
						<p>Utente Registrato con successo</p>	        	
						<h3><%= password%></h3>						
		        	<br>
		        	<br>       	
	        </div>
       
	    </div>
	</div>	
	<%} %>

	<div class="overlay" id="Overlay"></div>
	<div class="container text-center" id="Popup">
	    <button id="closeBtn" class="close-btn close-popup-btn"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Compila Form Turno</h3>
	
	        <div>
	        	<br>
	        	<br>
	        	<form action="<%=request.getContextPath()%>/AddTurno" method="POST">
		        	<input type="text" id="datePicker" name="dataTurno" value="Inserisci Data" >
		        	<br>
		        	<br>
		        	Ora inizio: <input type="time" name="oraInizio" id="oraInizio">
		        	<br>
		        	<br>
		        	Ora fine: <input type="time" name="oraFine" id="oraFine">
		        	<br>
		        	<br>
		        	<button type="submit" onclick="verificaOrario()">Aggiungi Tuno</button>
		        	<br>
		        	<br>
		        	<input type="hidden" id="toAdd" name="utenteTurno">
	        	</form>	        	
	        </div>
       
	    </div>
	</div>

	
	<div class="container">

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

		<div class="row">
			<div class="container-fluid1">
				<!-- Sidebar con pulsanti -->
				<div class="col-md-3 col-lg-2 sidebar">
					<!-- Vista dirigente -->
					<% if(u.getRuolo().equals("dirigente")) { %>
						<button id="showAddEmployee" class="button" onclick="showContent('content1')">Aggiungi Dipendente</button>
						<button id="showRemoveEmployee" class="button" onclick="showContent('content2')">Rimuovi Dipendente</button>
					<% } %>
					<!-- Vista comune -->
					<button id="showAddShift" class="button" onclick="showContent('content3')">Aggiungi Turni</button>
					<button id="showRemoveShift" class="button" onclick="showContent('content4')">Rimuovi Turni</button>
					
					<!-- Vista responsabile -->
					<% if(u.getRuolo().equals("responsabile")) { %>
					<button id="showMyShifts" class="button" onclick="showContent('content5')">I miei turni</button>
					<% } %>
				
				</div>

				<div class="col-md-9 col-lg-10">
					<div id="contentContainer">
						<!-- Contenuto dei div associati ai pulsanti -->
						<div id="content1" class="hidden">
							<form id="addEmployeeForm" action="<%=request.getContextPath()%>/AddEmployee" method="POST">
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
						
						    <!-- Rimuovi Responsabili -->
						    <h4>Responsabili:</h4>
						    <% 
						    	if(responsabili != null) {
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
						        }}}
						    %>
						
						    <!-- Rimuovi Subordinati -->
						    <h4>Subordinati:</h4>
						    <% 
						    	if(subordinati != null) {
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
						        }}}
						    %>
						</div>

						
									
						<div id="content3" class="hidden">
							<h3>Aggiungi turno</h3>
						
						    <!-- Rimuovi Responsabili -->
						    <% if(u.getRuolo().equals("dirigente")) { %>
							    <h4>Responsabili:</h4>
							    <% 
							        for (Object utente : responsabili) {
							        Utente responsabile = (Utente) utente;
							        if(responsabile.isStato()) {						        
							    %>
							            <input type="hidden" name="email" value="<%=responsabile.getEmail()%>">
							            <p><%=responsabile.getNome()%> <%=responsabile.getCognome()%>:
							                <button onclick="toAddUser('<%=responsabile.getEmail()%>')" class="btn btn-success open-popup-btn">Aggiungi turno</button>
							            </p>
							    <%
							        }}
						    }
						    %>
						
						    <!-- Rimuovi Subordinati -->
						    <% if(u.getRuolo().equals("responsabile")) { %>
							    <h4>Subordinati:</h4>
							    <% 
							        for (Object utente : mieiSubordinati) {
								        Utente subordinato = (Utente) utente;
								    if(subordinato.isStato()) {  
							    %>
							            <input type="hidden" name="email" value="<%=subordinato.getEmail()%>">
							            <p><%=subordinato.getNome()%> <%=subordinato.getCognome()%>:
							                <button onclick="toAddUser('<%=subordinato.getEmail()%>')" class="btn btn-success open-popup-btn">Aggiungi turno</button>
							            </p>
							    <%
							        }}
						    }
						    %>
						</div>
						
						
						
						<div id="content4" class="hidden">
							<h3>Rimuovi turno</h3>
						
						    <!-- Rimuovi turni Responsabili -->
						    <% if(u.getRuolo().equals("dirigente")) { %>
						    	<div class="form-container" style="overflow-y: auto; max-height: 500px;">
							    <h4>Responsabili:</h4>
							    <hr>
							    <% 
							        for (Object utente : responsabili) {
							        Utente responsabile = (Utente) utente;
							        if(responsabile.isStato()) {	
							        	Collection<Turno> turni = turniResponsabili.get(responsabile.getEmail());
							    %>

								<%		if(turniResponsabili.containsKey(responsabile.getEmail())) {%>
										
							            <input type="hidden" name="email" value="<%=responsabile.getEmail()%>">
							            <h5><%=responsabile.getNome()%> <%=responsabile.getCognome()%>:</h5>
							            
											<%for(Turno t: turni) {%>
																		            
											
											    <form class="<%=responsabile.getEmail()%>_form" action="<%=request.getContextPath()%>/RemoveTurno" method="post">
											        <input type="hidden" name="utenteId" value="<%=responsabile.getEmail()%>">
											        <input type="hidden" name="turnoId" value="<%=t.getId()%>">
											        <br>										          
											        <input type="text" name="turnoData" value="<%=t.getGiorno()%>">
											        Data
											        <br>										         
											        <input type="text" name="turnoOraF" value="<%=t.getOraInizio()%>">
											        Ora inizio
											        <br>										        
											        <input type="text" name="turnoOraF" value="<%=t.getOraFine()%>">
											        Ora fine	
											        <div class="form-buttons">
											            <button id="rimuoviTurno" class="btn btn-danger" type="submit">Rimuovi Turno</button>
											        </div>
											    </form>
											

											<br>
											
											<br>										           
						<%					}
						%>				<hr>
						<% 
										} 
							        }
							    }
						    }
						%>
						
						    <!-- Rimuovi turni Subordinati -->
						    <% if(u.getRuolo().equals("responsabile")) { %>
							    <h4>Subordinati:</h4>
							    <hr>
							    <% 
							        for (Object utente : mieiSubordinati) {
							        Utente subordinato = (Utente) utente;
							        if(subordinato.isStato()) {	
							        	Collection<Turno> turni = turniMieiSubordinati.get(subordinato.getEmail());
							    %>

								<%		if(turniMieiSubordinati.containsKey(subordinato.getEmail())) {%>
										
							            <input type="hidden" name="email" value="<%=subordinato.getEmail()%>">
							            <h5><%=subordinato.getNome()%> <%=subordinato.getCognome()%>:</h5>
							            
											<%for(Turno t: turni) {%>
																		            
											
											    <form class="<%=subordinato.getEmail()%>_form" action="<%=request.getContextPath()%>/RemoveTurno" method="post">
											        <input type="hidden" name="utenteId" value="<%=subordinato.getEmail()%>">
											        <input type="hidden" name="turnoId" value="<%=t.getId()%>">
											        <br>										          
											        <input type="text" name="turnoData" value="<%=t.getGiorno()%>">
											        Data
											        <br>										         
											        <input type="text" name="turnoOraF" value="<%=t.getOraInizio()%>">
											        Ora inizio
											        <br>										        
											        <input type="text" name="turnoOraF" value="<%=t.getOraFine()%>">
											        Ora fine	
											        <div class="form-buttons">
											            <button class="btn btn-danger" type="submit">Rimuovi Turno</button>
											        </div>
											    </form>
											

											<br>
											
											<br>										           
						<%					}
						%>				<hr>
						<% 
										} 
							        }
							    }
						    }
						%>							
						</div>
						</div>
						
						
						
						<div id="content5" class="hidden">
							<h3>I miei turni</h3>
							<!-- Caricare i propri turni -->
							<%
							if(mieiTurni != null && !mieiTurni.isEmpty()) {
							for(Object o : mieiTurni) {
								Turno t = (Turno) o;
							%>
							<p><%= t.getGiorno() %></p>
							<p>dalle ore <%= t.getOraFine() %></p>
							<p>alle ore <%= t.getOraFine() %></p>
							<% } }%>
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