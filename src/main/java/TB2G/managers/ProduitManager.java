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
        return ProduitManagerHolder.instance;
    }

    private ProduitDao produitdao = new ProduitDaoImpl();

    public List<Produit> listTshirt() {
        return produitdao.listTshirt();
    }

    public List<Produit> listPull() { return produitdao.listPull(); }

    public List<Produit> listChemise() { return produitdao.listChemise();}

    public List<Produit> listProduit() {return produitdao.listProduit(); }

    public Produit modifProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Produit cannot be null");
        }
        return produitdao.modifProduit(produit);
    }

    public void deleteProduit(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        produitdao.deleteProduit(id);
    }

    public Produit getProduitById(Integer produitId) {
        if (produitId == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return produitdao.getProduitById(produitId);
    }

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
        if (produit.getCouleur() == null || "".equals(produit.getCouleur())) {
            throw new IllegalArgumentException("Couleur can not be null.");
        }
        if (produit.getHexcouleur() == null || "".equals(produit.getHexcouleur())) {
            throw new IllegalArgumentException("Hexcouleur can not be null.");
        }
        return produitdao.addProduit(produit);
    }

    public Produit getProduit(Integer produitId) {
        if (produitId == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return produitdao.getProduit( produitId);
    }

    public List<Produit> RechercheProduit(String recherche){
        if (recherche == null) {
            throw new IllegalArgumentException("Recherche cannot be null");
        }
        else if(recherche.equals("")){
            return produitdao.listProduit();
        }
        else {
            return produitdao.RechercheProduit(recherche);
        }
    }

    public Integer getQuantiteDispoS(Integer produitId) {
        if (produitId != null) {
            return produitdao.getQuantiteDispoS(produitId);
        }
        else {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public Integer getQuantiteDispoM(Integer produitId) {
        if (produitId != null) {
            return produitdao.getQuantiteDispoM(produitId);
        }
        else {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public Integer getQuantiteDispoL(Integer produitId) {
        if (produitId != null) {
            return produitdao.getQuantiteDispoL(produitId);
        }
        else {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public void updateDispoS(Integer quantiteAcheter, Integer produitId) {
        if (quantiteAcheter != null && produitId != null) {
            produitdao.updateDispoS(quantiteAcheter, produitId);
        }
        else {
            throw new IllegalArgumentException("Parameter is null");
        }
    }

    public void updateDispoM(Integer quantiteAcheter, Integer produitId) {
        if (quantiteAcheter != null && produitId != null) {
            produitdao.updateDispoM(quantiteAcheter, produitId);
        }
        else {
            throw new IllegalArgumentException("Parameter is null");
        }
    }

    public void updateDispoL(Integer quantiteAcheter, Integer produitId) {
        if (quantiteAcheter != null && produitId != null) {
            produitdao.updateDispoL(quantiteAcheter, produitId);
        }
        else {
            throw new IllegalArgumentException("Parameter is null");
        }
    }
}
