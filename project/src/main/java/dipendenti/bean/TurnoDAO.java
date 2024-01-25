package dipendenti.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class TurnoDAO {

	private DataSource ds = null;

	public TurnoDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Turno nel database
	public synchronized Turno doSave(Turno turno) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet generatedKeys = null;

	    String query = "INSERT INTO Turno (giorno, ora_inizio, ora_fine) VALUES (?, ?, ?)";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

	        preparedStatement.setString(1, turno.getGiorno());
	        preparedStatement.setString(2, turno.getOraInizio());
	        preparedStatement.setString(3, turno.getOraFine());

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Inserimento del turno non riuscito, nessuna riga modificata.");
	        }

	        generatedKeys = preparedStatement.getGeneratedKeys();

	        if (generatedKeys.next()) {
	            int generatedId = generatedKeys.getInt(1);
	            turno.setId(generatedId); // Imposta l'ID generato nel tuo oggetto Turno
	        } else {
	            throw new SQLException("Inserimento del turno non riuscito, nessun ID generato.");
	        }
	    } finally {
	        try {
	            if (generatedKeys != null) {
	                generatedKeys.close();
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
	    }

	    return turno; // Restituisci il turno con l'ID aggiornato
	}

	// Metodo per eliminare un Turno dal database
	public synchronized void doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Turno WHERE id = ?";

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

	// Metodo per recuperare un Turno dal database tramite la chiave primaria e per un'azienda specifica dal database
	public synchronized Turno doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Turno WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
							rs.getString("ora_fine"));
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

	// Metodo per recuperare tutti i Turni per un'azienda specifica dal database
	public Collection<Turno> doRetrieveAll(String piva) throws SQLException {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Turno> turni = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	            "FROM Turno T " +
	            "JOIN AssegnatoA A ON T.id = A.id_turno " +
	            "JOIN Utente U ON A.id_utente = U.email " +
	            "WHERE U.idAzienda = (SELECT id FROM Azienda WHERE piva = ?);";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, piva);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Turno turno = new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
	                    rs.getString("ora_fine"));
	            turni.add(turno);
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

	    return turni;
	}


	// Metodo per recuperare tutti i Turni associati a un dipendente per un'azienda specifica dal database
	public Collection<Turno> doRetrieveAllByUser(String piva, String email) throws SQLException {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Turno> turni = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	            "FROM Turno T " +
	            "JOIN AssegnatoA A ON T.id = A.id_turno " +
	            "JOIN Utente U ON A.id_utente = U.email " +
	            "WHERE U.idAzienda = ? AND A.id_utente = ?;";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, piva);
	        preparedStatement.setString(2, email);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Turno turno = new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
	                    rs.getString("ora_fine"));
	            turni.add(turno);
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

	    return turni;
	}
	
	// Metodo per recuperare tutti i Turni dei responsabili per un'azienda specifica dal database
	public Collection<Turno> doRetrieveAllResp(String piva) throws SQLException {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Turno> turni = new LinkedList<>();

	    String selectSQL = "SELECT T.* " +
	            "FROM Turno T " +
	            "JOIN AssegnatoA A ON T.id = A.id_turno " +
	            "JOIN Utente U ON A.id_utente = U.email " +
	            "WHERE U.idAzienda = ? AND U.ruolo = 'responsabile'";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, piva);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            Turno turno = new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
	                    rs.getString("ora_fine"));
	            turni.add(turno);
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

	    return turni;
	}

}
