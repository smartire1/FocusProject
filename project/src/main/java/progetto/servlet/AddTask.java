package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import progetto.bean.*;

@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    String utenteId = request.getParameter("utenteId");
	    String descrizione = request.getParameter("descrizione");
	    String progettoIdParam = request.getParameter("progettoId");
	    if(progettoIdParam == null || progettoIdParam.equals("")) {
	    	System.out.println("ADD_TASK: Id Progetto is null OR is empty");
	    	request.getRequestDispatcher("/Progetto/projectDashboard.jsp").forward(request, response);
	    	return;
	    }
	    int progettoId = Integer.parseInt(progettoIdParam);
	    
	    Task task = new Task(0, descrizione, false, progettoId, utenteId);
	    TaskDAO taskDAO = new TaskDAO(ds);
	    
	    // Aggiungi task
	    try {
	    	taskDAO.doSave(task);
	    	System.out.println("Task salvato con successo!");
	    	
	    	request.setAttribute("id", progettoId);
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    	System.out.println("Errore salvataggio task");
	    }
	    
	    request.getRequestDispatcher("/LoadTask").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
