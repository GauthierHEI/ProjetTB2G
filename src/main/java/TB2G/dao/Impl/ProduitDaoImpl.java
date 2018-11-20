package TB2G.dao.Impl;

import TB2G.dao.ProduitDao;
import TB2G.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static TB2G.dao.Impl.DataSourceProvider.getDataSource;
import static jdk.nashorn.internal.runtime.GlobalFunctions.parseFloat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProduitDaoImpl implements ProduitDao {

    @Override
    public Produit addProduit(Produit produit) {
        String sqlQuery = "INSERT INTO produit(produit, dispoS, dispoM, dispoL, prix, cat, couleur,hexcouleur) VALUES(?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, produit.getNameproduit());
                statement.setInt(2, produit.getDispoS());
                statement.setInt(3, produit.getDispoM());
                statement.setInt(4, produit.getDispoL());
                statement.setFloat(5, produit.getPrix());
                statement.setInt(6, produit.getCat());
                statement.setString(7, produit.getCouleur());
                statement.setString(8, produit.getHexcouleur());
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
                        produits.add(mapProduit(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        tshirt.add(mapTshirt(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        pull.add(mapPull(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        chemise.add(mapChemise(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chemise;
    }

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
            throw new IllegalArgumentException("Le formulaire n'est pas bien rempli");
        }
    }
}

