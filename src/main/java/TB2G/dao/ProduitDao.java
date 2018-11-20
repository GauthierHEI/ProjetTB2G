package TB2G.dao;

import TB2G.entities.Produit;

import java.util.List;

public interface ProduitDao {

    Produit addProduit(Produit produit);

    List<Produit> listProduit();

    List<Produit> listTshirt();

    List<Produit> listPull();

    List<Produit> listChemise();

    Produit modifProduit(Produit produit);

}
