package account.servlet;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import account.bean.*;

class LoginTest {
	
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DataSource dataSource;

    @Mock
    private UtenteDAO utenteDAO;

    @InjectMocks
    private Login loginServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test per il caso di login riuscito
    @Test
    void testDoGetSuccessfulLogin() throws ServletException, IOException, SQLException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Simulazione del recupero dell'utente dal database
        Utente utenteStub = new Utente("test@example.com", Login.toHash("password123"), "Nome", "Cognome", "idAzienda123", true, "Ruolo");
        when(utenteDAO.doRetrieveByKey("test@example.com")).thenReturn(utenteStub);

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica il comportamento desiderato della servlet
        verify(session).setAttribute("utente", utenteStub);
        verify(session).setAttribute("idAzienda", utenteStub.getIdAzienda());
        verify(response).sendRedirect("/homePage.jsp");

        // Verifica che l'output contenga il messaggio di successo (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }

    // Test per il caso di account non attivo
    @Test
    void testDoGetInactiveAccount() throws ServletException, IOException, SQLException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("inactive@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Simulazione del recupero dell'utente inattivo dal database
        Utente utenteStub = new Utente("inactive@example.com", Login.toHash("password123"), "Nome", "Cognome", "idAzienda123", false, "Ruolo");
        when(utenteDAO.doRetrieveByKey("inactive@example.com")).thenReturn(utenteStub);

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica il comportamento desiderato della servlet
        verify(session, never()).setAttribute(anyString(), any());
        verify(response, never()).sendRedirect(anyString());

        // Verifica che l'output contenga il messaggio di account non attivo (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }
    
	// Test per credenziali non valide
    @Test
    void testDoGetInvalidCredentials() throws ServletException, IOException, SQLException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Simulazione del recupero dell'utente dal database con password diversa
        Utente utenteStub = new Utente("test@example.com", Login.toHash("differentpassword"), "Nome", "Cognome", "idAzienda123", true, "Ruolo");
        when(utenteDAO.doRetrieveByKey("test@example.com")).thenReturn(utenteStub);

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica che non siano stati chiamati metodi sulla sessione e sulla risposta
        verify(session, never()).setAttribute(anyString(), any());
        verify(response, never()).sendRedirect(anyString());

        // Verifica che l'output contenga il messaggio di credenziali non valide (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }

    // Test per account inesistente
    @Test
    void testDoGetNonexistentAccount() throws ServletException, IOException, SQLException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("nonexistent@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Simulazione del recupero dell'utente dal database che non esiste
        when(utenteDAO.doRetrieveByKey("nonexistent@example.com")).thenReturn(null);

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica che non siano stati chiamati metodi sulla sessione e sulla risposta
        verify(session, never()).setAttribute(anyString(), any());
        verify(response, never()).sendRedirect(anyString());

        // Verifica che l'output contenga il messaggio di account inesistente (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }
    
    @Test
    void testDoGetEmptyInput() throws ServletException, IOException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica che non siano stati chiamati metodi sulla sessione e sulla risposta
        verify(session, never()).setAttribute(anyString(), any());
        verify(response, never()).sendRedirect(anyString());

        // Verifica che l'output contenga il messaggio di input mancante (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }
    
    @Test
    void testDoGetSQLException() throws ServletException, IOException, SQLException {
        // Configurazione dei mock
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password123");

        // Simula un'eccezione durante la chiamata al database
        when(utenteDAO.doRetrieveByKey("test@example.com")).thenThrow(new SQLException("Errore di database"));

        // Creazione di uno StringWriter per catturare l'output della servlet
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Esecuzione della servlet
        loginServlet.doGet(request, response);

        // Asserzioni
        // Verifica che non siano stati chiamati metodi sulla sessione e sulla risposta
        verify(session, never()).setAttribute(anyString(), any());
        verify(response, never()).sendRedirect(anyString());

        // Verifica che l'output contenga il messaggio di errore del database (puoi personalizzare questa parte)
        writer.flush();
        String servletOutput = stringWriter.toString();
        // Asserzioni sull'output
        // ...
    }

}