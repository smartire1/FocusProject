package dipendenti.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dipendenti.bean.AssegnatoADAO;
import dipendenti.bean.TurnoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveTurnoTest {

    @InjectMocks
    private RemoveTurno servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private DataSource mockDataSource;

    @Mock
    private TurnoDAO mockTurnoDAO;

    @Mock
    private AssegnatoADAO mockAssegnatoADAO;

    @Mock
    private RequestDispatcher mockRequestDispatcher;

    @Mock
    private ServletContext mockServletContext;

    @BeforeEach
    public void setUp() {
        // Mock ServletConfig and initialize the servlet
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(mockServletContext);
        try {
			servlet.init(servletConfig);
		} catch (ServletException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testDoGet() throws ServletException, IOException, SQLException {
        // Configura il comportamento dei mock
        when(request.getParameter("turnoId")).thenReturn("1");
        when(request.getParameter("utenteId")).thenReturn("utente@example.com");
        when(mockServletContext.getAttribute("DataSource")).thenReturn(mockDataSource);
        when(mockDataSource.getConnection()).thenReturn((Connection) mock(Connection.class));
        when(mockDataSource.getConnection().prepareStatement(any(String.class))).thenReturn((PreparedStatement) mock(PreparedStatement.class));
        when(request.getRequestDispatcher("/LoadEmployees")).thenReturn(mockRequestDispatcher);

        // Simula la chiamata alla servlet
        servlet.doGet(request, response);

        // Verifica che i metodi siano stati chiamati correttamente
        verify(mockAssegnatoADAO).doDelete("utente@example.com", 1);
        verify(mockTurnoDAO).doDelete(1);
        verify(request).getRequestDispatcher("/LoadEmployees");
        verify(mockRequestDispatcher).forward(request, response);
    }
}
