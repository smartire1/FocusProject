package account.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import progetto.bean.Task;

public class UtenteDAO {

	private DataSource ds = null;

	public UtenteDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Utente nel database
	public synchronized void doSave(Utente utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Utente (email, pwd, nome, cognome, idAzienda, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());			
			preparedStatement.setString(3, utente.getNome());
			preparedStatement.setString(4, utente.getCognome());
			preparedStatement.setString(5, utente.getIdAzienda());
			preparedStatement.setString(6, utente.getRuolo());			
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

		String query = "UPDATE Utente SET stato = false WHERE email = ?";

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

	    String query = "UPDATE Utente SET email = ?, pwd = ? WHERE email = ?";

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
					return new Utente(rs.getString("email"), rs.getString("pwd"), rs.getString("nome"), 
									  rs.getString("cognome"), rs.getString("idAzienda"), 
									  rs.getBoolean("stato"),rs.getString("ruolo"));
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
	public synchronized Utente doRetrieveByKey(String email, String idAzienda) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Utente WHERE email = ? AND idAzienda = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, idAzienda);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Utente(rs.getString("email"), rs.getString("pwd"), rs.getString("nome"), 
							  rs.getString("cognome"), rs.getString("idAzienda"), 
							  rs.getBoolean("stato"),rs.getString("ruolo"));
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
	public Collection<Utente> doRetrieveAll(String idAzienda) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Utente> utenti = new LinkedList<>();
		String selectSQL = "SELECT * FROM Utente WHERE idAzienda = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idAzienda);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente(rs.getString("email"), rs.getString("pwd"), rs.getString("nome"), 
						  				   rs.getString("cognome"), rs.getString("idAzienda"), 
						  				   rs.getBoolean("stato"),rs.getString("ruolo"));
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
	
	// Metodo per recuperare tutti gli Utenti dal database che sono Responsabili
	public Collection<Utente> doRetrieveAllResponsabili(String idAzienda) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Utente> responsabili = new LinkedList<>();
		String selectSQL = "SELECT * FROM Utente u WHERE u.ruolo = 'responsabile' AND u.idAzienda = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idAzienda);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente(rs.getString("email"), rs.getString("pwd"), rs.getString("nome"), 
						  				   rs.getString("cognome"), rs.getString("idAzienda"), 
						  				   rs.getBoolean("stato"),rs.getString("ruolo"));
				responsabili.add(utente);
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
	
	// Metodo per recuperare tutti gli Utenti dal database che sono Subordinati
	public Collection<Utente> doRetrieveAllSubordinati(String idAzienda) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Utente> subordinati = new LinkedList<>();
		String selectSQL = "SELECT * FROM Utente u WHERE u.ruolo = 'subordinato' AND u.idAzienda = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idAzienda);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente(rs.getString("email"), rs.getString("pwd"), rs.getString("nome"), 
						  				   rs.getString("cognome"), rs.getString("idAzienda"), 
						  				   rs.getBoolean("stato"),rs.getString("ruolo"));
				subordinati.add(utente);
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