package dipendenti.bean;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssegnatoADAOTest {

    private DataSource mockDataSource;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = mock(DataSource.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testDoSave() throws SQLException {
        AssegnatoADAO assegnatoADAO = new AssegnatoADAO(mockDataSource);

        AssegnatoA assegnato = new AssegnatoA(1, "utente123");

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);

        assegnatoADAO.doSave(assegnato);

        verify(mockPreparedStatement).setInt(1, assegnato.getId_turno());
        verify(mockPreparedStatement).setString(2, assegnato.getId_utente());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDoDelete() throws SQLException {
        AssegnatoADAO assegnatoADAO = new AssegnatoADAO(mockDataSource);

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);

        assegnatoADAO.doDelete("utente123", 1);

        verify(mockPreparedStatement).setString(1, "utente123");
        verify(mockPreparedStatement).setInt(2, 1);
        verify(mockPreparedStatement).executeUpdate();
    }
}
