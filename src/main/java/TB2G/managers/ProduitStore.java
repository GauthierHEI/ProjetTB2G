package TB2G.managers;

import TB2G.dao.ProduitDao;
import TB2G.dao.UtilisateurDao;
import TB2G.entities.produit;
import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.Impl.UtilisateurDaoImpl;

import java.util.List;

public class ProduitStore  {

    public static class ProduitStoreHolder {
        private final static ProduitStore instance = new ProduitStore();
    }

    public static ProduitStore getInstance(){
        return ProduitStoreHolder.instance;
    }

    private ProduitStore() {

    }

    private ProduitDao produitdao = new ProduitDaoImpl();
    private UtilisateurDao utilisateurdao =  new UtilisateurDaoImpl();

    public List<produit> listTshirt() {
      return produitdao.listTshirt();
    }




}
