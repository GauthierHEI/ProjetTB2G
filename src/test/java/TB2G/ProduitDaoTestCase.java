package TB2G;

import TB2G.dao.Impl.DataSourceProvider;
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

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

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
}
