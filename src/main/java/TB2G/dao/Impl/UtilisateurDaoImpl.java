package TB2G.dao.Impl;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.utilisateur;

import java.sql.*;


import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public utilisateur addUtilisateur(utilisateur utilisateur) {
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
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }
}
