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

import comunicazioni.bean.Permesso;
import comunicazioni.bean.PermessoDAO;
import comunicazioni.servlet.PermissionManagement;

class PermissionManagementTest {

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

    private PermissionManagement servlet;

    @BeforeEach
    void setUp() throws ServletException, SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new PermissionManagement();
        servlet.init();
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext().getAttribute("DataSource")).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(null); // Puoi sostituire con il tuo mock di DataSource
    }

    @Test
    void testDoGetAccetta() throws ServletException, IOException, SQLException {
        when(request.getParameter("permitId")).thenReturn("1");
        when(request.getParameter("action")).thenReturn("accetta");

        Permesso permesso = new Permesso(1, "2022-01-01", "2022-01-02", "Vacanza", false, "utente@example.com");
        when(permessoDAO.doRetrieveByKey(1, "idAzienda")).thenReturn(permesso);

        servlet.doGet(request, response);

        // Verifica che il metodo doUpdate del DAO sia stato chiamato
        verify(permessoDAO).doUpdate(any(Permesso.class));

        // Verifica che la servlet reindirizzi correttamente
        verify(response).sendRedirect(request.getContextPath() + "/LoadData");
    }

    @Test
    void testDoGetRifiuta() throws ServletException, IOException, SQLException {
        when(request.getParameter("permitId")).thenReturn("2");
        when(request.getParameter("action")).thenReturn("rifiuta");

        Permesso permesso = new Permesso(2, "2022-02-01", "2022-02-02", "Malattia", false, "utente@example.com");
        when(permessoDAO.doRetrieveByKey(2, "idAzienda")).thenReturn(permesso);

        servlet.doGet(request, response);

        // Verifica che il metodo doUpdate del DAO sia stato chiamato
        verify(permessoDAO).doUpdate(any(Permesso.class));

        // Verifica che la servlet reindirizzi correttamente
        verify(response).sendRedirect(request.getContextPath() + "/LoadData");
    }

    // Puoi aggiungere altri test se necessario

}

