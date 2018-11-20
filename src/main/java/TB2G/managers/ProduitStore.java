package TB2G.managers;

import TB2G.dao.ProduitDao;
import TB2G.dao.UtilisateurDao;
import TB2G.entities.Produit;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.Impl.UtilisateurDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProduitStore  {

    static final Logger LOG = LoggerFactory.getLogger(ProduitStore.class);


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

    public Produit modifProduit(Produit produit) {return produitdao.modifProduit(produit);}

    public Produit getProduitById(Integer produitId) {return produitdao.getProduitById(produitId);}

    public Produit addProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Produit can not be null.");
        }
        if (produit.getNameproduit() == null || "".equals(produit.getNameproduit())) {
            throw new IllegalArgumentException("Name can not be null.");
        }
        if (produit.getDispoS() == null) {
            produit.setDispoS(0);
        }
        if (produit.getDispoM() == null) {
            produit.setDispoM(0);
        }
        if (produit.getDispoL() == null) {
            produit.setDispoL(0);
        }
        if (produit.getPrix() == null) {
            throw new IllegalArgumentException("Prix can not be null.");
        }
        if (produit.getCat() == null) {
            throw new IllegalArgumentException("Categorie can not be null.");
        }
        if (produit.getCouleur() == null) {
            throw new IllegalArgumentException("Couleur can not be null.");
        }
        if (produit.getHexcouleur() == null )
            throw new IllegalArgumentException("Hexcouleur can not be null.");
        LOG.info("Nouveau produit : nom{}", produit.getNameproduit());
        return produitdao.addProduit(produit);

    }

}
