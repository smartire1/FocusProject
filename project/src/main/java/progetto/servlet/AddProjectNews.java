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

import progetto.bean.Progetto;
import progetto.bean.ProgettoDAO;

@WebServlet("/AddProjectNews")
public class AddProjectNews extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    
	    String progettoIdParam = request.getParameter("idProject");
	    String nuovoAvviso = request.getParameter("newAvviso");
	    
	    Progetto progetto;
	    ProgettoDAO progettoDAO = new ProgettoDAO(ds);
	    
	    try {
	    	progetto = progettoDAO.doRetrieveByKey(Integer.parseInt(progettoIdParam), piva);
	    	progetto.setAvvisi(nuovoAvviso);
	    	progettoDAO.doUpdate(progetto);
	    	System.out.println("Avviso Aggiornato, nuovo avviso: " + nuovoAvviso);
	    	request.setAttribute("id", String.valueOf(progetto.getIdProgetto()));
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
		
	    request.getRequestDispatcher("/LoadProjects").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
