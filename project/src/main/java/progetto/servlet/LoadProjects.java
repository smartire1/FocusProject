package progetto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import progetto.bean.*;

@WebServlet("/LoadProjects")
public class LoadProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProgettoDAO progettoDAO = new ProgettoDAO(ds);
		
		HttpSession session = request.getSession();
		String piva = (String) session.getAttribute("piva");
	

		String progettoIdParam = request.getParameter("id");
		if (progettoIdParam != null && !progettoIdParam.isEmpty()) {
			int progettoId = Integer.parseInt(progettoIdParam);
			
			try {
				Progetto progetto = progettoDAO.doRetrieveByKey(progettoId, piva);
			    request.setAttribute("progetto", progetto);
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			request.getRequestDispatcher("/path/to/project.jsp").forward(request, response);
		}

		else {

			Collection<Progetto> progettiAttivi = null;
			Collection<Progetto> progettiConclusi = null;

			try {
				progettiAttivi = progettoDAO.doRetrieveAllCurrent(piva);
				progettiConclusi = progettoDAO.doRetrieveAllFinished(piva);

				request.setAttribute("progetti_attivi", progettiAttivi);
				request.setAttribute("progetti_conclusi", progettiConclusi);

			} catch (SQLException e) {
				System.out.println(e);
			}

			if (progettiAttivi == null) {
				System.out.println("progettiAttivi = null");
				progettiAttivi = new ArrayList<>();
			}

			if (progettiConclusi == null) {
				System.out.println("progettiConclusi = null");
				progettiConclusi = new ArrayList<>();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Progetto/projectDashboard.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
