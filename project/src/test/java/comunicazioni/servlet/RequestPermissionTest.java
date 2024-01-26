package comunicazioni.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import account.bean.Utente;
import comunicazioni.bean.Permesso;
import comunicazioni.bean.PermessoDAO;
import comunicazioni.servlet.RequestPermission;

class RequestPermissionTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private DataSource dataSource;

    @Mock
    private PermessoDAO permessoDAO;

    private RequestPermission servlet;

    @BeforeEach
    void setUp() throws ServletException, SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new RequestPermission();
        servlet.init();
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext().getAttribute("DataSource")).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(null); // Puoi sostituire con il tuo mock di DataSource
    }

    @Test
    void testDoGetValidRequest() throws ServletException, IOException, SQLException {
        when(session.getAttribute("idAzienda")).thenReturn("azienda123");
        when(session.getAttribute("utente")).thenReturn(new Utente("utente@example.com", "Nome", "Cognome", "ruolo", "azienda123", false, null));
        when(request.getParameter("dalGiorno")).thenReturn("2022-01-01");
        when(request.getParameter("alGiorno")).thenReturn("2022-01-02");
        when(request.getParameter("motivazione")).thenReturn("Vacanza");

        servlet.doGet(request, response);

        // Verifica che il metodo doSave del DAO sia stato chiamato
        verify(permessoDAO).doSave(any(Permesso.class));

        // Verifica che la servlet reindirizzi correttamente
        verify(request).setAttribute("notification", "Permesso aggiunto con successo!");
        verify(request.getRequestDispatcher(null)).forward(request, response);
    }

    @Test
    void testDoGetInvalidDates() throws ServletException, IOException, SQLException {
        when(request.getParameter("dalGiorno")).thenReturn("2022-01-02");
        when(request.getParameter("alGiorno")).thenReturn("2022-01-01");

        servlet.doGet(request, response);

        // Verifica che il metodo doSave del DAO non sia stato chiamato
        verify(permessoDAO, never()).doSave(any(Permesso.class));

        // Verifica che la servlet reindirizzi correttamente
        verify(request).setAttribute("notification", "Errore: le date non rispettano il formato");
        verify(request.getRequestDispatcher(null)).forward(request, response);
    }

    // Puoi aggiungere altri test se necessario

}
