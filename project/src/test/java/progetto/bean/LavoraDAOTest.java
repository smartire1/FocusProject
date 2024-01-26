package progetto.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LavoraDAOTest {

    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private LavoraDAO lavoraDAO;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        lavoraDAO = new LavoraDAO(dataSource);

        // Configura il comportamento del mock per restituire il collegamento, il PreparedStatement e il ResultSet
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testDoSave() throws SQLException {
        // Crea un oggetto Lavora per il test
        Lavora lavora = new Lavora("test@example.com", 1);

        // Esegui il metodo doSave() e verifica che non ci siano eccezioni
        assertDoesNotThrow(() -> lavoraDAO.doSave(lavora));

        // Verifica che i metodi appropriati siano stati chiamati
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "test@example.com");
        verify(preparedStatement, times(1)).setInt(2, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testDoDelete() throws SQLException {
        // Crea un oggetto Lavora per il test
        Lavora lavora = new Lavora("test@example.com", 1);

        // Esegui il metodo doDelete() e verifica che non ci siano eccezioni
        assertDoesNotThrow(() -> lavoraDAO.doDelete(lavora));

        // Verifica che i metodi appropriati siano stati chiamati
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "test@example.com");
        verify(preparedStatement, times(1)).setInt(2, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testDoRetriveByProject() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("email")).thenReturn("test@example.com");
        when(resultSet.getInt("id_progetto")).thenReturn(1);

        // Esegui il metodo doRetriveByProject()
        Collection<Lavora> result = lavoraDAO.doRetriveByProject(1);

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertEquals(1, result.size());
        Lavora lavora = result.iterator().next();
        assertEquals("test@example.com", lavora.getEmail());
        assertEquals(1, lavora.getId_progetto());
    }

    @Test
    void testDoRetriveByUser() throws SQLException {
        // Configura il comportamento del mock per restituire un ResultSet simulato
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("email")).thenReturn("test@example.com");
        when(resultSet.getInt("id_progetto")).thenReturn(1);

        // Esegui il metodo doRetriveByUser()
        Lavora result = lavoraDAO.doRetriveByUser("test@example.com");

        // Verifica che il metodo appropriato sia stato chiamato
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "test@example.com");
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica il risultato
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals(1, result.getId_progetto());
    }
}
