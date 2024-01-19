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
	if (progetto == null) {
		String projectId = request.getParameter("id");
		response.sendRedirect(request.getContextPath() + "/LoadProjects?id=" + projectId);

	} else {
		List<?> subordinati = (List<?>) request.getAttribute("subordinati");
		Utente responsabile = (Utente) request.getAttribute("responsabile");
	%>

	<div class="overlay" id="Overlay"></div>
	<div class="container text-center" id="Popup">
	    <button id="closeBtn" class="close-btn"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Seleziona subordinato</h3>
	        <form action="<%= request.getContextPath()%>/AddSubProject" method="post">
	            <%for(Object o: subordinati) {
	                Utente u = (Utente) o;%>
	                <div>
	                    <input type="hidden" name="email" value="<%= u.getEmail()%>">
	                    <input type="hidden" name="idProject" value="<%= progetto.getIdProgetto()%>">
	                    <p>Nome: <%= u.getNome()%> <button type="submit" class="btn btn-outline-danger" name="action" value="insert">Aggiungi</button></p>
	                </div>
	            <%} %>
	        </form>
	    </div>
	</div>

	
	<div class="container text-center">
		<h2><%= progetto.getNome() %></h2>
			<br>
			<br>
			
			<div class="row">
		        <!-- Contenitore Obiettivi -->
		        <div class="col-md-6">
		            <div class="riquadro">
		                <h3>Obiettivi</h3>
		            	<textarea id="testoObiettivi" class="form-control" readonly><%= progetto.getObbiettivi() %></textarea>
		            	<br>		                		                
		                <form id="concludiProgettoForm" action="<%=request.getContextPath()%>/ConcludeProject" method="post">
		                	<input type="hidden" id="idProgetto" name="id_progetto" value="<%=progetto.getIdProgetto()%>" />
					    	<button class="btn btn-primary" type="submit">Concludi</button>
					    </form>
		            </div>
		        </div>
		           	
				<div class="col-md-6">
		            <div class="riquadro dip">
		                <h3>Responsabile</h3>
		                <p>Nome: <%=responsabile.getNome() %></p>
		                <h4>Subordinati</h4>
		                <%for(Object o: subordinati) {
		                	Utente u = (Utente) o;%>
		                	<p>nome: <%= u.getNome()%></p>
		                <%} %>
		                <div id="dipendentiFormContainer">
		                	<button  class="btn btn-primary open-popup-btn">Assegna subordinato</button>
				        	<button class="btn btn-danger open-popup-btn">Rimuovi subordinato</button>
				        	<a class="btn btn-info" href="<%=request.getContextPath()%>/LoadProjects?operazione=task&id=<%=progetto.getIdProgetto()%>">Gestione Task</a>
		                </div>
		            </div>
				</div>
	    	</div> 
   
		 <div class="avvisi">
			<h3>Avvisi</h3>
		        <div class="mb-3">
		            <textarea class="form-control" id="testoAvviso" readonly><%= progetto.getAvvisi() %></textarea>
		        </div>
		        <div class="text-end">
		        	<button type="submit" class="btn btn-primary">Invia Avviso</button>
		        </div>
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