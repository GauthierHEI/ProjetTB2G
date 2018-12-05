package TB2G.managers;

import TB2G.dao.ProduitDao;
import TB2G.entities.Produit;
import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.Impl.UtilisateurDaoImpl;

import TB2G.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;

public class ProduitManager  {

    static final Logger LOG = LoggerFactory.getLogger(ProduitManager.class);


    private static class ProduitManagerHolder {
        private final static ProduitManager instance = new ProduitManager();
    }

    public static ProduitManager getInstance(){
        LOG.info("get instance of ProduitManagerHolder");
        return ProduitManagerHolder.instance;
    }

    private ProduitDao produitdao = new ProduitDaoImpl();

    public List<Produit> listTshirt() {
        LOG.info("run method listTshirt");
        return produitdao.listTshirt();
    }

    public List<Produit> listPull() {
        LOG.info("run method listPull");
        return produitdao.listPull(); }

    public List<Produit> listChemise() {
        LOG.info("run method listChemise");
        return produitdao.listChemise();}

    public List<Produit> listProduit() {
        LOG.info("run method listProduit");
        return produitdao.listProduit(); }

    public Produit modifProduit(Produit produit) {
        LOG.info("run method modifProduit");
        if (produit == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Produit cannot be null");
        }
        LOG.info("produit is different from null");
        return produitdao.modifProduit(produit);
    }

    public void deleteProduit(Integer id) {
        LOG.info("run method deleteProduit");
        if (id == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("id is different from null");
        produitdao.deleteProduit(id);
    }

    public Produit getProduitById(Integer produitId) {
        LOG.info("run method getProduitById");
        if (produitId == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("produitId is different from null");
        return produitdao.getProduitById(produitId);
    }

    public Produit addProduit(Produit produit) {
        LOG.info("run method addProduit");
        if (produit == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Produit can not be null.");
        }
        if (produit.getNameproduit() == null || "".equals(produit.getNameproduit())) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Name can not be null.");
        }
        if (produit.getDispoS() == null) {
            LOG.info("convert S size variable from null to 0");
            produit.setDispoS(0);
        }
        if (produit.getDispoM() == null) {
            LOG.info("convert M size variable from null to 0");
            produit.setDispoM(0);
        }
        if (produit.getDispoL() == null) {
            LOG.info("convert L size variable from null to 0");
            produit.setDispoL(0);
        }
        if (produit.getPrix() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Prix can not be null.");
        }
        if (produit.getCat() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Categorie can not be null.");
        }
        if (produit.getCouleur() == null || "".equals(produit.getCouleur())) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Couleur can not be null.");
        }
        if (produit.getHexcouleur() == null || "".equals(produit.getHexcouleur())) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Hexcouleur can not be null.");
        }
        LOG.info("all parameters are different from null");
        return produitdao.addProduit(produit);
    }

    public Produit getProduit(Integer produitId) {
        LOG.info("run method getProduit");
        if (produitId == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
        LOG.info("produitId is different from null");
        return produitdao.getProduit( produitId);
    }

    public List<Produit> RechercheProduit(String recherche){
        LOG.info("run method RechercheProduit");
        if (recherche == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Recherche cannot be null");
        }
        else if(recherche.equals("")){
            LOG.info("research's String is empty so we return all list of Produit");
            return produitdao.listProduit();
        }
        else {
            LOG.info("research is different from null and not empty String");
            return produitdao.RechercheProduit(recherche);
        }
    }

    public Integer getQuantiteDispoS(Integer produitId) {
        LOG.info("run method getQuantiteDispoS");
        if (produitId != null) {
            LOG.info("produitId is different from null");
            return produitdao.getQuantiteDispoS(produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public Integer getQuantiteDispoM(Integer produitId) {
        LOG.info("run method getQuantiteDispoM");
        if (produitId != null) {
            LOG.info("produitId is different from null");
            return produitdao.getQuantiteDispoM(produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public Integer getQuantiteDispoL(Integer produitId) {
        LOG.info("run method getQuantiteDispoL");
        if (produitId != null) {
            LOG.info("produitId is different from null");
            return produitdao.getQuantiteDispoL(produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public void updateDispoS(Integer quantiteAcheter, Integer produitId) {
        LOG.info("run method updateDispoS");
        if (quantiteAcheter != null && produitId != null) {
            LOG.info("quantiteAcheter and produitId are different from null");
            produitdao.updateDispoS(quantiteAcheter, produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Parameter is null");
        }
    }

    public void updateDispoM(Integer quantiteAcheter, Integer produitId) {
        LOG.info("run method updateDispoM");
        if (quantiteAcheter != null && produitId != null) {
            LOG.info("quantiteAcheter and produitId are different from null");
            produitdao.updateDispoM(quantiteAcheter, produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Parameter is null");
        }
    }

    public void updateDispoL(Integer quantiteAcheter, Integer produitId) {
        LOG.info("run method updateDispoL");
        if (quantiteAcheter != null && produitId != null) {
            LOG.info("quantiteAcheter and produitId are different from null");
            produitdao.updateDispoL(quantiteAcheter, produitId);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Parameter is null");
        }
    }
}
