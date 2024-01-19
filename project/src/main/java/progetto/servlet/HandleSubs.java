package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import progetto.bean.Lavora;
import progetto.bean.LavoraDAO;
import progetto.bean.Task;
import progetto.bean.TaskDAO;



@WebServlet("/HandleSubs")
public class HandleSubs extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    
	    String op = request.getParameter("action");
	    System.out.println(op + " operazione\n");
	    String email = request.getParameter("email");
	    String idProject = request.getParameter("idProject");
	    int idProjectInt = Integer.parseInt(request.getParameter("idProject"));
	    
	    if(op.equals("insert")) {		    
		    LavoraDAO lavoraDAO = new LavoraDAO(ds);
		    
		    System.out.println("\nInserisci");
		    
		    Lavora lavora = new Lavora(email, idProjectInt);
		    
	        try {
	            // Inserimento del subordinato nel progetto
	            lavoraDAO.doSave(lavora);
	
				System.out.println("Subordinato correttamente aggiunto");	            
				request.setAttribute("id", idProject);
				request.getRequestDispatcher("/LoadProjects").forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace(); // Puoi gestire l'errore in modo più appropriato a seconda delle tue esigenze
	        }
	    }
	    
	    if(op.equals("remove")) {
	    	TaskDAO taskDAO = new TaskDAO(ds);
		    System.out.println("\nRimuovi");	    	
	    	try {
				Collection<Task> tasks = taskDAO.doRetrieveAllByProjectAndUser(idProjectInt, email);
				if(tasks.isEmpty()) {
					LavoraDAO lavoraDAO = new LavoraDAO(ds);
					Lavora lavora = lavoraDAO.doRetriveByUser(email);
					lavoraDAO.doDelete(lavora);
					request.setAttribute("id", idProject);
					request.getRequestDispatcher("/LoadProjects").forward(request, response);
				}
				else {
					System.out.println("Impossibile rimuovere il subordinato in quanto ci sono altri task attivi assegnati");
					request.setAttribute("id", idProject);
					request.setAttribute("Error", "Impossibile rimuovere il subordinato, task ancora attivi");
					request.getRequestDispatcher("/LoadTask").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
