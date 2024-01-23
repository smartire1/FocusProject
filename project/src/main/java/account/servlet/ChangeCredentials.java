package account.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import account.bean.*;

@WebServlet("/ChangeCredentials")
public class ChangeCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// NOTA: manca la logica per modificare solo l'email o solo la password!
		
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String piva = (String) session.getAttribute("idAzienda");
        UtenteDAO utenteDAO = new UtenteDAO(ds);
        
        String notification = "";
        
        // Prendiamo i parametri inviati tramite form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
		// Se i parametri non rispettano il formato
        if(!utente.isValidInput(utente.getNome(), utente.getCognome(), email, password)) {
			notification = "I parametri inseriti non rispettano il formato";
		}
        
     // I parametri rispettano il formato
        else {
	        if(utente.getEmail().equals(email)) {
	        	notification = "L'email inserita è uguale a quella precedente";
	        	System.out.println("L'email inserita è uguale a quella precedente");
	        }
	        
	        else if(utente.getPassword().equals(password)) {
	        	notification = "La password inserita è uguale a quella precedente";
	        	System.out.println("La password inserita è uguale a quella precedente");
	        }
	        
	        else {
		        try {
		        	// Controlliamo se esiste un utente con quella e-mail
		        	if(utenteDAO.doRetrieveByKey(email) != null) {
		        		notification = "L'email inserita è già in uso";
		        	}
		        	
		        	// Se l'-email non è già in uso
		        	else {
		        		utenteDAO.doUpdateCredentials(utente, email, password);;
		        		
		        		// Aggiorniamo l'attributo di sessione 'utente'
		        		utente = utenteDAO.doRetrieveByKey(email, piva);
		        		session.setAttribute("utente", utente);
		        		
		        		System.out.println("Modifiche effettuate con successo!");
		        		notification = "Modifiche effettuate con successo!";
		        	}
		        }
		        catch(SQLException e) {
		        	e.printStackTrace();
		        	notification = "Modifica credenziali fallita";
		        }
	        }
		}
        
        session.setAttribute("notification", notification);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Account/userArea.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
