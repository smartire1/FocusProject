package account.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogoutTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private Logout logoutServlet;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    void testDoGet() throws ServletException, IOException {
        logoutServlet.doGet(request, response);

        verify(session).removeAttribute("utente");
        verify(session).removeAttribute("idAzienda");
        verify(session).invalidate();
        verify(response).sendRedirect(request.getContextPath());
    }
 
    @Test
    void testDoPost() throws ServletException, IOException {
        testDoGet();
    }
}
