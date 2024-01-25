package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import account.bean.*;
import comunicazioni.bean.*;

@WebServlet("/RequestPermission")
public class RequestPermission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i parametri dalla richiesta
        HttpSession session = request.getSession();
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        String idAzienda = (String) session.getAttribute("idAzienda");
        Utente utente = (Utente) session.getAttribute("utente");
        String dalGiorno = request.getParameter("dalGiorno");
        String alGiorno = request.getParameter("alGiorno");
        String motivazione = request.getParameter("motivazione");
        
        String notification = "";
        
        // Verifica se le stringhe sono vuote o null 
        if (dalGiorno == null || alGiorno == null || dalGiorno.isEmpty() || alGiorno.isEmpty()) {
        	System.out.println("date nulle o vuote");
        	notification = "date non inserite";
        }        
        
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dalGiornoDate = LocalDate.parse(dalGiorno, formatter);
		LocalDate alGiornoDate = LocalDate.parse(alGiorno, formatter);
        
        // Controllo che la data "dalGiorno" non sia successiva alla data "alGiorno"
        if (dalGiornoDate.isAfter(alGiornoDate)) {           
            System.out.println("Errore: la data 'dalGiorno' è successiva a 'alGiorno'");
            notification = "Errore: le date non rispettano il formato";
        }
        
        else {
	        Permesso permesso = new Permesso(0, dalGiorno, alGiorno, motivazione, null, utente.getEmail());
	        PermessoDAO permessoDAO = new PermessoDAO(ds);
	        
	        try {
	        	permessoDAO.doSave(permesso);
	        	System.out.println("Permesso aggiunto con successo!");
	        	notification = "Permesso aggiunto con successo!";
	        }
	        catch(SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("Aggiunta permesso fallita");
	        	notification = "Aggiunta permesso fallita";
	        }
        }
        
        request.setAttribute("notification", notification);
        request.getRequestDispatcher("/LoadData").forward(request, response);
        
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
