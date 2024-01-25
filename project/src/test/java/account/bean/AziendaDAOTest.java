package account.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AziendaDAOTest {

    private AziendaDAO aziendaDAO;
    private DataSource mockDataSource;

    @BeforeEach
    void setUp() throws Exception {
        mockDataSource = Mockito.mock(DataSource.class);
        aziendaDAO = new AziendaDAO(mockDataSource);
    }

    @AfterEach
    void tearDown() throws Exception {
        aziendaDAO = null;
    }

    @Test
    void testDoSave() throws SQLException {
        Azienda azienda = new Azienda("123456789", "Azienda Test");

        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        aziendaDAO.doSave(azienda);

        Mockito.verify(mockPreparedStatement).setString(1, azienda.getPiva());
        Mockito.verify(mockPreparedStatement).setString(2, azienda.getNome());
        Mockito.verify(mockPreparedStatement).executeUpdate();

        Mockito.verify(mockPreparedStatement).close();
        Mockito.verify(mockConnection).close();
    }

    @Test
    void testDoRetrieveByKey() throws SQLException {
        Azienda azienda = new Azienda("123456789", "Azienda Test");

        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);

        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);

        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getString("piva")).thenReturn(azienda.getPiva());
        Mockito.when(mockResultSet.getString("nome")).thenReturn(azienda.getNome());

        Azienda resultAzienda = aziendaDAO.doRetrieveByKey(azienda.getPiva());

        Mockito.verify(mockPreparedStatement).setString(1, azienda.getPiva());

        assertNotNull(resultAzienda);
        assertEquals(azienda, resultAzienda);

        Mockito.verify(mockResultSet).close();
        Mockito.verify(mockPreparedStatement).close();
        Mockito.verify(mockConnection).close();
    }
}
