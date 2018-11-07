package TB2G.dao.Impl;

import TB2G.dao.ProduitDao;
import TB2G.entities.produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static TB2G.dao.Impl.DataSourceProvider.getDataSource;

public class ProduitDaoImpl implements ProduitDao {


    @Override
    public produit addProduit(produit produit) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "insert into produit(id, nameproduit, dispoS, dispoM, dispoL, \n" +
                    "prix, cat, couleur, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, produit.getId());
                statement.setString(2,produit.getNameproduit());
                statement.setInt(3, produit.getDispoS());
                statement.setInt(4, produit.getDispoM());
                statement.setInt(5, produit.getDispoL());
                statement.setFloat(6, produit.getPrix());
                statement.setInt(7, produit.getCat());
                statement.setString(8, produit.getCouleur());
                statement.setString(9, produit.getImage());
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }
}

