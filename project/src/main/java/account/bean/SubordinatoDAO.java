package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class SubordinatoDAO {

	private DataSource ds = null;

	public SubordinatoDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Subordinato nel database
	public synchronized void doSave(Subordinato subordinato) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Subordinato (email) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, subordinato.getEmail());
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

	// Metodo per eliminare un Subordinato nel database
	public synchronized void doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Subordinato WHERE email = ?";

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

	// Metodo per aggiornare un Subordinato nel database
	public synchronized void doUpdate(Subordinato subordinato) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "UPDATE Subordinato SET email = ? WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, subordinato.getEmail());
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

	// Metodo per recuperare un Subordinato dal database tramite la chiave primaria
	public synchronized Subordinato doRetrieveByKey(String email, String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT S.* " +
                "FROM Subordinato S " +
                "JOIN Utente U ON S.email = U.email " +
                "WHERE U.email = ? AND U.piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, piva);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Subordinato(rs.getString("email"));
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

	// Metodo per recuperare tutti i Subordinati dal database
	public Collection<Subordinato> doRetrieveAll(String piva) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Subordinato> subordinati = new LinkedList<>();
		
		String selectSQL = "SELECT S.* " +
                "FROM Subordinato S " +
                "JOIN Utente U ON S.email = U.email " +
                "WHERE U.piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subordinato subordinato = new Subordinato(rs.getString("email"));
				subordinati.add(subordinato);
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

		return subordinati;
	}
}