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

    List<Produit> RechercheProduit(String recherche);

    Integer getQuantiteDispoS(Integer produitId);

    Integer getQuantiteDispoL(Integer produitId);

    Integer getQuantiteDispoM(Integer produitId);

    void updateDispoS(Integer quantiteAcheter, Integer produitId);

    void updateDispoM(Integer quantiteAcheter, Integer produitId);

    void updateDispoL(Integer quantiteAcheter, Integer produitId);

    List<Produit> getProduitByName(String nameProduit);

}
