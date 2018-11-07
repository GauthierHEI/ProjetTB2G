package TB2G.dao.Impl;

import TB2G.dao.ProduitDao;
import TB2G.entities.produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class ProduitDaoImpl implements ProduitDao {


    @Override
    public produit addProduit(produit produit) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "insert into produit(nameproduit, dispoS, dispoM, dispoL, \n" +
                    "prix, cat, couleur, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, produit.getNameproduit());
                statement.setInt(2, produit.getDispoS());
                statement.setInt(3, produit.getDispoM());
                statement.setInt(4, produit.getDispoL());
                statement.setFloat(5, produit.getPrix());
                statement.setInt(6, produit.getCat());
                statement.setString(7, produit.getCouleur());
                statement.setString(8, produit.getImage());
            }
        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }

    private produit mapProduit(ResultSet resultSetRow) throws SQLException {
        return new produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image")
        );
    }

    @Override
    public List<produit> listProduit() {
        String sqlQuery = "SELECT * FROM produit";
        List<produit> produits = new ArrayList<>();
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


    private produit mapTshirt(ResultSet resultSetRow) throws SQLException {
        return new produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur"),
                resultSetRow.getString("image")
        );
    }

    @Override
    public List<produit> listTshirt() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =1";
        List<produit> tshirt = new ArrayList<>();
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

    private produit mapPull(ResultSet resultSetRow) throws SQLException {
        return new produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur")
        );
    }

    @Override
    public List<produit> listPull() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =2";
        List<produit> pull = new ArrayList<>();
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

    private produit mapChemise(ResultSet resultSetRow) throws SQLException {
        return new produit(
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getInt("dispoS"),
                resultSetRow.getInt("dispoM"),
                resultSetRow.getInt("dispoL"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getInt("cat"),
                resultSetRow.getString("couleur")
        );
    }

    @Override
    public List<produit> listChemise() {
        String sqlQuery = "SELECT * FROM produit WHERE cat =3";
        List<produit> chemise = new ArrayList<>();
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


}

