package TB2G.dao;

import TB2G.entities.produit;

import java.util.List;

public interface ProduitDao {

    produit addProduit(produit produit);

    List<produit> listProduit();

}