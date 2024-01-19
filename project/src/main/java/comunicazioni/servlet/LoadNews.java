package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import comunicazioni.bean.*;

@WebServlet("/LoadNews")
public class LoadNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    HttpSession session = request.getSession();
	    String piva = (String) session.getAttribute("idAzienda");
	    
	    List<Comunicazione> news = new ArrayList<>();
	    ComunicazioneDAO comunicazioneDAO = new ComunicazioneDAO(ds);
	    
	    try {
	    	news = comunicazioneDAO.doRetrieveAll(piva);
	    	
	    	// Controlli
	    	if(news.isEmpty()) {
	    		System.out.println("La lista delle news è vuota!");
	    	} else {
	    		Collections.reverse(news);
	    	}
	    	
	    	request.setAttribute("news", news);
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
