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
        return PanierManager.PanierManagerHolder.instance;
    }

    private PanierDao panierdao = new PanierDaoImpl();

    public Panier addP2P(Panier produit) {
        if (produit==null) {
            throw new IllegalArgumentException("Produit cannot be null");
        }
        else if (produit.getIdUtil() == null) {
            throw new IllegalArgumentException("Produit Id Util cannot be null");
        }
        else if (produit.getProduit() == null || produit.getProduit().getId() == null) {
            throw new IllegalArgumentException("Produit Id cannot be null");
        }
        else if (produit.getTaille() == null || "".equals(produit.getTaille()) || (!"S".equals(produit.getTaille()) && !"M".equals(produit.getTaille()) && !"L".equals(produit.getTaille()))) {
            throw new IllegalArgumentException("Taille cannot be null or void or not S-M-L");
        }
        else if (produit.getQuantite() == null) {
            throw new IllegalArgumentException("Quantite cannot be null");
        }
        return panierdao.addP2P(produit);
    }

    public List<Panier> listPanier(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return panierdao.listPanier(id);
    }

    public void AcheterPanier(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        panierdao.AcheterPanier(id);
    }

    public void deleteProduitPanier(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        panierdao.deleteProduitPanier(id);
    }

}
