<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="account.bean.*" import="dipendenti.bean.*"%>

<%
	Utente u = (Utente) session.getAttribute("utente");

	// Dirigente
	List<StatisticheResponsabile> statsResponsabili = (List<StatisticheResponsabile>) request.getAttribute("statsResponsabili");
	List<StatisticheSubordinato> statsSubordinati = (List<StatisticheSubordinato>) request.getAttribute("statsSubordinati");
	
	// Responsabile
	StatisticheResponsabile myStatsResponsabile = (StatisticheResponsabile) request.getAttribute("myStatsResponsabile");
	List<StatisticheSubordinato> statsMySubordinati = (List<StatisticheSubordinato>) request.getAttribute("statsMySubordinati");
	
	// Subordinato
	StatisticheSubordinato myStatsSubordinato = (StatisticheSubordinato) request.getAttribute("myStatsSubordinato");
	
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
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Dipendenti/css/Stats.css">
	
	<!-- JavaScript -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Dipendenti/js/Stats.js"></script>
	
	<!-- font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">
</head>

<body>

	<header>
		<a id="logo" href="<%=request.getContextPath()%>/homePage.jsp">FOCUS PROJECT</a>
	</header>

	<jsp:include page="../navbar.jsp"/>
	
	<div id="stats">
	
	<% if(u.getRuolo().equals("dirigente")) { %>
	    <!-- Statistiche dei soli repsonsabili -->
	    <h5>Responsabili</h5>
	    <%
	        for(StatisticheResponsabile sr : statsResponsabili) { %>
	        	<p><%=sr.getEmail()%></p>
	            <button onclick="toggleStats('<%= sr.getEmail() %>')">Mostra/Chiudi Statistiche</button>
	            <div id="<%= sr.getEmail() %>_stats" style="display: none;">
	                <p>Numero permessi richiesti: <%= sr.getNum_permessi_richiesti() %></p>
	                <p>Numero progetti completati: <%= sr.getNum_progetti_completati() %></p>
	                <p>Numero progetti in corso: <%= sr.getNum_progetti_in_corso() %></p>
	                <p>Numero subordinati gestiti: <%= sr.getNum_subordinati_gestiti() %></p>
	            </div>
	        <%
	        }
	    %>
	
	    <!-- Statistiche dei soli subordinati -->
	    <h5>Subordinati</h5>
	    <%
	        for(StatisticheSubordinato ss : statsSubordinati) { %>
	        	<p><%=ss.getEmail()%></p>
	            <button onclick="toggleStats('<%= ss.getEmail() %>')">Mostra/Chiudi Statistiche</button>
	            <div id="<%= ss.getEmail() %>_stats" style="display: none;">
	                <p>Numero permessi richiesti: <%= ss.getNum_permessi_richiesti() %></p>
	                <p>Numero progetti completati: <%= ss.getNum_progetti_completati() %></p>
	                <p>Numero progetti in corso: <%= ss.getNum_progetti_in_corso() %></p>
	                <p>Numero task completati: <%= ss.getNum_task_completati() %></p>
	                <p>Numero task in corso: <%= ss.getNum_task_in_corso() %></p>
	            </div>
	        <%
	        }
	    %>
	<% } %>
	
	
	
	<% if(u.getRuolo().equals("responsabile")) { %>
	    <h3>Le mie statistiche</h3>
		<div id="<%= myStatsResponsabile.getEmail() %>_stats" style="display: block;">
			<p>Numero permessi richiesti: <%= myStatsResponsabile.getNum_permessi_richiesti() %></p>
			<p>Numero progetti completati: <%= myStatsResponsabile.getNum_progetti_completati() %></p>
			<p>Numero progetti in corso: <%= myStatsResponsabile.getNum_progetti_in_corso() %></p>
			<p>Numero task completati: <%= myStatsResponsabile.getNum_subordinati_gestiti() %></p>
	    </div>

	    <!-- Statistiche dei soli subordinati -->
	    <h5>Subordinati</h5>
	    <%
	        for(StatisticheSubordinato ss : statsMySubordinati) { %>
	        	<p><%=ss.getEmail()%></p>
	            <button onclick="toggleStats('<%= ss.getEmail() %>')">Mostra/Chiudi Statistiche</button>
	            <div id="<%= ss.getEmail() %>_stats" style="display: none;">
	                <p>Numero permessi richiesti: <%= ss.getNum_permessi_richiesti() %></p>
	                <p>Numero progetti completati: <%= ss.getNum_progetti_completati() %></p>
	                <p>Numero progetti in corso: <%= ss.getNum_progetti_in_corso() %></p>
	                <p>Numero task completati: <%= ss.getNum_task_completati() %></p>
	                <p>Numero task in corso: <%= ss.getNum_task_in_corso() %></p>
	            </div>
	        <%
	        }
	    %>
	<% } %>
	
	
	
	<% if(u.getRuolo().equals("subordinato")) { %>
	    <h3>Le mie statistiche</h3>
		<div id="<%= myStatsSubordinato.getEmail() %>_stats" style="display: block;">
			<p>Numero permessi richiesti: <%= myStatsSubordinato.getNum_permessi_richiesti() %></p>
			<p>Numero progetti completati: <%= myStatsSubordinato.getNum_progetti_completati() %></p>
			<p>Numero progetti in corso: <%= myStatsSubordinato.getNum_progetti_in_corso() %></p>
			<p>Numero task completati: <%= myStatsSubordinato.getNum_task_completati() %></p>
	        <p>Numero task in corso: <%= myStatsSubordinato.getNum_task_in_corso() %></p>
	    </div>
	<% } %>
	
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
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
	</script>

</body>

</html>