<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="progetto.bean.*" import="account.bean.*"  %>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Progetto</title>
	
	<!-- CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"  crossorigin="anonymous">
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css">	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/Progetto/css/project.css">			  

	<!-- JavaScript -->
 	<script type="text/javascript" src="<%= request.getContextPath()%>/Progetto/js/project.js"></script>
  
  <!-- jQuery UI -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
	
	<!-- font -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
	<header>
		<a id="logo" href="homePage.jsp">FOCUS PROJECT</a>					
	</header>

	<jsp:include page="../navbar.jsp" />

	<br>

	<%
	Progetto progetto = (Progetto) request.getAttribute("progetto");
	Utente user = (Utente) session.getAttribute("utente");
	String ruolo = user.getRuolo();
	if (progetto == null) {
		String projectId = request.getParameter("id");
		response.sendRedirect(request.getContextPath() + "/LoadProjects?id=" + projectId);

	} else {
		List<?> subordinati = (List<?>) request.getAttribute("subordinati");
		List<?> subordinatiProj = (List<?>) request.getAttribute("subordinatiProj");
		List<?> responsabili = (List<?>) request.getAttribute("responsabili");
		Utente responsabile = (Utente) request.getAttribute("responsabile");
	%>

	<div class="overlay" id="Overlay"></div>
	<div class="container text-center" id="Popup">
	    <button id="closeBtn" class="close-btn"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Seleziona subordinato</h3>
	
	        <!-- Form per l'aggiunta di subordinati -->
	        <% for(Object o: subordinati) {
	            Utente u = (Utente) o; %>
		        <form action="<%= request.getContextPath()%>/HandleSubs" method="post">
	
		                <div>
		                    <input type="hidden" name="email" value="<%= u.getEmail()%>">
		                    <input type="hidden" name="idProject" value="<%= progetto.getIdProgetto()%>">
		                    <p class="addButton">Nome: <%= u.getNome()%> <%= u.getEmail()%>
		                        <button type="submit" class="btn btn-outline-danger" name="action" value="insert">Aggiungi</button>
		                    </p>
		                </div>
		        </form>
			<% } %>
			
	        <% for(Object o: subordinatiProj) {
	            Utente u = (Utente) o; %>			
	        <!-- Form per la rimozione di subordinati -->
		        <form action="<%= request.getContextPath()%>/HandleSubs" method="post">
		                <div>
		                    <input type="hidden" name="email" value="<%= u.getEmail()%>">
		                    <input type="hidden" name="idProject" value="<%= progetto.getIdProgetto()%>">
		                    <p class="removeButton">Nome: <%= u.getNome()%> <%= u.getEmail()%>
		                        <button type="submit" class="btn btn-outline-danger" name="action" value="remove">Rimuovi</button>
		                    </p>
		                </div>
		        </form>
	        <% } %>	        
	    </div>
	</div>
	
	<div class="overlay1" id="Overlay1"></div>
	<div class="container text-center" id="Popup1">
	    <button id="closeBtn1" class="close-btn close-popup-btn1"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Seleziona responsabile</h3>
	
	        <!-- Form per l'aggiunta di subordinati -->
			<% for(Object o: responsabili) {
			    Utente u = (Utente) o; %>
			    <div>
			        <input type="hidden" class="responsabileProgetto" name="email" value="<%= u.getEmail()%>">
			        <p class="addButton">Nome: <%= u.getNome()%> <%= u.getEmail()%>
			            <button id="closeBtn2" type="button" class="btn btn-outline-danger replaceResp close-popup-btn1" name="action" value="insert" onclick="updateInputValueResponsabile('<%=u.getEmail() %>', 'newresponsabile')">Sostituisci</button>
			        </p>
			    </div>
			<% } %> 

       
	    </div>
	</div>	


	
	<div class="container text-center">
		<div class="col text-center d-flex flex-column align-items-center">
			<input id="nomeProgetto" style="display:none;" type="text" name="idProject" value="<%= progetto.getNome()%>" required>
		</div>	
		<h2 id="headerProgetto" style="display:block;"><%= progetto.getNome() %></h2>
					
			<hr>		
			<div class="container text-center">
			  <div class="row">
			  	<div class="col">
				    <form id="addProjectForm" action="<%=request.getContextPath()%>/AddProject" method="POST">
				    	 <input type="hidden" id="idProject" name="idProject" value="<%= progetto.getIdProgetto()%>" required>
				    
				         <input type="hidden" id="newnome" name="nome" value="<%= progetto.getNome()%>" required>
				
				         <input type="hidden" id="newresponsabile" name="responsabile" value="<%= progetto.getResponsabile_email()%>" required>
				
				         <input type="hidden" id="newdescrizione" name="descrizione" value="<%= progetto.getDescrizione()%>" required>
				
				         <input type="hidden" id="newobiettivi" name="obiettivi" value="<%= progetto.getObbiettivi()%>" required>
				
				         <input type="hidden" id="newscadenza" name="scadenza" value="<%= progetto.getScadenza()%>" >
				
				         <input type="hidden" id="newbudget" name="budget" value="<%= progetto.getBudget()%>" required>
				
				         <button id="applicaMod" name="action" value="edit" style="display:none;" class="btn btn-success" type="submit">applica modifiche</button>
				         
				    </form>			  	
			  	</div>
			    <div class="col">
					<% if (!progetto.isStato()) { %>
					    <form id="concludiProgettoForm" action="<%= request.getContextPath() %>/ConcludeProject" method="post">
					        <input type="hidden" id="idProgetto" name="id_progetto" value="<%= progetto.getIdProgetto() %>" />
					        <% if (ruolo.compareTo("responsabile") == 0) { %>
					        <button class="btn btn-primary" name="action" value="concludi" type="submit">Concludi</button>
					        <% } else if (ruolo.compareTo("dirigente") == 0) { %>
					        <button class="btn btn-danger" name="action" value="elimina" type="submit">Elimina</button>					        
					    </form>
	    		</div>
	    		<div class="col">
			    
			    <button id="editButton" style="display:block;" class="btn btn-primary" name="action" value="edit" onclick="handleEditClick()">Modifica</button>
				<button id="ReloadMod" style="display:none;" class="btn btn-danger" type="button" onclick="window.location.href='<%= request.getContextPath() %>/LoadProjects?id=<%= progetto.getIdProgetto() %>';">Annulla modifiche</button>

					<% } }else { %>
					    <h3>Progetto Concluso!</h3>
					<% } %>
			    </div>
			  </div>
			</div>
	    
			<br>		    
			<h3>Descrizione</h3>					    			
		    <textarea id="testoDescrizione" class="form-control" readonly><%= progetto.getDescrizione() %></textarea>	
		    <br>
			<br>		
				<p>
					Scadenze
					<input id="scadenzeProgetto" name="scadenze_progetto" value="<%=progetto.getScadenza()%>" readonly/>
					budget		
					<input id="budgetProgetto" name="budget_progetto" value="<%=progetto.getBudget()%>" readonly/>
				</p>
				<br>
				<br>			
			<div class="row">
		        <!-- Contenitore Obiettivi -->
		        <div class="col-md-6">
		            <div class="riquadro">
		                <h3>Obiettivi</h3>
		            	<textarea id="testoObiettivi" class="form-control" readonly><%= progetto.getObbiettivi() %></textarea>
		            	<br>
		            </div>
		        </div>
		           	
				<div class="col-md-6">
		            <div class="riquadro dip">
		                <h3>Responsabile</h3>
		                <input type="hidden" id="responsabileProgetto" value="<%=progetto.getResponsabile_email()%>">
		                <div class="row">
			                <div class="col text-center d-flex flex-column align-items-center">
			                	<p id="nomeresp">Nome: <%=responsabile.getNome() %></p>

			                	<button onclick="addButton()" class="btn btn-danger open-popup-btn1" id="responsabileProgettoBtn" style="display:none;">Modifica responsabile</button>
			                </div>
		                </div>
		                <hr>
		                <h4>Subordinati</h4>
		                <%for(Object o: subordinatiProj) {
		                	Utente u = (Utente) o;%>
		                	<p>nome: <%= u.getNome()%></p>
		                <%} %>
		                
		            	<%if(!progetto.isStato()) {%>
			            	<%if(ruolo.compareTo("responsabile")==0) {%>	                		                
				                <div id="dipendentiFormContainer">
				                	<button onclick="addButton()" class="btn btn-primary open-popup-btn">Assegna subordinato</button>
						        	<button onclick="removeButton()" class="btn btn-danger open-popup-btn">Rimuovi subordinato</button>
						        	<a class="btn btn-info" href="<%=request.getContextPath()%>/LoadTask?id=<%=progetto.getIdProgetto()%>">Gestione Task</a>
						    		<%}else {%>
						    			<a class="btn btn-info" href="<%=request.getContextPath()%>/LoadTask?id=<%=progetto.getIdProgetto()%>">Visualizza Task</a>
						    		<%} %>
				                </div>
 
					    <%} else { %>
					        	<a class="btn btn-info" href="<%=request.getContextPath()%>/LoadTask?id=<%=progetto.getIdProgetto()%>">Visualizza Task</a>
					    <% }%>
		            </div>
				</div>
	    	</div> 
   
		 <div class="avvisi container">
			<h3>Avvisi</h3>
		        <div class="mb-3">
		            <textarea class="form-control" id="testoAvviso" readonly><%= progetto.getAvvisi() %></textarea>
		        </div>
		        <%if(!progetto.isStato()) {%>			        
			        <div class="text-end">
			        	<button type="submit" class="btn btn-primary">Invia Avviso</button>
			        </div>
		        <% } %>
		</div>	
	</div>
        <% } %>	 	
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