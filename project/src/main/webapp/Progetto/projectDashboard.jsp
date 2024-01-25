<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="account.bean.*"%>
<%@ page import="progetto.bean.*"%>
<%@ page import="java.util.Collection"%>

<%
	Utente utente = (Utente) session.getAttribute("utente");
	
	if(utente == null) {
		response.sendRedirect(request.getContextPath() + "/LoadProjects");
		return;
	}

	// Dirigente
	Collection<?> progettiAttivi = (Collection<?>) request.getAttribute("progettiAttivi");
	Collection<?> progettiConclusi = (Collection<?>) request.getAttribute("progettiConclusi");
	Collection<?> responsabiliDisp = (Collection<?>) request.getAttribute("responsabiliDisp");
	
	// Responsabile
	Collection<?> progettiAttiviResp = (Collection<?>) request.getAttribute("progettiAttiviResp");
	Collection<?> progettiConclusiResp = (Collection<?>) request.getAttribute("progettiConclusiResp");
	
	// Subordinato
	Collection<?> progettiAttiviSub = (Collection<?>) request.getAttribute("progettiAttiviSub");
	Collection<?> progettiConclusiSub = (Collection<?>) request.getAttribute("progettiConclusiSub");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard Progetti</title>

    <!-- CSS -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Progetto/css/projectDashboard.css">

    <!-- JavaScript -->
    <script type="text/javascript"
        src="<%=request.getContextPath()%>/Progetto/js/projectDashboard.js"></script>

<!-- jQuery e jQuery UI Datepicker -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    
    <!-- font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 	
   

</head>
<body>
    <header>
        <a id="logo" href="homePage.jsp">FOCUS PROJECT</a>
    </header>

    <jsp:include page="../navbar.jsp" />

	<div class="overlay" id="Overlay"></div>
	<div class="container text-center" id="Popup">
	    <button id="closeBtn" class="close-btn close-popup-btn"><i class="fas fa-times"></i></button>
	    <div class="container">
	        <h3 id="Operation" class="title">Seleziona responsabile</h3>
	
	        <!-- Form per l'aggiunta di subordinati -->
	        <%if(responsabiliDisp != null ) {%>
			<% for(Object o: responsabiliDisp) {
			    Utente u = (Utente) o; %>
			    <div>
			        <p class="addButton">Nome: <%= u.getNome()%> <%= u.getEmail()%>
			            <button id="closeBtn2" type="button" class="btn btn-outline-danger replaceResp close-popup-btn" name="action" value="insert" onclick="updateInputValueResponsabile('<%=u.getEmail() %>')">Seleziona</button>
			        </p>
			    </div>
			<% }} %> 

       
	    </div>
	</div>

    <div class="container-fluid1">
        <div class="row">
        
            <!-- Sidebar con pulsanti -->
            <div class="col-md-12 text-center">
            <%if(utente.getRuolo().equals("dirigente")) { %>
                <button id="showAddProject" class="button larger-button" onclick="showContent('content1')">Aggiungi Progetto</button>
            <% } %>
                <button id="showOngoingProject" class="button larger-button" onclick="showContent('content2')">Progetti in corso</button>
                <button id="showCompletedProjects" class="button larger-button" onclick="showContent('content3')">Progetti conclusi</button>
            </div>

            <div class="col-md-12">
                <div id="contentContainer">
                
                	<!-- Aggiunta progetto (op. riservata al dirigente) -->
                	<%if(utente.getRuolo().equals("dirigente")) {%>
                    <div id="content1" class="active">
                        <form id="addProjectForm" action="<%=request.getContextPath()%>/AddProject" method="POST">
                            <h5>Aggiungi un nuovo Progetto</h5>
                            <label for="nome">Nome Progetto:</label>
                            <input type="text" id="nome" name="nome" required>

                            <label for="responsabile">Responsabile:</label>
                            <button  type="button" class="btn btn-success open-popup-btn">Responsabili</button>
                            <input type="text" id="responsabile" name="responsabile" readonly>

                            <label for="descrizione">Descrizione:</label>
                            <input type="text" id="descrizione" name="descrizione" required>

                            <label for="obiettivi">Obiettivi:</label>
                            <input type="text" id="obiettivi" name="obiettivi" required>

                            <label for="scadenza">Scadenza:</label>
                            <input type="text" id="scadenza" name="scadenza">

                            <label for="budget">Budget:</label>
                            <input type="number" id="budget" name="budget" required>

                            <button name="action" value="add" type="submit">Aggiungi Progetto</button>
                        </form>
                    </div>
                    <%} %>
					
					
					
					<!-- Progetti in corso -->
                    <div id="content2" class="hidden">
                        <h5>Progetti in corso</h5>
                        
                        <!-- DRIRIGENTE: stampa tutti i progetti attivi -->
                        <% if(utente.getRuolo().equals("dirigente")) {
                        	if(progettiAttivi != null) {
                        		for(Object obj : progettiAttivi) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
                        
                        <!-- RESPONSABILE: stampa tutti i progetti assegnati -->
                        <% if(utente.getRuolo().equals("responsabile")) {
                        	if(progettiAttiviResp != null) {
                        		for(Object obj : progettiAttiviResp) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
                        
                        <!-- SUBORDINATO: stampa tutti i progetti assegnati -->
                        <% if(utente.getRuolo().equals("subordinato")) {
                        	if(progettiAttiviSub != null) {
                        		for(Object obj : progettiAttiviSub) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
                    </div>



					<!-- Progetti conclusi -->
                    <div id="content3" class="hidden">
                        <h5>Progetti conclusi</h5>
                        
                        <!-- DRIRIGENTE: stampa tutti i progetti attivi -->
                        <% if(utente.getRuolo().equals("dirigente")) {
                        	if(progettiConclusi != null) {
                        		for(Object obj : progettiConclusi) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
                        
                        <!-- RESPONSABILE: stampa tutti i progetti assegnati -->
                        <% if(utente.getRuolo().equals("responsabile")) {
                        	if(progettiConclusiResp != null) {
                        		for(Object obj : progettiConclusiResp) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
                        
                        <!-- SUBORDINATO: stampa tutti i progetti assegnati -->
                        <% if(utente.getRuolo().equals("subordinato")) {
                        	if(progettiConclusiSub != null) {
                        		for(Object obj : progettiConclusiSub) {
                        			Progetto progetto = (Progetto) obj;%>
			                  		<div class="currentProjectList">
		                            	<a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
		                        	</div>
		                        <%}
                        	}
                        } %>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
