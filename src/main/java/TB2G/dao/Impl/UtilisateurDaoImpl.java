package TB2G.dao.Impl;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "insert into utilisateur( email, prenom, nom, datenaissance," +
                    "motdepasse, adresseliv, adressefac, admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,utilisateur.getEmail());
                statement.setString(2, utilisateur.getPrenom());
                statement.setString(3, utilisateur.getNom());
                if (utilisateur.getNaissance() != null) {
                    statement.setDate(4, Date.valueOf(utilisateur.getNaissance()));
                }
                statement.setString(5, utilisateur.getMotdepasse());
                statement.setString(6, utilisateur.getAdresseliv());
                statement.setString(7, utilisateur.getAdressefac());
                statement.setBoolean(8, false);
                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        utilisateur.setId(generatedKeys.getInt(1));
                        return utilisateur;
                    }
                }
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            return new Utilisateur(null,null,null,null,null,null,
                    null,null, null);
        }
        return null;
    }

    private Utilisateur mapUtilisateur(ResultSet resultSetRow) throws SQLException {
        return new Utilisateur(
                resultSetRow.getInt("utilisateur_id"),
                resultSetRow.getString("email"),
                resultSetRow.getString("prenom"),
                resultSetRow.getString("nom"),
                resultSetRow.getDate("datenaissance").toLocalDate(),
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

    @Override
    public Utilisateur getUtilisateurByMail(String mail) {

        String sqlQuery = "SELECT * FROM utilisateur WHERE email =?";
        Utilisateur utilisateur = null;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, mail);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        utilisateur = mapUtilisateur(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            utilisateur = null;
        }
        return utilisateur;
    }

    public void ModificationMdp(Utilisateur utilisateur, String newMdp){

        String SQLQuery = "UPDATE utilisateur SET motdepasse=? WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
                statement.setString(1, newMdp);
                statement.setInt(2, utilisateur.getId());
                statement.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Le mot de passe n'a pas pu être modifié, vérifier que vous avez bien rempli le formulaire");
        }

    }

    @Override
    public void ModificationAdresse(Utilisateur utilisateur, String newAdresse) {
        String SQLQuery = "UPDATE utilisateur SET adresseliv=? WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
                statement.setString(1, newAdresse);
                statement.setInt(2, utilisateur.getId());
                statement.executeUpdate();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("L'adresse n'a pas pu etre modifiee, verifier que vous avez bien rempli le formulaire");
        }
    }

    @Override
    public void ModificationEmail(Utilisateur utilisateur, String newEmail) {
        String SQLQuery = "UPDATE utilisateur SET email=? WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
                statement.setString(1, newEmail);
                statement.setInt(2, utilisateur.getId());
                statement.executeUpdate();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("L'email n'a pas pu etre modifie, verifier que vous avez bien rempli le formulaire");
        }
    }
}
