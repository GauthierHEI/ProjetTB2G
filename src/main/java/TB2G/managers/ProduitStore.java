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

    public void deleteProduit(Integer id) { produitdao.deleteProduit(id);}

    public Produit getProduitById(Integer produitId) {return produitdao.getProduitById(produitId);}

    public Produit addProduit(Produit produit) { return produitdao.addProduit(produit); }

    public File imageDansFichier (Part filePart) throws IOException {

        File uploads = new File(PropertiesUtils.cheminPro());
        File file = File.createTempFile("img", ".jpg", uploads);

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file;
    }

    public Produit getProduit(Integer id) {
        return produitdao.getProduit( id);
    }

    public Integer getQuantiteDispoS(Integer produitId) {
        return produitdao.getQuantiteDispoS(produitId);
    }

    public Integer getQuantiteDispoM(Integer produitId) {
        return produitdao.getQuantiteDispoM(produitId);
    }

    public Integer getQuantiteDispoL(Integer produitId) {
        return produitdao.getQuantiteDispoL(produitId);
    }

    public List<Produit> getProduitByName(String nameProduit) { return produitdao.getProduitByName(nameProduit); }

}
