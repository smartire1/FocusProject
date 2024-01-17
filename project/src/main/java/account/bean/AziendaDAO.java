package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class AziendaDAO {

	private DataSource ds = null;

	public AziendaDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Utente nel database
	public synchronized void doSave(Azienda azienda) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Azienda (piva, nome) VALUES (?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, azienda.getPiva());			
			preparedStatement.setString(2, azienda.getNome());
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

	// Metodo per eliminare un Utente nel database
	public synchronized void doDelete(String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Azienda WHERE piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, piva);
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

	public synchronized Azienda doRetrieveByKey(String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Azienda WHERE piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, piva);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Azienda(rs.getString("piva"), rs.getString("nome"));
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
}