package TB2G.dao.Impl;

import TB2G.dao.PanierDao;
import TB2G.entities.Panier;

import java.sql.*;
import java.util.List;

public class PanierDaoImpl implements PanierDao {


    @Override
    public Panier addP2P(Panier produit) {
        String sqlQuery = "INSERT INTO panier(produit_id, nameproduit, taille, quantite, prixUni, prixTotal, vendu) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, produit.getId());
                statement.setString(2, produit.getNameproduit());
                statement.setString(3,produit.getTaille());
                statement.setInt(4, produit.getQuantite());
                statement.setFloat(5, produit.getPrixUni());
                Float prixT = produit.getQuantite()*produit.getPrixUni();
                statement.setFloat(6, prixT);
                statement.setBoolean(7, false);
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

    @Override
    public List<Panier> listPanier() {
        return null;
    }
}
