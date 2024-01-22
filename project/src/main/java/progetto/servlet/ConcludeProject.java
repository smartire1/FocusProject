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
		String op = request.getParameter("action");
		int progettoId = 0;

		if (progettoIdParam != null && !progettoIdParam.isEmpty())
		    progettoId = Integer.parseInt(progettoIdParam);
		
        // Cercare il progetto nel db
		ProgettoDAO progettoDAO = new ProgettoDAO(ds);
		TaskDAO taskDAO = new TaskDAO(ds);
		Progetto progetto;
		
		//Gestione conclusione progetto (Responsabile)
		if(op.equals("concludi")) {
			try {
				// Ci sono task associati?
				Collection<Task> tasks = taskDAO.doRetrieveAllByProjectInProgress(progettoId, idAzienda);

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
			
		//Gestione eliminazione del progetto (dirigente)	
		} else if (op.equals("elimina")) {
			try {
				LavoraDAO lavoraDAO = new LavoraDAO(ds);
				Collection<Lavora> dipendenze = lavoraDAO.doRetriveByProject(progettoId);
				Collection<Task> tasks = taskDAO.doRetrieveAllByProject(progettoId, idAzienda);
				
				//Rimozione delle dipendenze tra dipendenti e progetto
				for(Lavora l: dipendenze) {
					lavoraDAO.doDelete(l);
				}	
				
				//Rimozione di tutti i task associati a quel progetto
				for(Task t: tasks) {
					taskDAO.doDelete(t.getIdTask());	
				}
				
				//Rimozione del progetto dal db
				progettoDAO.doDelete(progettoId);
				
				System.out.println("Progetto rimosso con successo");
				request.getRequestDispatcher("/LoadProjects").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}