package TB2G.dao;

import TB2G.entities.Produit;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public interface ProduitDao {

    Produit addProduit(Produit produit);

    List<Produit> listProduit();

    List<Produit> listTshirt();

    List<Produit> listPull();

    List<Produit> listChemise();
}
