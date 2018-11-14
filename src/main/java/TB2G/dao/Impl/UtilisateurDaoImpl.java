package TB2G.dao.Impl;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "insert into utilisateur(id, email, prenom, nom, naissance, \n" +
                    "motdepasse, adresseliv, adressefac, admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, utilisateur.getId());
                statement.setString(2,utilisateur.getEmail());
                statement.setString(3, utilisateur.getPrenom());
                statement.setString(4, utilisateur.getNom());
                statement.setDate(5, Date.valueOf(utilisateur.getNaissance()));
                statement.setString(6, utilisateur.getMotdepasse());
                statement.setString(7, utilisateur.getAdresseliv());
                statement.setString(8, utilisateur.getAdressefac());
                statement.setBoolean(9, false);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }

    private Utilisateur mapUtilisateur(ResultSet resultSetRow) throws SQLException {
        return new Utilisateur(
                resultSetRow.getInt("utilisateur_id"),
                resultSetRow.getString("email"),
                resultSetRow.getString("prenom"),
                resultSetRow.getString("nom"),
                resultSetRow.getDate("naissance").toLocalDate(),
                resultSetRow.getString("motdepasse"),
                resultSetRow.getString("adresseliv"),
                resultSetRow.getString("adressefac"),
                resultSetRow.getBoolean("admin")
        );
    }

    @Override
    public List<Utilisateur> listUtilisateur() {
        String sqlQuery = "SELECT * FROM utilisateur";
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        utilisateurs.add(mapUtilisateur(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
}
