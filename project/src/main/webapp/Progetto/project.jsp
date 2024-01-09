<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="progetto.bean.Progetto" %>
	<%
	String projectId = request.getParameter("id");
	response.sendRedirect(request.getContextPath() + "/LoadProjects?id=" + projectId);
	%>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Progetto</title>
	
	<!-- CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"  crossorigin="anonymous">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="css/project.css">
	<script type="text/javascript" src="js/project.js"></script>
	<!-- font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>
<body>
		<%
            Progetto progetto = (Progetto) request.getAttribute("progetto");
            if (progetto != null) {
        %>
        
        <p>ID: <%= progetto.getIdProgetto() %></p>
        <p>Nome: <%= progetto.getNome() %></p>
        <p>Descrizione: <%= progetto.getDescrizione() %></p>
        <%-- Aggiungi altri campi del progetto se necessario --%>
        <% } else { %>
        <p>Progetto non trovato</p>
        <% } %>
	<header>
		<a id="logo" href="../homePage.jsp">FOCUS PROJECT</a>					
	</header>
	
	<jsp:include page="../navbar.jsp" />
	
	<div class="Container-fluid">
		<h2> Progetto x</h2>
		<div class="row">
        <!-- Contenitore Obiettivi -->
        <div class="col-md-6">
            <div class="riquadro">
                <h3>Obiettivi</h3>
                <!-- Qui andrà la lista di obiettivi recuperati dal sistema -->
                <!-- Ad esempio, potresti usare un <ul> per visualizzare gli obiettivi -->
                <ul id="obiettiviList">
                    <!-- Gli obiettivi verranno inseriti qui tramite JavaScript -->
                </ul>
                <button class="btn btn-primary">Concludi</button>
            </div>
        </div>
        </div>
        
         
	
	<div class="col-md-6">
            <div class="riquadro dip">
                <h3>Dipendenti</h3>
                <div id="dipendentiFormContainer">
                    <!-- Qui verranno visualizzati i form dei dipendenti -->
                </div>
            </div>
        </div>
    </div>
	
	
	
	
	

	
	
 <div class="avvisi">
	<h3>Aggiungi un Avviso</h3>
    <form id="avvisoForm">
        <div class="mb-3">
            <label for="testoAvviso" class="form-label">Testo Avviso:</label>
            <textarea class="form-control" id="testoAvviso" name="testoAvviso" rows="5" required></textarea>
        </div>
        <div class="text-end">
        <button type="submit" class="btn btn-primary">Invia Avviso</button>
        </div>
    </form>
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