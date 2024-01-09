package progetto.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;



public class ProgettoDAO {


	private DataSource ds = null;

	public ProgettoDAO(DataSource ds) {
		this.ds = ds;
	}

	// Metodo per salvare un Progetto nel database
	public synchronized void doSave(Progetto progetto) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO Progetto  (nome, descrizione, obbiettivi, "
				+ "stato, scadenza, budget, avvisi, numDipendenti, piva) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, progetto.getNome());
			preparedStatement.setString(2, progetto.getDescrizione());
			preparedStatement.setString(3, progetto.getObbiettivi());
			preparedStatement.setBoolean(4, progetto.getStato());
			preparedStatement.setString(5, progetto.getScadenza());
			preparedStatement.setDouble(6, progetto.getBudget());
			preparedStatement.setString(7, progetto.getAvvisi());
			preparedStatement.setInt(8, progetto.getNumDipendenti());
			preparedStatement.setString(9, progetto.getPiva());
			
			
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

	// Metodo per eliminare un Progetto nel database
	public synchronized void doDelete(int idProgetto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "DELETE FROM Progetto WHERE id_progetto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idProgetto);
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

	// Metodo per aggiornare un Progetto nel database
	public synchronized void doUpdate(Progetto progetto) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String query = "UPDATE Progetto SET nome = ?, descrizione = ? obbiettivi = ?, stato = ?, scadenza = ?, budget = ?, avvisi = ?, numDipendenti";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, progetto.getNome());
			preparedStatement.setString(2, progetto.getDescrizione());
			preparedStatement.setString(3, progetto.getObbiettivi());
			preparedStatement.setBoolean(4, progetto.getStato());
			preparedStatement.setString(5, progetto.getScadenza());
			preparedStatement.setDouble(6, progetto.getBudget());
			preparedStatement.setString(7, progetto.getAvvisi());
			preparedStatement.setInt(8, progetto.getNumDipendenti());
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

	// Metodo per recuperare un Progetto dal database tramite la chiave primaria
	public synchronized Progetto doRetrieveByKey(int idProgetto, String piva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "SELECT * FROM Progetto WHERE id_progetto = ? AND piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idProgetto);
			preparedStatement.setString(2, piva);
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					return new Progetto(rs.getInt("id_progetto"), rs.getString("nome"), rs.getString("descrizione"), rs.getString("obbiettivi"),
							rs.getBoolean("stato"), rs.getString("scadenza") , rs.getDouble("budget"),  rs.getString("avvisi"),
							rs.getInt("numDipendenti"), rs.getString("piva"));
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
	

	// Metodo per recuperare tutti gli Progetti dal database
	public Collection<Progetto> doRetrieveAll(String piva) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Progetto> progetti = new LinkedList<>();
		String selectSQL = "SELECT * FROM Progetto WHERE piva = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, piva);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Progetto progetto = new Progetto(rs.getInt("id_progetto"), rs.getString("nome"), rs.getString("descrizione"), rs.getString("obbiettivi"),
						rs.getBoolean("stato"), rs.getString("scadenza") , rs.getDouble("budget"),  rs.getString("avvisi"),
						rs.getInt("numDipendenti"), rs.getString("piva"));
				progetti.add(progetto);
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

		return progetti;
	}
	
	// Metodo per recuperare tutti gli Progetti dal database
		public Collection<Progetto> doRetrieveAllFinished(String piva) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Progetto> progetti = new LinkedList<>();
			String selectSQL = "SELECT * FROM Progetto WHERE stato = true AND piva = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, piva);

				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Progetto progetto = new Progetto(rs.getInt("id_progetto"), rs.getString("nome"), rs.getString("descrizione"), rs.getString("obbiettivi"),
							rs.getBoolean("stato"), rs.getString("scadenza") , rs.getDouble("budget"),  rs.getString("avvisi"),
							rs.getInt("numDipendenti"), rs.getString("piva"));
					progetti.add(progetto);
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

			return progetti;
		}
		
		// Metodo per recuperare tutti gli Progetti dal database
			public Collection<Progetto> doRetrieveAllCurrent(String piva) throws SQLException {

				Connection connection = null;
				PreparedStatement preparedStatement = null;

				Collection<Progetto> progetti = new LinkedList<>();
				String selectSQL = "SELECT * FROM Progetto WHERE stato = false AND piva = ?";

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					preparedStatement.setString(1, piva);

					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Progetto progetto = new Progetto(rs.getInt("id_progetto"), rs.getString("nome"), rs.getString("descrizione"), rs.getString("obbiettivi"),
								rs.getBoolean("stato"), rs.getString("scadenza") , rs.getDouble("budget"),  rs.getString("avvisi"),
								rs.getInt("numDipendenti"), rs.getString("piva"));
						progetti.add(progetto);
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

				return progetti;
			}
	
}
