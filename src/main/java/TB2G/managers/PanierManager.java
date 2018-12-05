package TB2G.managers;

import TB2G.dao.Impl.PanierDaoImpl;
import TB2G.dao.PanierDao;
import TB2G.entities.Panier;
import TB2G.entities.Produit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PanierManager {

    static final Logger LOG = LoggerFactory.getLogger(PanierManager.class);


    private static class PanierManagerHolder {
        private final static PanierManager instance = new PanierManager();
    }

    public static PanierManager getInstance(){
        LOG.info("get instance of PanierManagerHolder;");
        return PanierManager.PanierManagerHolder.instance;
    }

    private PanierDao panierdao = new PanierDaoImpl();

    public Panier addP2P(Panier produit) {
        LOG.info("run method addP2P");
        if (produit==null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Produit cannot be null");
        }
        else if (produit.getIdUtil() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Produit Id Util cannot be null");
        }
        else if (produit.getProduit() == null || produit.getProduit().getId() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Produit Id cannot be null");
        }
        else if (produit.getTaille() == null || "".equals(produit.getTaille()) || (!"S".equals(produit.getTaille()) && !"M".equals(produit.getTaille()) && !"L".equals(produit.getTaille()))) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Taille cannot be null or void or not S-M-L");
        }
        else if (produit.getQuantite() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Quantite cannot be null");
        }
        LOG.info("all Panier's parameters are different from null");
        return panierdao.addP2P(produit);
    }

    public List<Panier> listPanier(Integer id) {
        LOG.info("run method listPanier");
        if (id == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("id is different from null");
        return panierdao.listPanier(id);
    }

    public void AcheterPanier(Integer id) {
        LOG.info("run method AcheterPanier");
        if (id == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("id is different from null");
        panierdao.AcheterPanier(id);
    }

    public void deleteProduitPanier(Integer id) {
        LOG.info("run method deleteProduitPanier");
        if (id == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("id is different from null");
        panierdao.deleteProduitPanier(id);
    }

}
