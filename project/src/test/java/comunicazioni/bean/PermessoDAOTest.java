package comunicazioni.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class PermessoDAOTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private PermessoDAO permessoDAO;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        permessoDAO = new PermessoDAO(dataSource);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(dataSource.getConnection()).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("dal_giorno")).thenReturn("2022-01-01");
        when(resultSet.getString("al_giorno")).thenReturn("2022-01-10");
        when(resultSet.getString("motivo")).thenReturn("Vacanza");
        when(resultSet.getBoolean("stato")).thenReturn(true);  // Modifica qui
        when(resultSet.getString("richiedente_email")).thenReturn("richiedente@example.com");
    }

    @Test
    public void testDoSave() throws SQLException {
        Permesso permesso = new Permesso(1, "2022-01-01", "2022-01-10", "Vacanza", true, "richiedente@example.com");

        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        permessoDAO.doSave(permesso);

        verify(preparedStatement).setString(1, "2022-01-01");
        verify(preparedStatement).setString(2, "2022-01-10");
        verify(preparedStatement).setString(3, "Vacanza");
        verify(preparedStatement).setObject(4, true, java.sql.Types.BOOLEAN);
        verify(preparedStatement).setString(5, "richiedente@example.com");
        verify(preparedStatement).executeUpdate();
        verify(resultSet).next();
        verify(resultSet).getInt(1);

        assertEquals(1, permesso.getId());
    }


    @Test
    public void testDoUpdate() throws SQLException {
        Permesso permesso = new Permesso(1, "2022-01-01", "2022-01-10", "Vacanza", true, "richiedente@example.com");

        when(preparedStatement.executeUpdate()).thenReturn(1);

        permessoDAO.doUpdate(permesso);

        verify(preparedStatement).setString(1, "2022-01-01");
        verify(preparedStatement).setString(2, "2022-01-10");
        verify(preparedStatement).setString(3, "Vacanza");
        verify(preparedStatement).setObject(4, true, Types.BOOLEAN);
        verify(preparedStatement).setString(5, "richiedente@example.com");
        verify(preparedStatement).setInt(6, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDoRetrieveByKey() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("dal_giorno")).thenReturn("2022-01-01");
        when(resultSet.getString("al_giorno")).thenReturn("2022-01-10");
        when(resultSet.getString("motivo")).thenReturn("Vacanza");
        when(resultSet.getBoolean("stato")).thenReturn(true);
        when(resultSet.getString("richiedente_email")).thenReturn("richiedente@example.com");

        Permesso permesso = permessoDAO.doRetrieveByKey(1, "idAzienda");

        assertNotNull(permesso);
        assertEquals(1, permesso.getId());
        assertEquals("2022-01-01", permesso.getDalGiorno());
        assertEquals("2022-01-10", permesso.getAlGiorno());
        assertEquals("Vacanza", permesso.getMotivo());
        assertTrue(permesso.isStato());
        assertEquals("richiedente@example.com", permesso.getRichiedenteEmail());

        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setString(2, "idAzienda");
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("dal_giorno");
        verify(resultSet).getString("al_giorno");
        verify(resultSet).getString("motivo");
        verify(resultSet).getBoolean("stato");
        verify(resultSet).getString("richiedente_email");
    }

   
    @Test
    public void testDoRetrieveAllManagedAndByRuolo() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("dal_giorno")).thenReturn("2022-01-01");
        when(resultSet.getString("al_giorno")).thenReturn("2022-01-10");
        when(resultSet.getString("motivo")).thenReturn("Vacanza");
        when(resultSet.getBoolean("stato")).thenReturn(true);
        when(resultSet.getString("richiedente_email")).thenReturn("richiedente@example.com");

        List<Permesso> permessi = permessoDAO.doRetrieveAllManagedAndByRuolo("idAzienda", "ruolo");

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(1, permessi.size());
        Permesso permesso = permessi.get(0);
        assertEquals(1, permesso.getId());
        assertEquals("2022-01-01", permesso.getDalGiorno());
        assertEquals("2022-01-10", permesso.getAlGiorno());
        assertEquals("Vacanza", permesso.getMotivo());
        assertTrue(permesso.isStato());
        assertEquals("richiedente@example.com", permesso.getRichiedenteEmail());

        // Altri controlli se necessario
    }

    @Test
    public void testDoRetrieveAllNotManagedAndByRuolo1() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("dal_giorno")).thenReturn("2022-01-01");
        when(resultSet.getString("al_giorno")).thenReturn("2022-01-10");
        when(resultSet.getString("motivo")).thenReturn("Vacanza");
        when(resultSet.getBoolean("stato")).thenReturn(true);
        when(resultSet.getString("richiedente_email")).thenReturn("richiedente@example.com");

        List<Permesso> permessi = permessoDAO.doRetrieveAllNotManagedAndByRuolo("idAzienda", "ruolo");

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(1, permessi.size());
        Permesso permesso = permessi.get(0);
        assertEquals(1, permesso.getId());
        assertEquals("2022-01-01", permesso.getDalGiorno());
        assertEquals("2022-01-10", permesso.getAlGiorno());
        assertEquals("Vacanza", permesso.getMotivo());
        assertTrue(permesso.isStato());
        assertEquals("richiedente@example.com", permesso.getRichiedenteEmail());
    }

}
