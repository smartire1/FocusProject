package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import comunicazioni.bean.Permesso;
import comunicazioni.bean.PermessoDAO;

@WebServlet("/PermissionManagement")
public class PermissionManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	        PermessoDAO permessoDAO = new PermessoDAO(ds);
	        String idAzienda = (String) session.getAttribute("idAzienda");
	        
	        String richiedenteEmail = (String) request.getParameter("richiedenteEmail");
	        
	        List<Permesso> permessi = null;
	       
	        try {
	            permessi = permessoDAO.doRetrieveAll(idAzienda);
	            
	            /*
	             * Da finire
	             * 
	             * ...
	             * ...
	             * ...
	             * 
	             * */

	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	}
