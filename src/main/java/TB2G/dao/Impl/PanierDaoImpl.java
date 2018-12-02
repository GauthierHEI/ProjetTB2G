package TB2G.dao.Impl;

import TB2G.dao.PanierDao;
import TB2G.entities.Panier;
import TB2G.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class PanierDaoImpl implements PanierDao {


    @Override
    public Panier addP2P(Panier produit) {
        String sqlQuery = "INSERT INTO panier(utilisateur_id, produit_id, taille, quantite, vendu) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, produit.getIdUtil());
                statement.setInt(2, produit.getProduit().getId());
                statement.setString(3,produit.getTaille());
                statement.setInt(4, produit.getQuantite());
                statement.setBoolean(5, false);
                statement.executeUpdate();


                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        produit.setIdCo(generatedKeys.getInt(1));
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

    private Panier mapProdPanier(ResultSet resultSetRow) throws SQLException {
        Produit produit = new Produit (
                resultSetRow.getInt("produit_id"),
                resultSetRow.getString("produit"),
                resultSetRow.getFloat("prix"),
                resultSetRow.getString("image")
        );
        return new Panier(
                resultSetRow.getInt("element_id"),
                resultSetRow.getInt("utilisateur_id"),
                produit,
                resultSetRow.getString("taille"),
                resultSetRow.getInt("quantite"),
                resultSetRow.getBoolean("vendu")
        );
    }

    @Override
    public List<Panier> listPanier(Integer id) {

        List<Panier> listOfPanier = new ArrayList<>();
        try {
            Connection connection = getDataSource().getConnection();

            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM panier JOIN produit ON panier.produit_id = produit.produit_id WHERE utilisateur_id=? AND vendu=false ORDER BY element_id")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        listOfPanier.add(mapProdPanier(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPanier;
    }

    @Override
    public void AcheterPanier(Integer id){
        String sqlQuery = "UPDATE panier SET vendu = 1 WHERE utilisateur_id = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
