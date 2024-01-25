package account.servlet;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Base64;
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
		
		// NOTA: manca la logica per modificare solo l'email o solo la password
		
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
        	// Se le credenziali sono uguali alle precedenti
	        if(utente.getEmail().equals(email) && utente.getPassword().equals(toHash(password))) {
	        	notification = "Le credenziali inserite sono uguali alle precedenti";
	        	System.out.println("Le credenziali inserite sono uguali alle precedenti");
	        }
	        
	        else {
		        try {
		        	// Controlliamo se esiste un utente con quella e-mail che non sia l'utente corrente
		        	if(!email.equals(utente.getEmail()) && utenteDAO.doRetrieveByKey(email, piva) != null) {
		        		notification = "L'email inserita è già in uso";
		        		System.out.println("L'email inserita è già in uso");
		        	}
		        	
		        	// Se l'-email non è già in uso
		        	else {
		        		// Aggiorna le credenziali
		        		utenteDAO.doUpdateCredentials(utente, email, toHash(password));
		        		
		        		// Aggiorniamo l'attributo di sessione 'utente'
		        		utente = utenteDAO.doRetrieveByKey(email, piva);
		        		session.setAttribute("utente", utente);
		        		
		        		notification = "Modifiche effettuate con successo!";
		        		System.out.println("Modifiche effettuate con successo!");
		        	}
		        }
		        catch(SQLException e) {
		        	e.printStackTrace();
		        	notification = "Modifica credenziali fallita";
		        	System.out.println("Modifica credenziali fallita");
		        }
	        }
		}
        
        request.setAttribute("notification", notification);
        request.getRequestDispatcher("/Account/userArea.jsp").forward(request, response);
	}
	
    // Funzione di hashing della password
    public static String toHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
