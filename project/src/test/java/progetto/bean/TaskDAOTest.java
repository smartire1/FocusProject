package progetto.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskDAOTest {

    private DataSource dataSource;
    private TaskDAO taskDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        taskDAO = new TaskDAO(dataSource);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        // Configura il comportamento del mock per restituire la connessione
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

    }

    @Test
    void testDoSave() throws SQLException {
        // Arrange
        Task task = new Task(1, "descrizione", true, 3, "subordinatomail@mail.it");

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        taskDAO.doSave(task);

        // Assert
        // Verifica che il metodo executeUpdate sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, task.getIdProgetto());
        verify(preparedStatement).setString(2, task.getDescrizione());
        verify(preparedStatement).setBoolean(3, task.isStato());
        verify(preparedStatement).setString(4, task.getSubordinatoEmail());
        verify(preparedStatement).executeUpdate();
    }
    
    
    @Test
    void testDoDelete() throws SQLException {
        // Arrange
        int taskIdToDelete = 1;

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        taskDAO.doDelete(taskIdToDelete);

        // Assert
        // Verifica che il metodo executeUpdate sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, taskIdToDelete);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDoUpdate() throws SQLException {
        // Arrange
        Task updatedTask = new Task(2, "descrizione", true, 3, "subordinatomail@mail.it");

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        taskDAO.doUpdate(updatedTask);

        // Assert
        // Verifica che il metodo executeUpdate sia stato chiamato correttamente
        verify(preparedStatement).setString(1, updatedTask.getDescrizione());
        verify(preparedStatement).setBoolean(2, updatedTask.isStato());
        verify(preparedStatement).setString(3, updatedTask.getSubordinatoEmail());
        verify(preparedStatement).setInt(4, updatedTask.getIdTask());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDoRetrieveByKey() throws SQLException {
        // Arrange
        int taskIdToRetrieve = 1;
        String aziendaId = "example_company";

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_task")).thenReturn(taskIdToRetrieve);

        // Act
        Task retrievedTask = taskDAO.doRetrieveByKey(taskIdToRetrieve, aziendaId);

        // Assert
        // Verifica che il metodo executeQuery sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, taskIdToRetrieve);
        verify(preparedStatement).setString(2, aziendaId);

        // Verifica che il risultato restituito sia non nullo
        assertNotNull(retrievedTask);

        // Verifica che l'ID del Task restituito sia corretto
        assertEquals(taskIdToRetrieve, retrievedTask.getIdTask());
    }

    @Test
    void testDoRetrieveAllByProject() throws SQLException {
        // Arrange
        int projectId = 1;
        String aziendaId = "example_company";

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id_task")).thenReturn(1);

        // Act
        Collection<Task> tasks = taskDAO.doRetrieveAllByProject(projectId, aziendaId);

        // Assert
        // Verifica che il metodo executeQuery sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, projectId);
        verify(preparedStatement).setString(2, aziendaId);

        // Verifica che la collezione di Task restituita non sia vuota
        assertFalse(tasks.isEmpty());

        // Verifica che il numero di Task nella collezione sia corretto
        assertEquals(1, tasks.size());
    }

    @Test
    void testDoRetrieveAllByProjectAndUser() throws SQLException {
        // Arrange
        int projectId = 1;
        String userEmail = "test@example.com";

        // Configura il comportamento del mock per restituire un risultato positivo
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id_task")).thenReturn(1);

        // Act
        Collection<Task> tasks = taskDAO.doRetrieveAllByProjectAndUser(projectId, userEmail);

        // Assert
        // Verifica che il metodo executeQuery sia stato chiamato correttamente
        verify(preparedStatement).setInt(1, projectId);
        verify(preparedStatement).setString(2, userEmail);

        // Verifica che la collezione di Task restituita non sia vuota
        assertFalse(tasks.isEmpty());

        // Verifica che il numero di Task nella collezione sia corretto
        assertEquals(1, tasks.size());
    }
}
