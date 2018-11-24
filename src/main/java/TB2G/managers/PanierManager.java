package TB2G.managers;

import TB2G.dao.Impl.PanierDaoImpl;
import TB2G.dao.PanierDao;
import TB2G.entities.Panier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PanierManager {

    static final Logger LOG = LoggerFactory.getLogger(ProduitStore.class);


    private static class PanierManagerHolder {
        private final static PanierManager instance = new PanierManager();
    }

    public static PanierManager getInstance(){
        return PanierManager.PanierManagerHolder.instance;
    }

    private PanierDao panierdao = new PanierDaoImpl();

    private PanierManager() {
    }

    public Panier addP2P(Panier produit) {
        return panierdao.addP2P(produit);
    }

}