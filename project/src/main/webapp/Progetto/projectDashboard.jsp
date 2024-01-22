<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="progetto.bean.Progetto"%>
<%@ page import="java.util.Collection"%>

<%
Collection<?> progettiAttivi = (Collection<?>) request.getAttribute("progetti_attivi");
Collection<?> progettiConclusi = (Collection<?>) request.getAttribute("progetti_conclusi");

if (progettiAttivi == null || progettiConclusi == null) {
    response.sendRedirect(request.getContextPath() + "/LoadProjects");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard Progetti</title>

    <!-- CSS -->
    <link type="text/css" rel="stylesheet"
        href="<%=request.getContextPath()%>/css/style.css">
    <link type="text/css" rel="stylesheet"
        href="<%=request.getContextPath()%>/Progetto/css/projectDashboard.css">

    <!-- JavaScript -->
    <script type="text/javascript"
        src="<%=request.getContextPath()%>/Progetto/js/projectDashboard.js"></script>

    <!-- Bootstrap -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">

    <!-- font -->
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap">

</head>
<body>
    <header>
        <a id="logo" href="homePage.jsp">FOCUS PROJECT</a>
    </header>

    <jsp:include page="../navbar.jsp" />

    <div class="container-fluid1">
        <div class="row">
            <!-- Sidebar con pulsanti -->
            <div class="col-md-12 text-center">
                <button id="showAddProject" class="button larger-button"
                    onclick="showContent('content1')">Aggiungi Progetto</button>
                <button id="showOngoingProject" class="button larger-button"
                    onclick="showContent('content2')">Progetti in corso</button>
                <button id="showCompletedProjects" class="button larger-button"
                    onclick="showContent('content3')">Progetti conclusi</button>
            </div>

            <div class="col-md-12">
                <div id="contentContainer">
                    <div id="content1" class="active">
                        <form id="addProjectForm" action="<%=request.getContextPath()%>/AddProject" method="POST">
                            <h5>Aggiungi un nuovo Progetto</h5>
                            <label for="nome">Nome Progetto:</label>
                            <input type="text" id="nome" name="nome" required>

                            <label for="responsabile">Responsabile:</label>
                            <input type="text" id="responsabile" name="responsabile" required>

                            <label for="descrizione">Descrizione:</label>
                            <input type="text" id="descrizione" name="descrizione" required>

                            <label for="obiettivi">Obiettivi:</label>
                            <input type="text" id="obiettivi" name="obiettivi" required>

                            <label for="scadenza">Scadenza:</label>
                            <input type="text" id="scadenza" name="scadenza">

                            <label for="budget">Budget:</label>
                            <input type="number" id="budget" name="budget" required>

                            <button type="submit">Aggiungi Progetto</button>
                        </form>
                    </div>

                    <div id="content2" class="hidden">
                        <h5>Progetti in corso</h5>
                        <%
                        try {
                            if (progettiAttivi != null) {
                                if (!progettiAttivi.isEmpty()) {
                                    for (Object obj : progettiAttivi) {
                                        Progetto progetto = (Progetto) obj;
                        %>
                                        <div class="currentProjectList">
                                            <a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
                                        </div>
                        <%
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
                    </div>

                    <div id="content3" class="hidden">
                        <h5>Progetti conclusi</h5>
                        <%
                        try {
                            if (progettiConclusi != null) {
                                if (!progettiConclusi.isEmpty()) {
                                    for (Object obj : progettiConclusi) {
                                        Progetto progetto = (Progetto) obj;
                        %>
                                        <div class="currentProjectList">
                                            <a href="<%=request.getContextPath()%>/Progetto/project.jsp?id=<%=progetto.getIdProgetto()%>"><%=progetto.getNome()%></a>
                                        </div>
                        <%
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
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

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous">

    </script>

</body>
</html>
