package TB2G.dao;

import TB2G.entities.Produit;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public interface ProduitDao {

    Produit getProduitById(Integer produitId);

    Produit addProduit(Produit produit);

    void deleteProduit(Integer id);

    List<Produit> listProduit();

    List<Produit> listTshirt();

    List<Produit> listPull();

    List<Produit> listChemise();

    Produit modifProduit(Produit produit);

    Produit getProduit(Integer id);

    Produit getProduitByName(String produitName);
}
