package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import account.bean.Utente;
import account.bean.UtenteDAO;
import progetto.bean.*;

@WebServlet("/LoadProjects")
public class LoadProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    LavoraDAO lavoraDAO = new LavoraDAO(ds);
	    ProgettoDAO progettoDAO = new ProgettoDAO(ds);
	    UtenteDAO utenteDAO = new UtenteDAO(ds);
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    
	    String progettoIdParam = request.getParameter("id");
	    String progettoIdAtt = (String) request.getAttribute("id");

	    int progettoId = -1;

	    if (progettoIdParam != null && !progettoIdParam.isEmpty()) {
	        progettoId = Integer.parseInt(progettoIdParam);
	    } 

	    else if (progettoIdAtt != null && !progettoIdAtt.isEmpty()) {
	        progettoId = Integer.parseInt(progettoIdAtt);
	    } 
	    
	    List<Utente> subordinati = new ArrayList<>();
	    List<Utente> subordinatiProj = new ArrayList<>();
	    List<Utente> responsabili = new ArrayList<>();
	    
	    // L'utente ha chiesto informazioni su un Progetto specifico
	    if (progettoId != -1) {
			
	        try {

	        	// Otteniamo tutti i subordinati che lavorano a quel progetto
	            Collection<Lavora> subordinatiLavora = lavoraDAO.doRetriveByProject(progettoId);

	            for (Lavora l : subordinatiLavora) {
	            	subordinatiProj.add(utenteDAO.doRetrieveByKey(l.getEmail()));
	            }	            
	            subordinati = utenteDAO.doRetriveByNotProject(progettoId, piva);
	            
	            // Otteniamo il progetto e il responsabile associato
	            Progetto progetto = progettoDAO.doRetrieveByKey(progettoId, piva);
	            Utente responsabile = utenteDAO.doRetrieveByKey(progetto.getResponsabile_email());
	            responsabili = utenteDAO.doRetriveByNotProjectResp(progetto.getResponsabile_email(), piva);
	            // Impostiamo gli attributi per la visualizzazione nella JSP
	            request.setAttribute("responsabili", responsabili);
	            request.setAttribute("subordinatiProj", subordinatiProj);
	            request.setAttribute("subordinati", subordinati);
	            request.setAttribute("progetto", progetto);
	            request.setAttribute("responsabile", responsabile);

	        } catch (SQLException e) {
	            System.out.println(e);
	        }
			
	        request.getRequestDispatcher("/Progetto/project.jsp").forward(request, response);

	    }
	    
	    // Se l'ID del progetto non è presente nella richiesta, gestire la visualizzazione della dashboard
	    else {
	        Collection<Progetto> progettiAttivi = null;
	        Collection<Progetto> progettiConclusi = null;

	        try {
	            // Ottenere la lista di progetti attivi e conclusi associati all'azienda
	            progettiAttivi = progettoDAO.doRetrieveAllCurrent(piva);
	            progettiConclusi = progettoDAO.doRetrieveAllFinished(piva); 

	            // Impostare gli attributi per la visualizzazione nella JSP
	            request.setAttribute("progetti_attivi", progettiAttivi);
	            request.setAttribute("progetti_conclusi", progettiConclusi);

	        } catch (SQLException e) {
	            System.out.println(e);
	        }

	        // Inoltrare la richiesta alla JSP dedicata alla visualizzazione della dashboard
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Progetto/projectDashboard.jsp");
	        try {
	            dispatcher.forward(request, response);
	        } catch (ServletException | IOException e) {
	            System.err.println(e);
	        }
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
