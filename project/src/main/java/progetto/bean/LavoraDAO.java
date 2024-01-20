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
        String query = "INSERT INTO LavoraA (email, id_progetto) VALUES (?, ?)";
        
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
        String query = "DELETE FROM LavoraA WHERE email = ? AND id_progetto = ?";
        
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
        String query = "SELECT * FROM LavoraA WHERE id_progetto = ?";
        Collection<Lavora> subordinati = new ArrayList<>();
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id_project);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Lavora lavora = new Lavora(resultSet.getString("email"), resultSet.getInt("id_progetto"));
                    subordinati.add(lavora);
                }
            }
        }
        return subordinati;
    }    
 
    public synchronized Lavora doRetriveByUser(String email) throws SQLException {
        String query = "SELECT * FROM LavoraA WHERE email = ?";
        Lavora lavora = null;

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    lavora = new Lavora(resultSet.getString("email"), resultSet.getInt("id_progetto"));
                }
            }
        }

        return lavora;
    }
    
    public synchronized Collection<Lavora> doRetriveByCompany(String idAzienda) throws SQLException {
        String query = "SELECT * FROM LavoraA "
        		+ "JOIN Progetto p ON p.id_progetto = LavoraA.id_progetto "
        		+ "WHERE p.idAzienda = ?";
        Collection<Lavora> subordinati = new ArrayList<>();
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, idAzienda);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Lavora lavora = new Lavora(resultSet.getString("email"), resultSet.getInt("id_progetto"));
                    subordinati.add(lavora);
                }
            }
        }
        return subordinati;
    }
}
