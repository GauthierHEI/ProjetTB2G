package TB2G.dao;

import TB2G.entities.Produit;

import java.util.List;

public interface ProduitDao {

    Produit addProduit(Produit produit);

    Produit modifProduit(Produit produit);

    List<Produit> listProduit();

    List<Produit> listTshirt();

    List<Produit> listPull();

    List<Produit> listChemise();

    Produit getProduitById(Integer produitId);

    Integer deleteProduit(Integer id);

}
