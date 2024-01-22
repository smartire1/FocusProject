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

	    String query = "INSERT INTO Progetto (nome, descrizione, obbiettivi, stato, scadenza, budget, avvisi, numDipendenti, idAzienda, responsabile_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, progetto.getNome());
	        preparedStatement.setString(2, progetto.getDescrizione());
	        preparedStatement.setString(3, progetto.getObbiettivi());
	        preparedStatement.setBoolean(4, progetto.isStato());
	        preparedStatement.setString(5, progetto.getScadenza());
	        preparedStatement.setDouble(6, progetto.getBudget());
	        preparedStatement.setString(7, progetto.getAvvisi());
	        preparedStatement.setInt(8, progetto.getNumDipendenti());
	        preparedStatement.setString(9, progetto.getIdAzienda());
	        preparedStatement.setString(10, progetto.getResponsabile_email());

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

	    String query = "UPDATE Progetto SET nome = ?, descrizione = ?, obbiettivi = ?, stato = ?, scadenza = ?, budget = ? WHERE id_progetto = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, progetto.getNome());
	        preparedStatement.setString(2, progetto.getDescrizione());
	        preparedStatement.setString(3, progetto.getObbiettivi());
	        preparedStatement.setBoolean(4, progetto.isStato());
	        preparedStatement.setString(5, progetto.getScadenza());
	        preparedStatement.setDouble(6, progetto.getBudget());
	        preparedStatement.setInt(7, progetto.getIdProgetto());
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
	public synchronized Progetto doRetrieveByKey(int idProgetto, String idAzienda) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String query = "SELECT * FROM Progetto WHERE id_progetto = ? AND idAzienda = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, idProgetto);
	        preparedStatement.setString(2, idAzienda);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            if (rs.next()) {
	            	return new Progetto(
	            		    rs.getInt("id_progetto"),
	            		    rs.getString("nome"),
	            		    rs.getString("descrizione"),
	            		    rs.getString("obbiettivi"),
	            		    rs.getString("scadenza"),
	            		    rs.getString("avvisi"),
	            		    rs.getDouble("budget"),
	            		    rs.getInt("numDipendenti"),
	            		    rs.getString("responsabile_email"),
	            		    rs.getBoolean("stato"),
	            		    rs.getString("idAzienda")
	            		);
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
	public Collection<Progetto> doRetrieveAll(String idAzienda) throws SQLException {

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    Collection<Progetto> progetti = new LinkedList<>();

	    String selectSQL = "SELECT * FROM Progetto WHERE idAzienda = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, idAzienda);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Progetto progetto = new Progetto(
	                    rs.getInt("id_progetto"),
	                    rs.getString("nome"),
	                    rs.getString("descrizione"),
	                    rs.getString("obbiettivi"),
	                    rs.getString("scadenza"),
	                    rs.getString("avvisi"),
	                    rs.getDouble("budget"),
	                    rs.getInt("numDipendenti"),
	                    rs.getString("responsabile_email"),
	                    rs.getBoolean("stato"),
	                    rs.getString("idAzienda")
	            );
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
	
	// Metodo per recuperare tutti gli Progetti completati dal database
	public Collection<Progetto> doRetrieveAllFinished(String idAzienda) throws SQLException {
	    Collection<Progetto> progetti = new LinkedList<>();
	    String selectSQL = "SELECT * FROM Progetto WHERE stato = true AND idAzienda = ?";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

	        preparedStatement.setString(1, idAzienda);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                Progetto progetto = new Progetto(
	                        rs.getInt("id_progetto"),
	                        rs.getString("nome"),
	                        rs.getString("descrizione"),
	                        rs.getString("obbiettivi"),
	                        rs.getString("scadenza"),
	                        rs.getString("avvisi"),
	                        rs.getDouble("budget"),
	                        rs.getInt("numDipendenti"),
	                        rs.getString("responsabile_email"),
	                        rs.getBoolean("stato"),
	                        rs.getString("idAzienda")
	                );
	                progetti.add(progetto);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return progetti;
	}
		
	// Metodo per recuperare tutti gli Progetti attivi dal database
	public Collection<Progetto> doRetrieveAllCurrent(String idAzienda) throws SQLException {
	    Collection<Progetto> progetti = new LinkedList<>();
	    String selectSQL = "SELECT * FROM Progetto WHERE stato = false AND idAzienda = ?";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

	        preparedStatement.setString(1, idAzienda);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                Progetto progetto = new Progetto(
	                        rs.getInt("id_progetto"),
	                        rs.getString("nome"),
	                        rs.getString("descrizione"),
	                        rs.getString("obbiettivi"),
	                        rs.getString("scadenza"),
	                        rs.getString("avvisi"),
	                        rs.getDouble("budget"),
	                        rs.getInt("numDipendenti"),
	                        rs.getString("responsabile_email"),
	                        rs.getBoolean("stato"),
	                        rs.getString("idAzienda")
	                );
	                progetti.add(progetto);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return progetti;
	}
	
	// Metodo per recuperare tutti i Progetti associati a un responsabile dal database
	public Collection<Progetto> doRetrieveAllByResponsabile(String responsabileEmail, String idAzienda) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Progetto> progetti = new LinkedList<>();

	    String selectSQL = "SELECT * FROM Progetto WHERE responsabile_email = ? AND idAzienda = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, responsabileEmail);
	        preparedStatement.setString(2, idAzienda);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	            	Progetto progetto = new Progetto(
	            	        rs.getInt("id_progetto"),
	            	        rs.getString("nome"),
	            	        rs.getString("descrizione"),
	            	        rs.getString("obbiettivi"),
	            	        rs.getString("scadenza"),
	            	        rs.getString("avvisi"),
	            	        rs.getDouble("budget"),
	            	        rs.getInt("numDipendenti"),
	            	        rs.getString("responsabile_email"),
	            	        rs.getBoolean("stato"),
	            	        rs.getString("idAzienda")
	            	);
	            	
	                progetti.add(progetto);
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

	    return progetti;
	}
	
}
