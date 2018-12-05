package TB2G;

import TB2G.dao.Impl.PanierDaoImpl;
import TB2G.dao.PanierDao;
import TB2G.entities.Panier;
import TB2G.entities.Produit;
import TB2G.managers.PanierManager;
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

public class PanierManagerTestCase {

    @Mock
    private PanierDao panierDaoMock = new PanierDaoImpl();

    @InjectMocks
    private PanierManager panierManager = new PanierManager();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddP2P() {

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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "M",
                10,
                false
        );

        Mockito.when(panierDaoMock.addP2P(Mockito.anyObject())).thenReturn(produitPan);

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        assertEquals(produitPan1, produitPan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButProduitPanIsNull() {

        //GIVEN
        Panier produitPan = null;

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButProduitIdUtilIsNull() {

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

        Panier produitPan = new Panier(
                1,
                null,
                produit,
                "M",
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButProduitIsNull() {

        //GIVEN
        Panier produitPan = new Panier(
                1,
                1,
                null,
                "M",
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButProduitIdIsNull() {

        //GIVEN
        Produit produit = new Produit(
                null,
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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "M",
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButTailleIsNull() {

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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                null,
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButTailleIsVoid() {

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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "",
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButTailleIsNotSML() {

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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "J",
                10,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddP2PButQuantiteIsNull() {

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

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "M",
                null,
                false
        );

        //WHEN
        Panier produitPan1 = panierManager.addP2P(produitPan);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldListPanier() {

        //GIVEN
        List<Panier> paniers = new ArrayList<>();
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

        Integer idUtil = 1;

        Panier produitPan = new Panier(
                1,
                1,
                produit,
                "M",
                10,
                false
        );

        Panier produitPan1 = new Panier(
                2,
                1,
                produit,
                "M",
                10,
                false
        );

        paniers.add(produitPan);
        paniers.add(produitPan1);

        Mockito.when(panierDaoMock.listPanier(Mockito.anyObject())).thenReturn(paniers);

        //WHEN
        List<Panier> paniers2 = panierManager.listPanier(idUtil);

        //THEN
        assertEquals(paniers2, paniers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldListPanierButIdUtilIsNull() {

        //GIVEN
        Integer idUtil = null;

        //WHEN
        List<Panier> paniers2 = panierManager.listPanier(idUtil);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldAcheterPanier() {

        //WHEN
        Integer idUtil = 1;

        Mockito.doNothing().when(panierDaoMock).AcheterPanier(Mockito.anyInt());

        //WHEN
        panierManager.AcheterPanier(idUtil);

        //THEN
        Mockito.verify(panierDaoMock, Mockito.times(1)).AcheterPanier(Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAcheterPanierButIdUtilIsNull() {

        //WHEN
        Integer idUtil = null;

        //WHEN
        panierManager.AcheterPanier(idUtil);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldDeleteProduitPanier() {

        //WHEN
        Integer id = 1;

        Mockito.doNothing().when(panierDaoMock).deleteProduitPanier(Mockito.anyInt());

        //WHEN
        panierManager.deleteProduitPanier(id);

        //THEN
        Mockito.verify(panierDaoMock, Mockito.times(1)).deleteProduitPanier(Mockito.anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldDeleteProdutPanierButIdIsNull() {

        //WHEN
        Integer idUtil = null;

        //WHEN
        panierManager.deleteProduitPanier(idUtil);

        //THEN
        fail("Should get IllegalArgumentException");
    }
}
