package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import progetto.bean.*;

@WebServlet("/ConcludeProject")
public class ConcludeProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		HttpSession session = request.getSession();
		String idAzienda = (String) session.getAttribute("idAzienda");
		
		// Ottieni i parametri necessari dalla richiesta
		String progettoIdParam = request.getParameter("id_progetto");
		int progettoId = 0;

		if (progettoIdParam != null && !progettoIdParam.isEmpty())
		    progettoId = Integer.parseInt(progettoIdParam);
		
        // Cercare il progetto nel db
		ProgettoDAO progettoDAO = new ProgettoDAO(ds);
		TaskDAO taskDAO = new TaskDAO(ds);
		Progetto progetto;
		
		try {
			// Ci sono task associati?
			Collection<Task> tasks = taskDAO.doRetrieveAllByProject(progettoId, idAzienda);
			for(Task t: tasks) {
				System.out.println(t.getIdTask() + t.getSubordinatoEmail());
			}
			if(!tasks.isEmpty()) {
				System.out.println("Non è possibile concludere il progetto perché ci sono task associati ancora da completare");
				request.setAttribute("id", String.valueOf(progettoId));				
				request.setAttribute("Error", "Impossibile concludere il progetto, task da completare");
				request.getRequestDispatcher("/LoadTask").forward(request, response);				
			}
			
			else {
				progetto = progettoDAO.doRetrieveByKey(progettoId, idAzienda);
				progetto.setStato(true);
				progettoDAO.doUpdate(progetto);
				
				System.out.println("Progetto Spostato nella sezione progetti conclusi");				
				request.setAttribute("id", String.valueOf(progettoId));				
				request.setAttribute("Success", "Progetto Spostato nella sezione progetti conclusi");
				request.getRequestDispatcher("/LoadProjects").forward(request, response);	
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}