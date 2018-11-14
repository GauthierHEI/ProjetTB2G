package TB2G.managers;

import TB2G.dao.ProduitDao;
import TB2G.dao.UtilisateurDao;
import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.Impl.UtilisateurDaoImpl;

import java.util.List;

public class ProduitStore  {

    private static class ProduitStoreHolder {
        private final static ProduitStore instance = new ProduitStore();
    }

    public static ProduitStore getInstance(){
        return ProduitStoreHolder.instance;
    }

    private ProduitDao produitdao = new ProduitDaoImpl();

    private ProduitStore() {
    }

    public List<Produit> listTshirt() {
      return produitdao.listTshirt();
    }

    public List<Produit> listPull() { return produitdao.listPull(); }

    public List<Produit> listChemise() { return produitdao.listChemise();}

    public List<Produit> listProduit() {return produitdao.listProduit(); }

    public Produit addProduit(Produit produit) {
        return produitdao.addProduit(produit);
    }

}
