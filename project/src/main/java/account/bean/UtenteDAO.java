package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class UtenteDAO {

	private DataSource ds = null;

	public UtenteDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Utente nel database
	public synchronized void doSave(Utente utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Utente (nome, cognome, email, password, piva) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.setString(5, utente.getPiva());
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
	public synchronized void doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Utente WHERE email = ?";

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

	// Metodo per aggiornare un Utente nel database
	public synchronized void doUpdate(Utente utente, String email, String password) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String query = "UPDATE Utente SET email = ?, password = ? WHERE email = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, password);
	        preparedStatement.setString(3, utente.getEmail());
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

	// Metodo per recuperare un Utente dal database tramite la chiave primaria
	public synchronized Utente doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Utente WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("email"),
							rs.getString("password"), rs.getString("piva"));
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
	
	// Metodo per recuperare un Utente dal database tramite la chiave primaria
	public synchronized Utente doRetrieveByKey(String email, String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Utente WHERE email = ? AND piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, piva);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("email"),
							rs.getString("password"), rs.getString("piva"));
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

	// Metodo per recuperare tutti gli Utenti dal database
	public Collection<Utente> doRetrieveAll(String piva) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Utente> utenti = new LinkedList<>();
		String selectSQL = "SELECT * FROM Utente WHERE piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("email"),
						rs.getString("password"), rs.getString("piva"));
				utenti.add(utente);
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

		return utenti;
	}
}