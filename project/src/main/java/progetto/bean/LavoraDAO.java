package progetto.bean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class LavoraDAO {

    private final DataSource dataSource;

    public LavoraDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Metodo per salvare le statistiche nel database
    public synchronized void doSave(Lavora lavora) throws SQLException {
        String query = "INSERT INTO Lavora (email, id_progetto) VALUES (?, ?)";
        
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);       	
        ) {
    		preparedStatement.setString(1, lavora.getEmail()); 
    		preparedStatement.setInt(2, lavora.getId_progetto());
            preparedStatement.executeUpdate();
        }
    }

    // Metodo per aggiornare le statistiche nel database
    public synchronized void doDelete(Lavora lavora) throws SQLException {
        String query = "DELETE FROM Lavora WHERE email = ? AND id_progetto = ?";
        
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);       	
        ) {
    		preparedStatement.setString(1, lavora.getEmail()); 
    		preparedStatement.setInt(2, lavora.getId_progetto());
            preparedStatement.executeUpdate();
        }
    }
    
    public synchronized Collection<Lavora> doRetriveByProject(int id_project) throws SQLException {
        String query = "SELECT * FROM Lavora WHERE id_progetto = ?";
        Collection<Lavora> subordinati = new ArrayList<>();
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id_project);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Recupera i dati dal ResultSet e crea un oggetto Lavora
                    Lavora lavora = new Lavora(resultSet.getString("email"), resultSet.getInt("id_progetto"));
                    // Aggiungi l'oggetto alla collezione
                    subordinati.add(lavora);
                }
            }
        }
        return subordinati;
    }
 
}
