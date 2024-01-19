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
import progetto.bean.TaskDAO;

@WebServlet("/RemoveTask")
public class RemoveTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        HttpSession session = request.getSession();
        String piva = (String) session.getAttribute("idAzienda");
        
	    /* ---------------------------------------------- */
	    
	    String progettoIdParam = request.getParameter("progettoId");

	    if (progettoIdParam == null || progettoIdParam.equals("")) {
	        System.out.println("REMOVE_TASK: id parameter is NULL or EMPTY");
	        
	        Object idAttribute = request.getAttribute("progettoId");
	        if (idAttribute == null || idAttribute.toString().equals("")) {
	            System.out.println("REMOVE_TASK: Id Progetto is NULL or is EMPTY");
	            request.getRequestDispatcher("/Progetto/projectDashboard.jsp").forward(request, response);
	            return;
	        }

	        progettoIdParam = idAttribute.toString();
	    }

	    int progettoId = Integer.parseInt(progettoIdParam);

	    /* ---------------------------------------------- */

        String taskIdParam = request.getParameter("taskId");
        
        if (taskIdParam == null || taskIdParam.equals("")) {
            System.out.println("REMOVE_TASK: Task ID is null or empty");
            return;
        }

        int taskId = Integer.parseInt(taskIdParam);
        TaskDAO taskDAO = new TaskDAO(ds);

        try {
            taskDAO.doDelete(taskId);
            System.out.println("Task rimosso con successo!");
            
            request.setAttribute("id", progettoId);
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error rimozione task");
        }

        response.sendRedirect(request.getContextPath() + "/LoadTask");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
