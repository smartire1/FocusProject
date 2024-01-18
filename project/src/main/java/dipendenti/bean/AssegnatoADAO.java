package dipendenti.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AssegnatoADAO {
	private DataSource ds = null;

	public AssegnatoADAO(DataSource ds) {
		this.ds = ds;
	}
	
	public synchronized void doSave(AssegnatoA assegnato) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO AssegnatoA (id_turno, id_utente) VALUES (?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, assegnato.getId_turno());
			preparedStatement.setString(2, assegnato.getId_utente());
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

	public synchronized void doDelete(String email) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM AssegnatoA WHERE id_utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
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
}
