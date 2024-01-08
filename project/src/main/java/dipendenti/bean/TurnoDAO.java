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
	public synchronized void doSave(Turno turno) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Turno (giorno, ora_inizio, ora_fine, assegnato_a_email) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, turno.getGiorno());
			preparedStatement.setString(2, turno.getOraInizio());
			preparedStatement.setString(3, turno.getOraFine());
			preparedStatement.setString(4, turno.getAssegnatoAEmail());
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
	public synchronized Turno doRetrieveByKey(int id, String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT T.* " + "FROM Turno T " + "JOIN Utente U ON T.assegnato_a_email = U.email "
				+ "WHERE T.id = ? AND U.piva = ?;";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, piva);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
							rs.getString("ora_fine"), rs.getString("assegnato_a_email"));
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

		String selectSQL = "SELECT T.* " + "FROM Turno T " + "JOIN Utente U ON T.assegnato_a_email = U.email "
				+ "WHERE U.piva = ?;";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Turno turno = new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
						rs.getString("ora_fine"), rs.getString("assegnato_a_email"));
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

		String selectSQL = "SELECT T.* " + "FROM Turno T " + "JOIN Utente U ON T.assegnato_a_email = U.email "
				+ "WHERE U.piva = ? AND T.assegnato_a_email = ?;";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);
			preparedStatement.setString(2, email);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Turno turno = new Turno(rs.getInt("id"), rs.getString("giorno"), rs.getString("ora_inizio"),
						rs.getString("ora_fine"), rs.getString("assegnato_a_email"));
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
