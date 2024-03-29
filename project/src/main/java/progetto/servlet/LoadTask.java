package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import progetto.bean.*;
import account.bean.*;

@WebServlet("/LoadTask")
public class LoadTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    Utente utente = (Utente) session.getAttribute("utente");
	    
	    if(utente == null) {
	    	response.sendRedirect(request.getContextPath() + "/Account/login.jsp");
	    	return;
	    }
	    
	    /* ---------------------------------------------- */
	    
	    String progettoIdParam = request.getParameter("id");

	    if (progettoIdParam == null || progettoIdParam.equals("")) {
	        // System.out.println("LOAD_TASK: id parameter is NULL or EMPTY");
	        
	        Object idAttribute = request.getAttribute("id");
	        if (idAttribute == null || idAttribute.toString().equals("")) {
	            System.out.println("LOAD_TASK: Id Progetto is NULL or is EMPTY");
	            request.getRequestDispatcher("/Progetto/projectDashboard.jsp").forward(request, response);
	            return;
	        }

	        progettoIdParam = idAttribute.toString();
	    }

	    int progettoId = Integer.parseInt(progettoIdParam);
	    /* ---------------------------------------------- */
	    
	    UtenteDAO utenteDAO = new UtenteDAO(ds);
	    TaskDAO taskDAO = new TaskDAO(ds);
	    LavoraDAO lavoraDAO = new LavoraDAO(ds);
	    ProgettoDAO progettoDAO = new ProgettoDAO(ds);
	    
	    try {
	    	Collection<Lavora> dipendenti = lavoraDAO.doRetriveByProject(progettoId);
	    	
	    	// Lista di subordinati che lavorano al progetto corrente
	    	List<Utente> subordinati = new ArrayList<>();
	    	
	    	// HashMap che contiene la lista dei task di ogni subordinati che lavora al progetto corrente
	    	Map<String, List<Task>> taskProgetto = new HashMap<>();
	    	
	    	for(Lavora l : dipendenti) {
	    		// Reperiamo la lista dei subordinati che lavorano a quel progetto
	    		subordinati.add(utenteDAO.doRetrieveByKey(l.getEmail(), piva));
	    		
	    		// Reperiamo la lista dei task associati a quel subordinato
	    		List<Task> task = (List<Task>) taskDAO.doRetrieveAllByProjectAndUser(progettoId, l.getEmail());
	    		
	    		// ... e la salviamo in un hashMap
	    		taskProgetto.put(l.getEmail(), task);   		
	    		
	    	}
	    	Progetto progetto = progettoDAO.doRetrieveByKey(progettoId, piva);
	    	boolean isFinish = progetto.isStato();
	    	
	    	request.setAttribute("isFinish", isFinish);	    	
	    	request.setAttribute("progettoId", progettoId);
	    	request.setAttribute("subordinati", subordinati);
	    	request.setAttribute("taskProgetto", taskProgetto);
		    request.getRequestDispatcher("/Progetto/task.jsp").forward(request, response);	    	
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
