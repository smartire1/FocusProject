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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ---------------------------------------------------------
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    Utente utente = (Utente) session.getAttribute("utente");
	    
	    if(utente == null) {
	    	response.sendRedirect(request.getContextPath() + "/Account/login.jsp");
	    	return;
	    }
	    // ---------------------------------------------------------
	    LavoraDAO lavoraDAO = new LavoraDAO(ds);
	    ProgettoDAO progettoDAO = new ProgettoDAO(ds);
	    UtenteDAO utenteDAO = new UtenteDAO(ds);
	    // ---------------------------------------------------------
	    String progettoIdParam = request.getParameter("id");
	    String progettoIdAtt = (String) request.getAttribute("id");
	    
	    int progettoId = -1;
	    
	    if (progettoIdParam != null && !progettoIdParam.isEmpty()) {
	        progettoId = Integer.parseInt(progettoIdParam);
	    } 
	    else if (progettoIdAtt != null && !progettoIdAtt.isEmpty()) {
	        progettoId = Integer.parseInt(progettoIdAtt);
	    } 
	    // ---------------------------------------------------------
	    
	    List<Utente> subordinati = new ArrayList<>();
	    List<Utente> subordinatiProj = new ArrayList<>();
	    List<Utente> responsabili = new ArrayList<>();
	    
	    // L'utente ha chiesto informazioni su un Progetto specifico
	    if (progettoId != -1) {
	        try {
	        	// Otteniamo tutti i subordinati che lavorano a quel progetto
	            Collection<Lavora> subordinatiLavora = lavoraDAO.doRetriveByProject(progettoId);

	            for (Lavora l : subordinatiLavora) {
	            	subordinatiProj.add(utenteDAO.doRetrieveByKey(l.getEmail(), piva));
	            }	            
	            subordinati = utenteDAO.doRetriveByNotProject(progettoId, piva);
	            
	            // Otteniamo il progetto e il responsabile associato
	            Progetto progetto = progettoDAO.doRetrieveByKey(progettoId, piva);
	            Utente responsabile = utenteDAO.doRetrieveByKey(progetto.getResponsabile_email(), piva);
	            responsabili = utenteDAO.doRetriveByNotProjectResp(progetto.getResponsabile_email(), piva);
	            
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
	    
	 // ------------------------------------------------------------------------------------------------------------------
	    
	    // Se l'ID del progetto non è presente nella richiesta, gestire la visualizzazione della dashboard
	    else {
	    	// Se l'utente è il dirigente
	    	if(utente.getRuolo().equals("dirigente")) {
		        Collection<Progetto> progettiAttivi = null;
		        Collection<Progetto> progettiConclusi = null;
		        Collection<Utente> responsabiliDisp = new ArrayList<>();
		        
		        try {
		            // Ottenere la lista di progetti attivi e conclusi associati all'azienda
		            progettiAttivi = progettoDAO.doRetrieveAllByStato(piva, false);
		            progettiConclusi = progettoDAO.doRetrieveAllByStato(piva, true);
		            responsabiliDisp = utenteDAO.doRetrieveAllResponsabili(piva);
		            
		            request.setAttribute("responsabiliDisp", responsabiliDisp);
		            request.setAttribute("progettiAttivi", progettiAttivi);
		            request.setAttribute("progettiConclusi", progettiConclusi);
		            
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
	    	}
	    	
	    	// Se l'utente è un responsabile
	    	if(utente.getRuolo().equals("responsabile")) {
		        Collection<Progetto> progettiAttiviResp = null;
		        Collection<Progetto> progettiConclusiResp = null;		        
		        
		        try {
		            // Ottenere la lista di progetti attivi e conclusi associati all'azienda
		        	
		        	progettiAttiviResp = progettoDAO.doRetrieveAllByRespAndStato(utente.getEmail(), piva, false);
		        	progettiConclusiResp = progettoDAO.doRetrieveAllByRespAndStato(utente.getEmail(), piva, true);          
		        	
		            request.setAttribute("progettiAttiviResp", progettiAttiviResp);
		            request.setAttribute("progettiConclusiResp", progettiConclusiResp);
		            
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
	    	}
	        
	        // Se l'utente è un subordinato
	        if(utente.getRuolo().equals("subordinato")) {
		        Collection<Progetto> progettiAttiviSub = null;
		        Collection<Progetto> progettiConclusiSub = null;
	        	
	        	try {
	        		progettiAttiviSub = progettoDAO.doRetrieveAllBySubAndStato(utente.getEmail(), piva, false);
	        		progettiConclusiSub = progettoDAO.doRetrieveAllBySubAndStato(utente.getEmail(), piva, true);
	        		
		            request.setAttribute("progettiAttiviSub", progettiAttiviSub);
		            request.setAttribute("progettiConclusiSub", progettiConclusiSub);
		            
	        	} catch (SQLException e) {
		            e.printStackTrace();
		        }
	        }

	        // Inoltrare la richiesta alla JSP dedicata alla visualizzazione della dashboard
	        request.getRequestDispatcher("/Progetto/projectDashboard.jsp").forward(request, response);
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
