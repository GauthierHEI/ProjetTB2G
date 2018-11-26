package TB2G.dao.Impl;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class UtilisateurDaoImpl implements UtilisateurDao {

    static final Logger LOG = LoggerFactory.getLogger(UtilisateurDaoImpl.class);

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "insert into utilisateur( email, prenom, nom, datenaissance," +
                    "motdepasse, adresseliv, adressefac, admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, utilisateur.getEmail());
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
                LOG.info("Un nouvel utilisateur est ajouté dans la base de données : " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " " + utilisateur.getEmail());

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        utilisateur.setId(generatedKeys.getInt(1));
                        return utilisateur;
                    }
                }
            }
        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            LOG.error("exception SQL");
            return new Utilisateur(null, null, null, null, null, null, null, null, null);
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
        String sqlQuery = "SELECT * FROM utilisateur ";
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        utilisateurs.add(mapUtilisateur(resultSet));
                        LOG.info("ajout de la liste des utilisateurs dans la variable utilisateurs ");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
        LOG.info("return utilisateurs");
        return utilisateurs;


    }

    @Override
    public Utilisateur getUtilisateurByMail(String mail) {

        String sqlQuery = "SELECT * FROM utilisateur WHERE email =?";
        Utilisateur utilisateur = null;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                LOG.info("ajout du mail dans le statement");
                statement.setString(1, mail);
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        LOG.info("ajout de l'utilisateur concerné dans la variable utilisateur");
                        utilisateur = mapUtilisateur(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            utilisateur = null;
            LOG.error("exception : l'email n'est pas dans la base de données -> utilisateur = null");
        }
        LOG.info("return utilisateur");
        return utilisateur;
    }

    @Override
    public void editAdmin(Integer utilisateur_id, boolean role) {
        String sqlQuerry = "UPDATE utilisateur SET admin=? WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuerry)) {
                statement.setBoolean(1, role);
                statement.setInt(2, utilisateur_id);
                statement.executeUpdate();
                LOG.info("le rôle de l'utilisateur est modifié");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("le role n'a pas été modifié");
        }
    }


    @Override
    public void ModificationMdp(Utilisateur utilisateur, String newMdp) {

        String SQLQuery = "UPDATE utilisateur SET motdepasse=? WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
                statement.setString(1, newMdp);
                statement.setInt(2, utilisateur.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Le mot de passe n'a pas pu être modifié, vérifier que vous avez bien rempli le formulaire");
        }

    }

    @Override
    public void deleteUtilisateur(Integer utilisateur_id) {

        String SQLQuerry = "DELETE FROM utilisateur WHERE utilisateur_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQuerry)) {
                statement.setInt(1,utilisateur_id);
                statement.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
    }
}

