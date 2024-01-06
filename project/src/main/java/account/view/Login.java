package account.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;


@WebServlet(name = "Login", value = "/Login")

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        DataSource dataSource = null;
        Connection connessione = null;
        List<Utente> users;

        try {
            // Ottieni il DataSource dal contesto JNDI
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/storage");

            connessione = dataSource.getConnection();
            
            System.out.println("Servlet Login...");
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
}