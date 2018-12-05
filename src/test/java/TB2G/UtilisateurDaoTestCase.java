package TB2G;

import TB2G.dao.Impl.UtilisateurDaoImpl;
import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.managers.UtilisateurSource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

public class UtilisateurDaoTestCase {

    @Mock
    private UtilisateurDao utilisateurDaoMock = new UtilisateurDaoImpl();

    @InjectMocks
    private UtilisateurSource utilisateurSource = new UtilisateurSource();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldGetUtilisateur() {

        //GIVEN
        Integer utilisateurId = 1;
        String mail = "alex@alex.fr";
        String prenom = "Alex";
        String nom = "Alex";
        LocalDate dateNaissance = LocalDate.of(1997, 10, 21);
        String motDePasse = "test";//"$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0";
        String adresseLiv = "10 rue Adolphe";
        String adresseFac = "10 rue Adolphe";
        Boolean admin = false;
        Utilisateur utilisateur = new Utilisateur(
                utilisateurId,
                mail,
                prenom,
                nom,
                dateNaissance,
                motDePasse,
                adresseLiv,
                adresseFac,
                admin
        );

        Mockito.when(utilisateurDaoMock.getUtilisateurByMail(Mockito.anyString())).thenReturn(utilisateur);

        //WHEN

        Utilisateur result = utilisateurSource.getUtilisateurByMail("alex@alex.fr");

        //THEN
        assertEquals(result, utilisateur);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetUtilisateurButMailIsVoid() {

        //GIVEN
        Integer utilisateurId = 1;
        String mail = "alex@alex.fr";
        String prenom = "Alex";
        String nom = "Alex";
        LocalDate dateNaissance = null;
        String motDePasse = "test";
        String adresseLiv = "10 rue Adolphe";
        String adresseFac = "10 rue Adolphe";
        Boolean admin = false;
        Utilisateur utilisateur = new Utilisateur(
                utilisateurId,
                mail,
                prenom,
                nom,
                dateNaissance,
                motDePasse,
                adresseLiv,
                adresseFac,
                admin
        );

        Mockito.when(utilisateurDaoMock.getUtilisateurByMail(Mockito.anyString())).thenReturn(utilisateur);

        //WHEN

        Utilisateur result = utilisateurSource.getUtilisateurByMail("");

        //THEN
        fail("Should get mail not found.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetUtilisateurButMailIsNull() {
        //GIVEN
        Integer utilisateurId = 1;
        String mail = "alex@alex.fr";
        String prenom = "Alex";
        String nom = "Alex";
        LocalDate dateNaissance = null;
        String motDePasse = "test";
        String adresseLiv = "10 rue Adolphe";
        String adresseFac = "10 rue Adolphe";
        Boolean admin = false;
        Utilisateur utilisateur = new Utilisateur(
                utilisateurId,
                mail,
                prenom,
                nom,
                dateNaissance,
                motDePasse,
                adresseLiv,
                adresseFac,
                admin
        );

        Mockito.when(utilisateurDaoMock.getUtilisateurByMail(Mockito.anyString())).thenReturn(utilisateur);

        //WHEN

        Utilisateur result = utilisateurSource.getUtilisateurByMail(null);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldListUtilisateurs() {

        //GIVEN
        Integer utilisateurId1 = 1;
        String mail1 = "alex@alex.fr";
        String prenom1 = "Alex";
        String nom1 = "Alex";
        LocalDate dateNaissance1 = LocalDate.of(1997, 10, 21);
        String motDePasse1 = "test";//"$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0";
        String adresseLiv1 = "10 rue Adolphe";
        String adresseFac1 = "10 rue Adolphe";
        Boolean admin1 = false;
        Utilisateur utilisateur1 = new Utilisateur(
                utilisateurId1,
                mail1,
                prenom1,
                nom1,
                dateNaissance1,
                motDePasse1,
                adresseLiv1,
                adresseFac1,
                admin1
        );

        Integer utilisateurId2 = 1;
        String mail2 = "alex@alex.fr";
        String prenom2 = "Alex";
        String nom2 = "Alex";
        LocalDate dateNaissance2 = LocalDate.of(1997, 10, 21);
        String motDePasse2 = "test";//"$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0";
        String adresseLiv2 = "10 rue Adolphe";
        String adresseFac2 = "10 rue Adolphe";
        Boolean admin2 = false;
        Utilisateur utilisateur2 = new Utilisateur(
                utilisateurId2,
                mail2,
                prenom2,
                nom2,
                dateNaissance2,
                motDePasse2,
                adresseLiv2,
                adresseFac2,
                admin2
        );

        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(utilisateur1);
        utilisateurs.add(utilisateur2);

        Mockito.when(utilisateurDaoMock.listUtilisateur()).thenReturn(utilisateurs);

        //WHEN

        List<Utilisateur> result = utilisateurSource.listUtilisateur();

        //THEN
        assertEquals(result, utilisateurs);
    }



    @Test
    public void shouldAddUtilisateur(){

        //GIVEN
        Utilisateur utilisateurToCreate = new Utilisateur(null,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);

        Mockito.when(utilisateurDaoMock.addUtilisateur(Mockito.anyObject())).thenReturn(utilisateurToCreate);

        //WHEN
        Utilisateur result = utilisateurSource.addUtilisateur(utilisateurToCreate);

        //THEN
        assertEquals(result, utilisateurToCreate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddUtilisateurButDateIsNull() {
        //GIVEN
        Utilisateur utilisateurToCreate = new Utilisateur(null,"test@email.fr","Test","Test",
                null,"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);

        //WHEN
        Utilisateur result = utilisateurSource.addUtilisateur(utilisateurToCreate);

        //THEN
       fail("Should get Illegal ArgumentException");
    }


    @Test
    public void shouldEditAdmin() {

        //GIVEN
        Integer utilisateurId = 1;
        Boolean role = true;
        String mail = "alex@alex.fr";

        Mockito.doNothing().when(utilisateurDaoMock).editAdmin(Mockito.anyInt(), Mockito.anyBoolean());

        //WHEN
        utilisateurSource.editAdmin(utilisateurId, role);

        //THEN
        Mockito.verify(utilisateurDaoMock, Mockito.times(1)).editAdmin(Mockito.anyInt(), Mockito.anyBoolean());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldEditAdminButIdIsNull() {

        //GIVEN
        Integer utilisateurId = null;
        Boolean role = true;
        String mail = "alex@alex.fr";

        Mockito.doNothing().when(utilisateurDaoMock).editAdmin(Mockito.anyInt(), Mockito.anyBoolean());

        //WHEN
        utilisateurSource.editAdmin(utilisateurId, role);

        //THEN
        fail("l'id est null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldEditAdminButRoleIsNull() {

        //GIVEN
        Integer utilisateurId = 1;
        Boolean role = null;
        String mail = "alex@alex.fr";

        Mockito.doNothing().when(utilisateurDaoMock).editAdmin(Mockito.anyInt(), Mockito.anyBoolean());

        //WHEN
        utilisateurSource.editAdmin(utilisateurId, role);

        //THEN
        fail("l'id est null");
    }

    @Test
    public void shouldModifyPassword() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newMdp = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationMdp(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationMdp(utilisateur, newMdp);

        //THEN
        Mockito.verify(utilisateurDaoMock, Mockito.times(1)).ModificationMdp(Mockito.anyObject(), Mockito.anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyPasswordButUtilisateurIsNull() {

        //GIVEN
        Utilisateur utilisateur = null;
        String newMdp = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationMdp(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationMdp(utilisateur, newMdp);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyPasswordButUtilisateurIdIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(null,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newMdp = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationMdp(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationMdp(utilisateur, newMdp);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyPasswordButNewMDPIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newMdp = null;
        Mockito.doNothing().when(utilisateurDaoMock).ModificationMdp(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationMdp(utilisateur, newMdp);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyPasswordButNewMDPIsVoid() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newMdp = "";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationMdp(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationMdp(utilisateur, newMdp);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifyEmail() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newEmail = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationEmail(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationEmail(utilisateur, newEmail);

        //THEN
        Mockito.verify(utilisateurDaoMock, Mockito.times(1)).ModificationEmail(Mockito.anyObject(), Mockito.anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyEmailButUtilisateurIsNull() {

        //GIVEN
        Utilisateur utilisateur = null;
        String newEmail = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationEmail(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationEmail(utilisateur, newEmail);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyEmailButUtilisateurIdIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(null,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newEmail = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationEmail(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationEmail(utilisateur, newEmail);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyEmailButNewEmailIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newEmail = null;
        Mockito.doNothing().when(utilisateurDaoMock).ModificationEmail(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationEmail(utilisateur, newEmail);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyEmailButNewEmailIsVoid() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newEmail = "";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationEmail(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationEmail(utilisateur, newEmail);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldModifyAdresse() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newAdresse = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationAdresse(utilisateur, newAdresse);

        //THEN
        Mockito.verify(utilisateurDaoMock, Mockito.times(1)).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyAdresseButUtilisateurIsNull() {

        //GIVEN
        Utilisateur utilisateur = null;
        String newAdresse = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationAdresse(utilisateur, newAdresse);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyAdresseButUtilisateurIdIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(null,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newAdresse = "nouveau";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationAdresse(utilisateur, newAdresse);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyAdresseButNewAdresseIsNull() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newAdresse = null;
        Mockito.doNothing().when(utilisateurDaoMock).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationAdresse(utilisateur, newAdresse);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldModifyAdresseButNewAdresseIsVoid() {

        //GIVEN
        Utilisateur utilisateur = new Utilisateur(1,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);
        String newAdresse = "";
        Mockito.doNothing().when(utilisateurDaoMock).ModificationAdresse(Mockito.anyObject(), Mockito.anyString());

        //WHEN
        utilisateurSource.ModificationAdresse(utilisateur, newAdresse);

        //THEN
        fail("Should get IllegalArgumentException");
    }

    @Test
    public void shouldDeleteUtilisateur() {

        //GIVEN
        Integer utilisateur_id = 1;
        Mockito.doNothing().when(utilisateurDaoMock).deleteUtilisateur(Mockito.anyInt());

        //WHEN
        utilisateurSource.deleteUtilisateur(utilisateur_id);

        //THEN
        Mockito.verify(utilisateurDaoMock, Mockito.times(1)).deleteUtilisateur(Mockito.anyObject());

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldDeleteUtilisateurButIdIsNull() {

        //GIVEN
        Integer utilisateur_id = null;
        Mockito.doNothing().when(utilisateurDaoMock).deleteUtilisateur(Mockito.anyInt());

        //WHEN
        utilisateurSource.deleteUtilisateur(utilisateur_id);

        //THEN
        fail("Should get IllegalArgumentException");

    }
}
