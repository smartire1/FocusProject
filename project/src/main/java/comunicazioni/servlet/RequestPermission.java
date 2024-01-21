package comunicazioni.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        
        // Correggiamo il formato diverso
        dalGiorno = convertToDatabaseFormat(dalGiorno);
        alGiorno = convertToDatabaseFormat(alGiorno);
        System.out.println(dalGiorno + ", " + alGiorno);
        
        // Controllo che la data "dalGiorno" non sia successiva alla data "alGiorno"
        if (isDateAfter(dalGiorno, alGiorno)) {
            response.setContentType("text/html");
            System.out.println("Errore: la data 'dalGiorno' è successiva a 'alGiorno'");
            notification = "Errore: le date non rispettano il formato";
        }
        
        else {
	        Permesso permesso = new Permesso(0, dalGiorno, alGiorno, motivazione, false, utente.getEmail());
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
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Comunicazioni/communicationDashboard.jsp");
        dispatcher.forward(request, response);
        
	}
	
    // Metodo per verificare se una data è successiva a un'altra data
    private boolean isDateAfter(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.after(d2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Metodo per convertire la data da "MM/dd/yyyy" a "yyyy-MM-dd"
    private String convertToDatabaseFormat(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(inputDate);
            String formattedDate = outputFormat.format(date);

            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
