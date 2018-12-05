package TB2G;

import TB2G.dao.Impl.ProduitDaoImpl;
import TB2G.dao.ProduitDao;
import TB2G.entities.Produit;
import TB2G.managers.ProduitStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ProduitDaoTestCase {

    @Mock
    private ProduitDao produitDaoMock = new ProduitDaoImpl();

    @InjectMocks
    private ProduitStore produitStore = new ProduitStore();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetProduit() {

        //GIVEN
        Integer produitId = 1;
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.getProduitById(Mockito.anyInt())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.getProduitById(produitId);

        //THEN
        assertEquals(produit1, produit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetProduitButIdIsNull() {
        //GIVEN
        Integer produitId = null;
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.getProduitById(Mockito.anyInt())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.getProduitById(produitId);

        //THEN
        fail("Should get IllegalArgumentException");

    }

    @Test
    public void shouldListProduits() {

        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        Mockito.when(produitDaoMock.listProduit()).thenReturn(produits);

        //WHEN
        List<Produit> result = produitStore.listProduit();

        //THEN
        assertEquals(result, produits);

    }

    @Test
    public void shouldListTshirts() {
        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        Mockito.when(produitDaoMock.listTshirt()).thenReturn(produits);

        //WHEN
        List<Produit> result = produitStore.listTshirt();

        //THEN
        assertEquals(result, produits);
    }

    @Test
    public void shouldListPulls() {
        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        Mockito.when(produitDaoMock.listPull()).thenReturn(produits);

        //WHEN
        List<Produit> result = produitStore.listPull();

        //THEN
        assertEquals(result, produits);
    }

    @Test
    public void shouldListChemise() {
        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        Mockito.when(produitDaoMock.listChemise()).thenReturn(produits);

        //WHEN
        List<Produit> result = produitStore.listChemise();

        //THEN
        assertEquals(result, produits);
    }

    @Test
    public void shouldGetProduitForPanier() {

        //GIVEN
        Integer produitId = 1;
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                9.99f,
                ""
        );

        Mockito.when(produitDaoMock.getProduit(Mockito.anyInt())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.getProduit(produitId);

        //THEN
        assertEquals(produit1, produit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetProduitForPanierButIdIsNull() {

        //GIVEN
        Integer produitId = null;
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                9.99f,
                ""
        );

        Mockito.when(produitDaoMock.getProduit(Mockito.anyInt())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.getProduit(produitId);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldAddProduit() throws Exception {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        assertEquals(produit1, produit);
    }

    @Test
    public void shouldAddProduitAndDispoAreNull() throws Exception {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                null,
                null,
                null,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        assertEquals(produit1, produit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = null;

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButNameIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                null,
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButNameIsVoid() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButPrixIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                null,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButCatIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                null,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get Illegal Argument Exception.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButCouleurIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                null,
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButCouleurIsVoid() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButHexCouleurIsNull() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                null
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddProduitButHexCouleurIsVoid() throws IllegalArgumentException {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                ""
        );

        Mockito.when(produitDaoMock.addProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.addProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException.");
    }

    @Test
    public void shouldDeleteProduit() {

        //GIVEN
        int produitId = 1;

        Mockito.doNothing().when(produitDaoMock).deleteProduit(Mockito.anyInt());

        //WHEN
        produitStore.deleteProduit(produitId);

        //THEN
        Mockito.verify(produitDaoMock, Mockito.times(1)).deleteProduit(Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldDeleteProduitButIdIsNull() {

        //GIVEN
        Integer produitId = null;

        Mockito.doNothing().when(produitDaoMock).deleteProduit(Mockito.anyInt());

        //WHEN
        produitStore.deleteProduit(produitId);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifProduit() {

        //GIVEN
        Produit produit = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        Mockito.when(produitDaoMock.modifProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.modifProduit(produit);

        //THEN
        assertEquals(produit1, produit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifProduitButProduitIsNull() {

        //GIVEN
        Produit produit = null;

        Mockito.when(produitDaoMock.modifProduit(Mockito.anyObject())).thenReturn(produit);

        //WHEN
        Produit produit1 = produitStore.modifProduit(produit);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldRechercheAll() {

        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        String recherche = "";

        Mockito.when(produitDaoMock.listProduit()).thenReturn(produits);

        //WHEN
        List<Produit> produitsRecherche = produitStore.RechercheProduit(recherche);

        //THEN
        assertEquals(produits, produitsRecherche);
    }

    @Test
    public void shouldRechercheTshirt() {

        //GIVEN
        List<Produit> produits = new ArrayList<>();

        Produit produit1 = new Produit(
                1,
                "Tshirt Gris",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );
        Produit produit2 = new Produit(
                2,
                "Tshirt Blanc",
                10,
                10,
                10,
                9.99f,
                1,
                "Gris",
                "",
                "#000000"
        );

        produits.add(produit1);
        produits.add(produit2);

        String recherche = "T-shirt";

        Mockito.when(produitDaoMock.RechercheProduit(Mockito.anyString())).thenReturn(produits);

        //WHEN
        List<Produit> produitsRecherche = produitStore.RechercheProduit(recherche);

        //THEN
        assertEquals(produits, produitsRecherche);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRechercheButRechercheIsNull(){

        //GIVEN
        String recherche = null;

        //WHEN
        List<Produit> produitsRecherche = produitStore.RechercheProduit(recherche);

        //THEN
        fail("Should get IllegalArgumentException");

    }

    @Test
    public void shouldGetDispoS() {

        //GIVEN
        Integer produit_id = 1;
        Integer dispoSOri = 10;

        Mockito.when(produitDaoMock.getQuantiteDispoS(Mockito.anyInt())).thenReturn(10);

        //WHEN
        Integer dispoS = produitStore.getQuantiteDispoS(produit_id);

        //THEN
        assertEquals(dispoSOri, dispoS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetDispoSButProduiIdIsNull() {

        //GIVEN
        Integer produit_id = null;

        //WHEN
        Integer dispoS = produitStore.getQuantiteDispoS(produit_id);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldGetDispoM() {

        //GIVEN
        Integer produit_id = 1;
        Integer dispoMOri = 10;

        Mockito.when(produitDaoMock.getQuantiteDispoM(Mockito.anyInt())).thenReturn(10);

        //WHEN
        Integer dispoM = produitStore.getQuantiteDispoM(produit_id);

        //THEN
        assertEquals(dispoMOri, dispoM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetDispoMButProduiIdIsNull() {

        //GIVEN
        Integer produit_id = null;

        //WHEN
        Integer dispoM = produitStore.getQuantiteDispoM(produit_id);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldGetDispoL() {

        //GIVEN
        Integer produit_id = 1;
        Integer dispoLOri = 10;

        Mockito.when(produitDaoMock.getQuantiteDispoL(Mockito.anyInt())).thenReturn(10);

        //WHEN
        Integer dispoL = produitStore.getQuantiteDispoL(produit_id);

        //THEN
        assertEquals(dispoLOri, dispoL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetDispoLButProduiIdIsNull() {

        //GIVEN
        Integer produit_id = null;

        //WHEN
        Integer dispoL = produitStore.getQuantiteDispoL(produit_id);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifyDispoS() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoS(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoS(produitId, quantiteAcheter);

        //THEN
        Mockito.verify(produitDaoMock, Mockito.times(1)).updateDispoS(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoSButIdIsNull() {

        //GIVEN
        Integer produitId = null;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoS(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoS(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoSButQuantiteIsNull() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = null;

        Mockito.doNothing().when(produitDaoMock).updateDispoS(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoS(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifyDispoM() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoM(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoM(produitId, quantiteAcheter);

        //THEN
        Mockito.verify(produitDaoMock, Mockito.times(1)).updateDispoM(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoMButIdIsNull() {

        //GIVEN
        Integer produitId = null;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoM(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoM(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoMButQuantiteIsNull() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = null;

        Mockito.doNothing().when(produitDaoMock).updateDispoM(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoM(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifyDispoL() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoL(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoL(produitId, quantiteAcheter);

        //THEN
        Mockito.verify(produitDaoMock, Mockito.times(1)).updateDispoL(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoLButIdIsNull() {

        //GIVEN
        Integer produitId = null;
        Integer quantiteAcheter = 1;

        Mockito.doNothing().when(produitDaoMock).updateDispoL(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoL(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyDispoLButQuantiteIsNull() {

        //GIVEN
        Integer produitId = 1;
        Integer quantiteAcheter = null;

        Mockito.doNothing().when(produitDaoMock).updateDispoL(Mockito.anyInt(), Mockito.anyInt());

        //WHEN
        produitStore.updateDispoL(produitId, quantiteAcheter);

        //THEN
        fail("Should get IllegalArgumentException");
    }
}
