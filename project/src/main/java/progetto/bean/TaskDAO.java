package progetto.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class TaskDAO {

	private DataSource ds = null;

	public TaskDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Task nel database
	public synchronized void doSave(Task task) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Task (id_progetto, descrizione, stato, assegnato_a_email) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, task.getIdProgetto());
			preparedStatement.setString(2, task.getDescrizione());
			preparedStatement.setBoolean(3, task.isStato());
			preparedStatement.setString(4, task.getAssegnatoAEmail());
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
	}
	
	// Metodo per eliminare un Task dal database
	public synchronized void doDelete(int id) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String query = "DELETE FROM Task WHERE id_task = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        preparedStatement.executeUpdate();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }
	}
	
	// Metodo per aggiornare un Task nel database
	public synchronized void doUpdate(Task task) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String updateSQL = "UPDATE Task SET descrizione = ?, stato = ?, assegnato_a_email = ? WHERE id_task = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setString(1, task.getDescrizione());
	        preparedStatement.setBoolean(2, task.isStato());
	        preparedStatement.setString(3, task.getAssegnatoAEmail());
	        preparedStatement.setInt(4, task.getIdTask());

	        preparedStatement.executeUpdate();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}
	
	// Metodo per recuperare un Task dal database tramite la chiave primaria e per un'azienda specifica dal database
	public synchronized Task doRetrieveByKey(int id, String piva) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String query = "SELECT T.* " +
	                   "FROM Task T " +
	                   "JOIN Utente U ON T.assegnato_a_email = U.email " +
	                   "WHERE T.id_task = ? AND U.piva = ?;";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        preparedStatement.setString(2, piva);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            if (rs.next()) {
	                return new Task(rs.getInt("id_task"), rs.getInt("id_progetto"), rs.getString("descrizione"),
	                                rs.getBoolean("stato"), rs.getString("assegnato_a_email"));
	            }
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return null;
	}
	
	// Metodo per recuperare tutti i Task per un'azienda specifica dal database
	public Collection<Task> doRetrieveAll(String piva) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Task> tasks = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	                       "FROM Task T " +
	                       "JOIN Utente U ON T.assegnato_a_email = U.email " +
	                       "WHERE U.piva = ?;";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, piva);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Task task = new Task(rs.getInt("id_task"), rs.getInt("id_progetto"), rs.getString("descrizione"),
	                                 rs.getBoolean("stato"), rs.getString("assegnato_a_email"));
	            tasks.add(task);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return tasks;
	}
	
	// Metodo per recuperare tutti i Task associati a un dipendente per un'azienda specifica dal database
	public Collection<Task> doRetrieveAllByUser(String piva, String email) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Task> tasks = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	                       "FROM Task T " +
	                       "JOIN Utente U ON T.assegnato_a_email = U.email " +
	                       "WHERE U.piva = ? AND T.assegnato_a_email = ?;";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, piva);
	        preparedStatement.setString(2, email);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Task task = new Task(rs.getInt("id_task"), rs.getInt("id_progetto"), rs.getString("descrizione"),
	                                 rs.getBoolean("stato"), rs.getString("assegnato_a_email"));
	            tasks.add(task);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return tasks;
	}

	// Metodo per recuperare tutti i Task associati a uno specifico progetto per un'azienda specifica dal database
	public Collection<Task> doRetrieveAllByProject(int idProgetto, String piva) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Task> tasks = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	                       "FROM Task T " +
	                       "JOIN Progetto P ON T.id_progetto = P.id_progetto " +
	                       "JOIN Utente U ON P.piva = U.piva " +
	                       "WHERE T.id_progetto = ? AND U.piva = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, idProgetto);
	        preparedStatement.setString(2, piva);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Task task = new Task(rs.getInt("id_task"), rs.getInt("id_progetto"), rs.getString("descrizione"),
	                                 rs.getBoolean("stato"), rs.getString("assegnato_a_email"));
	            tasks.add(task);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return tasks;
	}

	// Metodo per recuperare tutti i Task associati a uno specifico progetto, utente e azienda dal database
	public Collection<Task> doRetrieveAllByProjectAndUser(int idProgetto, int idUtente, String piva) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Task> tasks = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	                       "FROM Task T " +
	                       "JOIN Progetto P ON T.id_progetto = P.id_progetto " +
	                       "JOIN Utente U ON T.assegnato_a_email = U.email " +
	                       "WHERE T.id_progetto = ? AND U.id_utente = ? AND P.piva = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, idProgetto);
	        preparedStatement.setInt(2, idUtente);
	        preparedStatement.setString(3, piva);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Task task = new Task(rs.getInt("id_task"), rs.getInt("id_progetto"), rs.getString("descrizione"),
	                                 rs.getBoolean("stato"), rs.getString("assegnato_a_email"));
	            tasks.add(task);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return tasks;
	}
}
