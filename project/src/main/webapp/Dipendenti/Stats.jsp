<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="account.bean.*"%>

<%
	Utente u = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
	<title>Statistiche</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	
	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Dipendenti/css/employeeDashboard.css">
	
	<!-- JavaScript -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Dipendenti/js/employeeDashboard.js"></script>
	
	<!-- font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>

<body>

	<header>
		<a id="logo" href="<%=request.getContextPath()%>/homePage.jsp">FOCUS PROJECT</a>
	</header>

	<jsp:include page="../navbar.jsp"/>
	
	<% if(u.getRuolo().equals("dirigente")) { %>
		<!-- Caricare lista responsabili e subordinati -->
		
		
		<!-- Statistiche comuni a ogni dipendente -->
		<ul>
		    <li>Numero Progetti Completati: </li>
		    <li>Numero Progetti in Corso: </li>
		    <li>Numero Permessi Richiesti: </li>
		</ul>
		
		<!-- Statistiche dei soli repsonsabili -->
		<ul>
	        <li>Numero Subordinati Gestiti: </li>
	        <li>Numero Scadenze Rispettate: </li>
	    </ul>
	    
	    <!-- Statistiche dei soli subordinati -->
	    <ul>
	        <li>Numero Task Completati: </li>
	        <li>Numero Task in Corso: </li>
	    </ul>
	    
	<% } %>
	
	
	
	<% if(u.getRuolo().equals("responsabile")) { %>
	    <h2>Le mie statistiche</h2>
		<ul>
		    <li>Numero Progetti Completati: </li>
		    <li>Numero Progetti in Corso: </li>
		    <li>Numero Permessi Richiesti: </li>
			<li>Numero Subordinati Gestiti: </li>
	        <li>Numero Scadenze Rispettate: </li>
		</ul>
	<% } %>
	
	
	
	<% if(u.getRuolo().equals("subordinato")) { %>
	    <h2>Le mie statistiche</h2>
		<ul>
		    <li>Numero Progetti Completati: </li>
		    <li>Numero Progetti in Corso: </li>
		    <li>Numero Permessi Richiesti: </li>
		    <li>Numero Task Completati: </li>
	        <li>Numero Task in Corso: </li>
		</ul>
	<% } %>
	
	
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
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
	</script>

</body>

</html>