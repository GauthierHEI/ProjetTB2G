package TB2G;

import TB2G.dao.Impl.DataSourceProvider;
import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.ProduitDao;
import TB2G.entities.Produit;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

public class ProduitDaoTestCase {
    private ProduitDao produitDao = new ProduitDaoImpl();

    /*@Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM produit");

            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('1','Tshirt Gris',10,10,10,9.99,1,'Gris','','#000000');");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('2','Pull Gris',10,10,10,19.99,2,'Gris','','#000000');");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('3','Chemise Blanche',10,10,10,14.99,3,'Blanc','','#000000');");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('4','Tshirt Blanc',10,10,10,9.99,1,'Blanc','','#000000' );");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('5','Tshirt Rouge',10,10,10,9.99,1,'Rouge','','#000000' );");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('6','Tshirt Bleu',10,10,10,9.99,1,'Bleu','','#000000'   );");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('7','Pull Bleu',10,10,10,19.99,2,'Bleu','','#000000'    );");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('8','Pull Rouge',10,10,10,19.99,2,'Rouge','','#000000'  );");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('9','Chemise Bleue',10,10,10,14.99,3,'Bleu','','#000000');");
            stmt.executeUpdate("INSERT INTO `produit` (`produit_id`,`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('10','Tshirt Noir',10,10,10,9.99,1,'Noir','','#000000'   );");

        }
    }

    @Test
    public void shouldListProduits() {
        //WHEN
        List<Produit> produits = produitDao.listProduit();

        //THEN
        assertThat(produits).hasSize(10);
        assertThat(produits).extracting("nameproduit","dispoS","dispoM","dispoL","prix","cat","couleur","image","Hexcouleur").containsOnly(
                tuple("Tshirt Gris",10,10,10,9.99f,1,"Gris","","#000000"),
                tuple("Pull Gris",10,10,10,19.99f,2,"Gris","","#000000"),
                tuple("Chemise Blanche",10,10,10,14.99f,3,"Blanc","","#000000"),
                tuple("Tshirt Blanc",10,10,10,9.99f,1,"Blanc","","#000000"),
                tuple("Tshirt Rouge",10,10,10,9.99f,1,"Rouge","","#000000"),
                tuple("Tshirt Bleu",10,10,10,9.99f,1,"Bleu","","#000000"),
                tuple("Pull Bleu",10,10,10,19.99f,2,"Bleu","","#000000"),
                tuple("Pull Rouge",10,10,10,19.99f,2,"Rouge","","#000000"),
                tuple("Chemise Bleue",10,10,10,14.99f,3,"Bleu","","#000000"),
                tuple("Tshirt Noir",10,10,10,9.99f,1,"Noir","","#000000")
        );
    }

    @Test
    public void shouldListTshirts() {
        //WHEN
        List<Produit> produits = produitDao.listTshirt();

        //THEN
        assertThat(produits).hasSize(5);
        assertThat(produits).extracting("nameproduit","dispoS","dispoM","dispoL","prix","cat","couleur","image","Hexcouleur").containsOnly(
                tuple("Tshirt Gris",10,10,10,9.99f,1,"Gris","","#000000"),
                tuple("Tshirt Blanc",10,10,10,9.99f,1,"Blanc","","#000000"),
                tuple("Tshirt Rouge",10,10,10,9.99f,1,"Rouge","","#000000"),
                tuple("Tshirt Bleu",10,10,10,9.99f,1,"Bleu","","#000000"),
                tuple("Tshirt Noir",10,10,10,9.99f,1,"Noir","","#000000")
        );
    }

    @Test
    public void shouldListPulls() {
        //WHEN
        List<Produit> produits = produitDao.listPull();

        //THEN
        assertThat(produits).hasSize(3);
        assertThat(produits).extracting("nameproduit","dispoS","dispoM","dispoL","prix","cat","couleur","image","Hexcouleur").containsOnly(
                tuple("Pull Gris",10,10,10,19.99f,2,"Gris","","#000000"),
                tuple("Pull Bleu",10,10,10,19.99f,2,"Bleu","","#000000"),
                tuple("Pull Rouge",10,10,10,19.99f,2,"Rouge","","#000000")
        );
    }

    @Test
    public void shouldListChemise() {
        //WHEN
        List<Produit> produits = produitDao.listChemise();

        //THEN
        assertThat(produits).hasSize(2);
        assertThat(produits).extracting("nameproduit","dispoS","dispoM","dispoL","prix","cat","couleur","image","Hexcouleur").containsOnly(
                tuple("Chemise Blanche",10,10,10,14.99f,3,"Blanc","","#000000"),
                tuple("Chemise Bleue",10,10,10,14.99f,3,"Bleu","","#000000")
        );
    }

    @Test
    public void shouldGetProduit() {

        //WHEN
        int produitId = 1;
        Produit produit = produitDao.getProduitById(produitId);

        //THEN
        assertThat(produit).isNotNull();
        assertThat(produit.getNameproduit()).isEqualTo("Tshirt Gris");
        assertThat(produit.getDispoS()).isEqualTo(10);
        assertThat(produit.getDispoM()).isEqualTo(10);
        assertThat(produit.getDispoL()).isEqualTo(10);
        assertThat(produit.getPrix()).isEqualTo(9.99f);
        assertThat(produit.getCat()).isEqualTo(1);
        assertThat(produit.getCouleur()).isEqualTo("Gris");
        assertThat(produit.getImage()).isEqualTo("");
        assertThat(produit.getHexcouleur()).isEqualTo("#000000");
    }

    @Test
    public void shouldNotGetProduit() {
        //WHEN
        int produitId = -1;
        Produit produit = produitDao.getProduitById(produitId);

        //THEN
        assertThat(produit).isNull();
    }

    @Test
    public void shouldGetProduitForPanier() {

        //WHEN
        int produitId = 1;
        Produit produit = produitDao.getProduit(produitId);

        //THEN
        assertThat(produit).isNotNull();
        assertThat(produit.getId()).isEqualTo(1);
        assertThat(produit.getNameproduit()).isEqualTo("Tshirt Gris");
        assertThat(produit.getPrix()).isEqualTo(9.99f);
        assertThat(produit.getImage()).isEqualTo("");
    }

    @Test
    public void shouldNotGetProduitForPanier() {
        //WHEN
        int produitId = -1;
        Produit produit = produitDao.getProduit(produitId);

        //THEN
        assertThat(produit).isNull();
    }

    @Test
    public void shouldAddProduit() throws Exception {

        //GIVEN
        String name = "nouveau";
        int dispoS = 1;
        int dispoM = 1;
        int dispoL = 1;
        float prix = (float) 10.00;
        int cat = 1;
        String couleur = "Blanc";
        String image = "";
        String hexcouleur = "#FFFFFF";

        Produit produit = new Produit(null, name, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur);

        //WHEN
        Produit newProduit = produitDao.addProduit(produit);

        //THEN
        assertThat(newProduit).isNotNull();
        assertThat(newProduit.getNameproduit()).isEqualTo("nouveau");
        assertThat(newProduit.getDispoS()).isEqualTo(1);
        assertThat(newProduit.getDispoM()).isEqualTo(1);
        assertThat(newProduit.getDispoL()).isEqualTo(1);
        assertThat(newProduit.getPrix()).isEqualTo(10.00f);
        assertThat(newProduit.getCat()).isEqualTo(1);
        assertThat(newProduit.getCouleur()).isEqualTo("Blanc");
        assertThat(newProduit.getImage()).isEqualTo("");
        assertThat(newProduit.getHexcouleur()).isEqualTo("#FFFFFF");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE produit='nouveau'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("produit_id")).isEqualTo(newProduit.getId());
                assertThat(rs.getString("produit")).isEqualTo("nouveau");
                assertThat(rs.getInt("dispoS")).isEqualTo(1);
                assertThat(rs.getInt("dispoM")).isEqualTo(1);
                assertThat(rs.getInt("dispoL")).isEqualTo(1);
                assertThat(rs.getFloat("prix")).isEqualTo(10.00f);
                assertThat(rs.getInt("cat")).isEqualTo(1);
                assertThat(rs.getString("couleur")).isEqualTo("Blanc");
                assertThat(rs.getString("image")).isEqualTo("");
                assertThat(rs.getString("hexcouleur")).isEqualTo("#FFFFFF");
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButIsNull() throws IllegalArgumentException {

        Produit produit = null;

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButNameIsNull() throws IllegalArgumentException {

        Produit produit = new Produit(null, null, null, null, null, (float) 9.99, 1, "atroce", null, "#FFFFFF");

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButNameIsVoid() throws IllegalArgumentException {

        Produit produit = new Produit(null, "", null, null, null, (float) 9.99, 1, "atroce", null, "#FFFFFF");

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButPrixIsNull() throws IllegalArgumentException {

        Produit produit = new Produit(null, "test", null, null, null, null, 1, "atroce", null, "#7E5700");

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButCatIsNull() throws IllegalArgumentException {

        Produit produit = new Produit(null, "test", null, null, null, (float) 9.99, null, "atroce", null, "#7E5700");

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButCouleurIsNull() throws IllegalArgumentException {

        Produit produit = new Produit(null, "test", null, null, null, (float) 9.99, 1, null, null, "#7E5700");

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButHexCouleurIsNull() throws IllegalArgumentException {

        Produit produit = new Produit(null, "test", null, null, null, (float) 9.99, 1, "atroce", null, null);

        //WHEN
        produitDao.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test
    public void shouldDeleteProduit() {

        //GIVEN
        int produitId = 1;

        //WHEN
        produitDao.deleteProduit(produitId);
        Produit produit = produitDao.getProduitById(produitId);

        //THEN
        assertThat(produit).isNull();
    }

    @Test
    public void shouldModifProduit() throws Exception {

        //GIVEN
        int produitId = 1;
        String name = "modif";
        int dispoS = 1;
        int dispoM = 1;
        int dispoL = 1;
        float prix = (float) 10.00;
        int cat = 1;
        String couleur = "Blanc";
        String image = "";
        String hexcouleur = "#FFFFFF";

        Produit produit = new Produit(produitId, name, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur);

        //WHEN
        Produit newProduit = produitDao.modifProduit(produit);

        //THEN
        assertThat(newProduit).isNotNull();
        assertThat(newProduit.getNameproduit()).isEqualTo("modif");
        assertThat(newProduit.getDispoS()).isEqualTo(1);
        assertThat(newProduit.getDispoM()).isEqualTo(1);
        assertThat(newProduit.getDispoL()).isEqualTo(1);
        assertThat(newProduit.getPrix()).isEqualTo(10.00f);
        assertThat(newProduit.getCat()).isEqualTo(1);
        assertThat(newProduit.getCouleur()).isEqualTo("Blanc");
        assertThat(newProduit.getImage()).isEqualTo("");
        assertThat(newProduit.getHexcouleur()).isEqualTo("#FFFFFF");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE produit='modif'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("produit_id")).isEqualTo(newProduit.getId());
                assertThat(rs.getString("produit")).isEqualTo("modif");
                assertThat(rs.getInt("dispoS")).isEqualTo(1);
                assertThat(rs.getInt("dispoM")).isEqualTo(1);
                assertThat(rs.getInt("dispoL")).isEqualTo(1);
                assertThat(rs.getFloat("prix")).isEqualTo(10.00f);
                assertThat(rs.getInt("cat")).isEqualTo(1);
                assertThat(rs.getString("couleur")).isEqualTo("Blanc");
                assertThat(rs.getString("image")).isEqualTo("");
                assertThat(rs.getString("hexcouleur")).isEqualTo("#FFFFFF");
            }
        }
    }

    @Test
    public void shouldModifProduitAndValuesAreNull() throws Exception {

        //GIVEN
        Integer produitId = 1;
        String name = "";
        Integer dispoS = null;
        Integer dispoM = null;
        Integer dispoL = null;
        Float prix = null;
        Integer cat = null;
        String couleur = "";
        String image = null;
        String hexcouleur = "";

        Produit produit = new Produit(produitId, name, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur);

        //WHEN
        Produit newProduit = produitDao.modifProduit(produit);

        //THEN
        assertThat(newProduit).isNotNull();
        assertThat(newProduit.getNameproduit()).isEqualTo("Tshirt Gris");
        assertThat(newProduit.getDispoS()).isEqualTo(10);
        assertThat(newProduit.getDispoM()).isEqualTo(10);
        assertThat(newProduit.getDispoL()).isEqualTo(10);
        assertThat(newProduit.getPrix()).isEqualTo(9.99f);
        assertThat(newProduit.getCat()).isEqualTo(1);
        assertThat(newProduit.getCouleur()).isEqualTo("Gris");
        assertThat(newProduit.getImage()).isEqualTo("");
        assertThat(newProduit.getHexcouleur()).isEqualTo("#000000");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE produit='Tshirt Gris'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("produit_id")).isEqualTo(newProduit.getId());
                assertThat(rs.getString("produit")).isEqualTo("Tshirt Gris");
                assertThat(rs.getInt("dispoS")).isEqualTo(10);
                assertThat(rs.getInt("dispoM")).isEqualTo(10);
                assertThat(rs.getInt("dispoL")).isEqualTo(10);
                assertThat(rs.getFloat("prix")).isEqualTo(9.99f);
                assertThat(rs.getInt("cat")).isEqualTo(1);
                assertThat(rs.getString("couleur")).isEqualTo("Gris");
                assertThat(rs.getString("image")).isEqualTo("");
                assertThat(rs.getString("hexcouleur")).isEqualTo("#000000");
            }
        }
    }

    @Test
    public void shouldModifProduitAndNameCouleurHexcouleurVoid() throws Exception {

        //GIVEN
        Integer produitId = 1;
        String name = null;
        Integer dispoS = null;
        Integer dispoM = null;
        Integer dispoL = null;
        Float prix = null;
        Integer cat = null;
        String couleur = null;
        String image = null;
        String hexcouleur = null;

        Produit produit = new Produit(produitId, name, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur);

        //WHEN
        Produit newProduit = produitDao.modifProduit(produit);

        //THEN
        assertThat(newProduit).isNotNull();
        assertThat(newProduit.getNameproduit()).isEqualTo("Tshirt Gris");
        assertThat(newProduit.getDispoS()).isEqualTo(10);
        assertThat(newProduit.getDispoM()).isEqualTo(10);
        assertThat(newProduit.getDispoL()).isEqualTo(10);
        assertThat(newProduit.getPrix()).isEqualTo(9.99f);
        assertThat(newProduit.getCat()).isEqualTo(1);
        assertThat(newProduit.getCouleur()).isEqualTo("Gris");
        assertThat(newProduit.getImage()).isEqualTo("");
        assertThat(newProduit.getHexcouleur()).isEqualTo("#000000");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE produit='Tshirt Gris'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("produit_id")).isEqualTo(newProduit.getId());
                assertThat(rs.getString("produit")).isEqualTo("Tshirt Gris");
                assertThat(rs.getInt("dispoS")).isEqualTo(10);
                assertThat(rs.getInt("dispoM")).isEqualTo(10);
                assertThat(rs.getInt("dispoL")).isEqualTo(10);
                assertThat(rs.getFloat("prix")).isEqualTo(9.99f);
                assertThat(rs.getInt("cat")).isEqualTo(1);
                assertThat(rs.getString("couleur")).isEqualTo("Gris");
                assertThat(rs.getString("image")).isEqualTo("");
                assertThat(rs.getString("hexcouleur")).isEqualTo("#000000");
            }
        }
    }

    @Test
    public void shouldModifProduitAndHexcouleur000001() throws Exception {

        //GIVEN
        Integer produitId = 1;
        String name = null;
        Integer dispoS = null;
        Integer dispoM = null;
        Integer dispoL = null;
        Float prix = null;
        Integer cat = null;
        String couleur = null;
        String image = null;
        String hexcouleur = "#000001";

        Produit produit = new Produit(produitId, name, dispoS, dispoM, dispoL, prix, cat, couleur, image, hexcouleur);

        //WHEN
        Produit newProduit = produitDao.modifProduit(produit);

        //THEN
        assertThat(newProduit).isNotNull();
        assertThat(newProduit.getNameproduit()).isEqualTo("Tshirt Gris");
        assertThat(newProduit.getDispoS()).isEqualTo(10);
        assertThat(newProduit.getDispoM()).isEqualTo(10);
        assertThat(newProduit.getDispoL()).isEqualTo(10);
        assertThat(newProduit.getPrix()).isEqualTo(9.99f);
        assertThat(newProduit.getCat()).isEqualTo(1);
        assertThat(newProduit.getCouleur()).isEqualTo("Gris");
        assertThat(newProduit.getImage()).isEqualTo("");
        assertThat(newProduit.getHexcouleur()).isEqualTo("#000000");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE produit='Tshirt Gris'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("produit_id")).isEqualTo(newProduit.getId());
                assertThat(rs.getString("produit")).isEqualTo("Tshirt Gris");
                assertThat(rs.getInt("dispoS")).isEqualTo(10);
                assertThat(rs.getInt("dispoM")).isEqualTo(10);
                assertThat(rs.getInt("dispoL")).isEqualTo(10);
                assertThat(rs.getFloat("prix")).isEqualTo(9.99f);
                assertThat(rs.getInt("cat")).isEqualTo(1);
                assertThat(rs.getString("couleur")).isEqualTo("Gris");
                assertThat(rs.getString("image")).isEqualTo("");
                assertThat(rs.getString("hexcouleur")).isEqualTo("#000000");
            }
        }
    }
    */
}
