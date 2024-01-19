package progetto.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import progetto.bean.Lavora;
import progetto.bean.LavoraDAO;



@WebServlet("/AddSubProject")
public class AddSubProject extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    LavoraDAO lavoraDAO = new LavoraDAO(ds);
	    
	    String email = request.getParameter("email");
	    int idProject = Integer.parseInt(request.getParameter("idProject"));
	    
	    Lavora lavora = new Lavora(email, idProject);
	    
        try {
            // Inserimento del subordinato nel progetto
            lavoraDAO.doSave(lavora);

            // Gestione della risposta
            response.sendRedirect(request.getContextPath() + "/LoadProjects");
        } catch (Exception e) {
            e.printStackTrace(); // Puoi gestire l'errore in modo più appropriato a seconda delle tue esigenze
        }	    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
