package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ResponsabileDAO {

	private DataSource ds = null;

	public ResponsabileDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Responsabile nel database
	public synchronized void doSave(Responsabile responsabile) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Responsabile (email) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, responsabile.getEmail());
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

	// Metodo per eliminare un Responsabile dal database
	public synchronized void doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Responsabile WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
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

	// Metodo per aggiornare un Responsabile nel database
	public synchronized void doUpdate(Responsabile responsabile) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "UPDATE Responsabile SET email = ? WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, responsabile.getEmail());
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

	// Metodo per recuperare un Responsabile dal database tramite la chiave primaria
	public synchronized Responsabile doRetrieveByKey(String email, String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT R.* " + "FROM Responsabile R " + "JOIN Utente U ON R.email = U.email "
				+ "WHERE U.email = ? AND U.piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, piva);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Responsabile(rs.getString("email"));
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

	// Metodo per recuperare tutti i Responsabili dal database
	public Collection<Responsabile> doRetrieveAll(String piva) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Responsabile> responsabili = new LinkedList<>();

		String selectSQL = "SELECT R.* " + "FROM Responsabile R " + "JOIN Utente U ON R.email = U.email "
				+ "WHERE U.piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Responsabile responsabile = new Responsabile(rs.getString("email"));
				responsabili.add(responsabile);
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

		return responsabili;
	}

}
