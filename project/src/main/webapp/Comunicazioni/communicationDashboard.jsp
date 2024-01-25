<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="comunicazioni.bean.*" import="java.util.*" import="account.bean.*"%>

<%
	// SE L'UTENTE NON E' AUTENTICATO
	Utente u = (Utente) session.getAttribute("utente");
	if(u == null) {
		response.sendRedirect(request.getContextPath() + "/LoadData");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard Comunicazioni</title>

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/Comunicazioni/css/communicationDashboard.css">

<!-- JavaScript -->
<script type="text/javascript" src="<%=request.getContextPath()%>/Comunicazioni/js/communicationDashboard.js"></script>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<!-- font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">

<!-- jQuery e jQuery UI Datepicker -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<script>
	$(function() {
		$(".datepicker").datepicker();
	});
</script>

</head>
<body>
	<header>
		<a id="logo" href="<%=request.getContextPath()%>/homePage.jsp">FOCUS PROJECT</a>
	</header>
	
	<%
	// Comunicazioni
	List<Comunicazione> news = (List<Comunicazione>) request.getAttribute("news");
	
	// Permessi non gestiti
	List<Permesso> permessiResponsabili = (List<Permesso>) request.getAttribute("permessiResponsabili");
	List<Permesso> permessiSubordinati = (List<Permesso>) request.getAttribute("permessiSubordinati");
	
	// Permessi gestiti
	List<Permesso> permessiRespGestiti = (List<Permesso>) request.getAttribute("permessiRespGestiti");
	List<Permesso> permessiSubGestiti = (List<Permesso>) request.getAttribute("permessiSubGestiti");
	
	// Personali
	List<Permesso> permessiRichiesti = (List<Permesso>) request.getAttribute("permessiRichiesti");
	%>
	
	<%
	String notification = (String) request.getAttribute("notification");
	if (notification != null && !notification.isEmpty()) {
	%>
	<div id="notification" class="notification">
		<span><%=notification%></span>
		<!-- Aggiungi un pulsante o chiudi automaticamente dopo un certo periodo -->
		<button onclick="closeNotification()">Chiudi</button>
	</div>
	<%
	}
	%>
	
	<div class="container">
		<div class="row">
			<div class="container-fluid1">
				<!-- Sidebar con pulsanti -->
				<div class="col-md-3 col-lg-2 sidebar">
					<button id="showAddNews" class="button" onclick="showContent('content1')">News</button>
					
					<%
					if(u.getRuolo().equals("dirigente") || u.getRuolo().equals("responsabile")) {%>
							<button id="showPermissionManagement" class="button" onclick="showContent('content2')">Gestione Permessi</button>
							<button id="showManagedPermissions" class="button" onclick="showContent('content4')">Permessi Gestiti</button>
					<%}
					
					if(u.getRuolo().equals("subordinato") || u.getRuolo().equals("responsabile")) {%>
							<button id="showRequestPermission" class="button" onclick="showContent('content3')">Richiedi Permesso</button>
							<button id="showRequestedPermissions" class="button" onclick="showContent('content5')">Permessi Richiesti</button>
					<%}
					%>
					
				</div>

				<div class="col-md-9 col-lg-10">
					<div id="contentContainer">
						<!-- Contenuto dei div associati ai pulsanti -->
						<div id="content1" class="active">
						
						<% if(u.getRuolo().equals("dirigente") || u.getRuolo().equals("responsabile")) {%>
							<!-- Pubblica news -->
							<h4>Pubblica news</h4>
							<form id="addNewsForm" action="<%=request.getContextPath()%>/AddNews" method="POST">
								<label for="titolo">Titolo:</label> <input type="text" id="titolo" name="titolo" required> <label for="testo">Testo:</label>
								<input type="text" id="testo" name="testo" required>
								
								<button type="submit">Pubblica</button>
							</form>
							
							<hr/>
						<% }%>
						
							<!-- News pubblicate -->
							<h4>News pubblicate</h4>
							<div id="publishedNews">
								<%
								if (news != null && !news.isEmpty()) {
									int loopCount = Math.min(5, news.size());
									for (int i = 0; i < loopCount; i++) {
										Comunicazione c = news.get(i);
								%>
								<div class="news">
									<!-- Aggiungi la classe "news" qui -->
									<h5><%=c.getTitolo()%></h5>
									<p><%=c.getCorpo()%></p>
								</div>
								<%
								}
								} else {
								%>
								<p>Nessuna news pubblicata.</p>
								<%
    							}
    							%>
							</div>
						</div>

						<div id="content2" class="hidden">
							<h4>Gestione Permessi</h4>
							<!-- Permessi richiesti dai Responasbili (Vista Dirigente) -->
							<%	if(u.getRuolo().equals("dirigente")) {%>
								<p>Responsabili:</p>
								<%
								if (permessiResponsabili != null && !permessiResponsabili.isEmpty()) {
									for (Permesso permesso : permessiResponsabili) {
								%>
									<form action="<%=request.getContextPath()%>/PermissionManagement" method="post">
										<input type="hidden" name="permitId" value="<%=permesso.getId()%>">
									    <input type="hidden" name="email" value="<%=permesso.getRichiedenteEmail()%>">
									    <div id ="singlePermesso">
									        <h5> <%=permesso.getRichiedenteEmail()%> </h5>
									
									        <p>
									            Dal giorno: <%=permesso.getDalGiorno()%> <br/>
									            Al giorno: <%=permesso.getAlGiorno()%> <br/>
									            Motivo: <%=permesso.getMotivo()%> <br/>
									        </p>
									
									        <button type="submit" name="action" value="accetta">Accetta</button>
									        <button type="submit" name="action" value="rifiuta">Rifiuta</button>
									    </div>
									</form>
								<%
									}
								} else { %> <p>Nessuna richiesta di permesso. </p> <% }
							}
							%>
							
							<!-- Permessi richiesti dai Subordinati (Vista Responsabile) -->
							<%	if(u.getRuolo().equals("responsabile")) {%>
								<p>Subordinati:</p>
								<%
								if (permessiSubordinati != null && !permessiSubordinati.isEmpty()) {
									for (Permesso permesso : permessiSubordinati) {
								%>
									<form action="<%=request.getContextPath()%>/PermissionManagement" method="post">
										<input type="hidden" name="permitId" value="<%=permesso.getId()%>">
									    <input type="hidden" name="email" value="<%=permesso.getRichiedenteEmail()%>">
									    <div id ="singlePermesso">
									        <h5> <%=permesso.getRichiedenteEmail()%> </h5>
									
									        <p>
									            Dal giorno: <%=permesso.getDalGiorno()%> <br/>
									            Al giorno: <%=permesso.getAlGiorno()%> <br/>
									            Motivo: <%=permesso.getMotivo()%> <br/>
									        </p>
									
									        <button type="submit" name="action" value="accetta">Accetta</button>
									        <button type="submit" name="action" value="rifiuta">Rifiuta</button>
									    </div>
									</form>
								<%
									}
								} else { %> <p>Nessuna richiesta di permesso. </p> <% }
							}
							%>
							
						</div>
						
						<div id="content3" class="hidden">
							<h4>Richiedi permesso</h4>
							<form action="<%=request.getContextPath()%>/RequestPermission" method="post">
								<label for="dalGiorno">Dal giorno:</label> <input type="text"
									id="dalGiorno" name="dalGiorno" class="datepicker" required>

								<label for="alGiorno">Al giorno:</label> <input type="text"
									id="alGiorno" name="alGiorno" class="datepicker" required>

								<label for="motivazione">Motivazione:</label>
								<textarea id="motivazione" name="motivazione" required></textarea>

								<input type="submit" value="Richiedi">
							</form>
						</div>
						
						<div id="content4" class="hidden">
							<h4>Permessi Gestiti</h4>
							<%
						    if ("dirigente".equals(u.getRuolo())) {
						        for (Permesso p : permessiRespGestiti) { %>
						            <div>
						            	<%= p.getRichiedenteEmail() %>
						            	<% if(p.isStato()) {%>
						            		<p id="approvato">Approvato</p>
						            	<% } else { %>
						            		<p id="rifiutato">Rifiutato</p>
						            	<%}%>
						            	dal giorno <%= p.getDalGiorno() %> <br/>
						            	al giorno <%= p.getAlGiorno() %> <br/>
						            	<%= p.getMotivo() %>
						            </div>
						        <%}
						        
						        if(permessiRespGestiti.isEmpty()) {%>
						        	<p>Nessun permesso gestito</p>
						        <%}
						        
						    } else if ("responsabile".equals(u.getRuolo())) {
						        for (Permesso p : permessiSubGestiti) { %>
						            <div>
						            	<%= p.getRichiedenteEmail() %>
						            	<% if(p.isStato()) {%>
						            		<p id="approvato">Approvato</p>
						            	<% } else { %>
						            		<p id="rifiutato">Rifiutato</p>
						            	<%}%>
						            	dal giorno <%= p.getDalGiorno() %> <br/>
						            	al giorno <%= p.getAlGiorno() %> <br/>
						            	<%= p.getMotivo() %>
						            </div>
						        <%}
						        
						        if(permessiSubGestiti.isEmpty()) {%>
					        	<p>Nessun permesso gestito</p>
					        	
					        <%}
						    }%>
						    
						</div>
						
						<div id="content5" class="hidden">
							<h4>Permessi Richiesti</h4>
							<%
							for (Permesso p : permessiRichiesti) { %>
						    	<div>
						            <% if(p.isStato()) {%>
						            	<p id="approvato">Approvato</p>
						            <% } else { %>
						            	<p id="rifiutato">Rifiutato</p>
						            <%}%>
						            dal giorno <%= p.getDalGiorno() %> <br/>
						            al giorno <%= p.getAlGiorno() %> <br/>
						            <%= p.getMotivo() %>
						   		</div>
							<%}
							
					        if(permessiRichiesti.isEmpty()) {%>
				        	<p>Nessun permesso richiesto</p>
				        	<%}
					        
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
					</script>) <a href="">Focus Project.</a> Tutti i diritti sono riservati.
				</p>
			</nav>
		</div>
	</footer>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>