package TB2G.dao.Impl;

import TB2G.dao.ProduitDao;
import TB2G.entities.Produit;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static TB2G.dao.Impl.DataSourceProvider.getDataSource;
import static TB2G.dao.Impl.UtilisateurDaoImpl.LOG;
import static jdk.nashorn.internal.runtime.GlobalFunctions.parseFloat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProduitDaoImpl implements ProduitDao {

    @Override
    public Produit addProduit(Produit produit) {
        String sqlQuery = "INSERT INTO produit(produit, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, produit.getNameproduit());
                statement.setInt(2, produit.getDispoS());
                statement.setInt(3, produit.getDispoM());
                statement.setInt(4, produit.getDispoL());
                statement.setFloat(5, produit.getPrix());
                statement.setInt(6, produit.getCat());
                statement.setString(7, produit.getCouleur());
                statement.setString(8,produit.getImage());
                statement.setString(9,produit.getHexcouleur());
                statement.executeUpdate();


                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        produit.setId(generatedKeys.getInt(1));
                        return produit;
                    }
                }
            }

        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            LOG.error("exception SQL");
            return null;
        }
        return null;
    }

    @Override
    public Produit modifProduit(Produit produit) {
        String sqlQuery = "UPDATE produit SET produit=? ,dispoS =? , dispoM=? , dispoL=? , prix=? , cat=? , couleur=? ,hexcouleur=? " +
                "WHERE produit_id=? ";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, produit.getNameproduit());
                statement.setInt(2, produit.getDispoS());
                statement.setInt(3, produit.getDispoM());
                statement.setInt(4, produit.getDispoL());
                statement.setFloat(5, produit.getPrix());
                statement.setInt(6, produit.getCat());
                statement.setString(7, produit.getCouleur());
                statement.setString(8, produit.getHexcouleur());
                statement.setInt(9, produit.getId());
                statement.executeUpdate();

                return produit;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer deleteProduit(Integer id) {

        Integer idProduitSupprime;
        String sqlQuery = "DELETE FROM produit WHERE produit_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idProduitSupprime = generatedKeys.getInt(1);
                        return idProduitSupprime;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
            return null;
        }
        return null;
    }

    private Produit mapProduit(ResultSet resultSetRow) throws SQLException {
        return new Produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image"),
                resultSetRow.getString("hexcouleur")
        );
    }

    @Override
    public List<Produit> listProduit() {
        String sqlQuery = "SELECT * FROM produit";
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        LOG.info("ajout de la liste de produits dans la variable produits");
                        produits.add(mapProduit(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
        LOG.info("retourne produits");
        return produits;
    }


    private Produit mapTshirt(ResultSet resultSetRow) throws SQLException {
        return new Produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image"),
                resultSetRow.getString("hexcouleur")
        );
    }

    @Override
    public List<Produit> listTshirt() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =1";
        List<Produit> tshirt = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        LOG.info("ajout de la liste de Tshirts dans la variable tshirt");
                        tshirt.add(mapTshirt(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
        LOG.info("retourne tshirt");
        return tshirt;
    }

    private Produit mapPull(ResultSet resultSetRow) throws SQLException {
        return new Produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image"),
                resultSetRow.getString("hexcouleur")
        );
    }

    @Override
    public List<Produit> listPull() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =2";
        List<Produit> pull = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        LOG.info("ajout de la liste de pull dans la variable pull");
                        pull.add(mapPull(resultSet));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
        LOG.info("retourne pull");
        return pull;
    }

    private Produit mapChemise(ResultSet resultSetRow) throws SQLException {
        return new Produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image"),
                resultSetRow.getString("hexcouleur")
        );
    }

    @Override
    public List<Produit> listChemise() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =3";
        List<Produit> chemise = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        LOG.info("ajout de la liste de chemises dans la variable chemise");
                        chemise.add(mapChemise(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("exception SQL");
        }
        LOG.info("retourne chemise");
        return chemise;
    }

    @Override
    public Produit getProduitById(Integer produitId) {

        String sqlQuery = "SELECT * FROM produit WHERE produit_id =?";
        Produit produit = null;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, String.valueOf(produitId));
                System.out.println(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        produit = mapProduit(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            produit = null;
        }
        return produit;
    }


}

