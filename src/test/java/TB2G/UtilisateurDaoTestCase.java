package TB2G;

import TB2G.dao.Impl.DataSourceProvider;
import TB2G.dao.Impl.UtilisateurDaoImpl;
import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

public class UtilisateurDaoTestCase {
    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM utilisateur");

            stmt.executeUpdate("INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('alex@alex.fr','Alex','Alex','1997-10-21','$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0','10 rue Adolphe','10 rue Adolphe',false);");
            stmt.executeUpdate("INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('admin@hei.yncrea.fr','Admin','Admin','1978-05-24','$argon2i$v=19$m=65536,t=5,p=1$h6yY9UMpKwAFiKSM9vrBpDnOaxAAcvMzkDIxK2hjg+b0NrRI7Hutfpz8PS+rchimUndfBfHdN/JQDQVpuBEl7DL3dxhOmG6yVEmO0GZ1PTJq3fT/kNi2bcbikEjp+ttCHgMKMYvab5iW2VsDjwUbinxb0PixDqDOnZYCV5U3SIE$q8Rv5q8v71NIFpzVnHjGI1MaJQOq8b9fJZyzuzHi9GN/NEr1IMFuYjlpneLvnXpKECtvqNq+s/cYuUiWqFJvsgl1j3HumqnubfXeTi5q20N3LCZis1EaZKd1pYsmpF8vysVdQe9V5GofVrrjCwIohoq+3Qj49rQb+GBCBVyJmpw','13 rue de Toul','13 rue de Toul',true);");
        }
    }

    @Test
    public void shouldListUtilisateur() {

        //WHEN
        List<Utilisateur> utilisateurs = utilisateurDao.listUtilisateur();

        //THEN
        assertThat(utilisateurs).hasSize(2);
        assertThat(utilisateurs).extracting("email","prenom","nom","naissance","motdepasse","adresseliv","adressefac","admin").containsOnly(
                tuple("alex@alex.fr","Alex","Alex", LocalDate.of(1997, 10, 21),"$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0","10 rue Adolphe","10 rue Adolphe",false),
                tuple("admin@hei.yncrea.fr","Admin","Admin",LocalDate.of(1978, 05, 24),"$argon2i$v=19$m=65536,t=5,p=1$h6yY9UMpKwAFiKSM9vrBpDnOaxAAcvMzkDIxK2hjg+b0NrRI7Hutfpz8PS+rchimUndfBfHdN/JQDQVpuBEl7DL3dxhOmG6yVEmO0GZ1PTJq3fT/kNi2bcbikEjp+ttCHgMKMYvab5iW2VsDjwUbinxb0PixDqDOnZYCV5U3SIE$q8Rv5q8v71NIFpzVnHjGI1MaJQOq8b9fJZyzuzHi9GN/NEr1IMFuYjlpneLvnXpKECtvqNq+s/cYuUiWqFJvsgl1j3HumqnubfXeTi5q20N3LCZis1EaZKd1pYsmpF8vysVdQe9V5GofVrrjCwIohoq+3Qj49rQb+GBCBVyJmpw","13 rue de Toul","13 rue de Toul",true)
        );
    }

    @Test
    public void shouldGetUtilisateur() {

        //WHEN
        Utilisateur utilisateur = utilisateurDao.getUtilisateurByMail("admin@hei.yncrea.fr");

        //THEN
        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getPrenom()).isEqualTo("Admin");
        assertThat(utilisateur.getNom()).isEqualTo("Admin");
        assertThat(utilisateur.getNaissance()).isEqualTo(LocalDate.of(1978, 05, 24));
        assertThat(utilisateur.getMotdepasse()).isEqualTo("$argon2i$v=19$m=65536,t=5,p=1$h6yY9UMpKwAFiKSM9vrBpDnOaxAAcvMzkDIxK2hjg+b0NrRI7Hutfpz8PS+rchimUndfBfHdN/JQDQVpuBEl7DL3dxhOmG6yVEmO0GZ1PTJq3fT/kNi2bcbikEjp+ttCHgMKMYvab5iW2VsDjwUbinxb0PixDqDOnZYCV5U3SIE$q8Rv5q8v71NIFpzVnHjGI1MaJQOq8b9fJZyzuzHi9GN/NEr1IMFuYjlpneLvnXpKECtvqNq+s/cYuUiWqFJvsgl1j3HumqnubfXeTi5q20N3LCZis1EaZKd1pYsmpF8vysVdQe9V5GofVrrjCwIohoq+3Qj49rQb+GBCBVyJmpw");
        assertThat(utilisateur.getAdresseliv()).isEqualTo("13 rue de Toul");
        assertThat(utilisateur.getAdressefac()).isEqualTo("13 rue de Toul");
        assertThat(utilisateur.getAdmin()).isEqualTo(true);
    }

    @Test
    public void shouldNotGetUtilisateur() {

        //WHEN
        Utilisateur utilisateur = utilisateurDao.getUtilisateurByMail("falseEmail");

        //THEN
        assertThat(utilisateur).isNull();
    }

    @Test
    public void shouldAddUtilisateur() throws Exception {

        //GIVEN
        Utilisateur utilisateurToCreate = new Utilisateur(null,"test@email.fr","Test","Test",
                LocalDate.of(2000,01,01),"Test",
                "1 rue de Test 1000 Ville","1 rue de Test 1000 Ville",false);

        //WHEN
        Utilisateur utilisateurCreated = utilisateurDao.addUtilisateur(utilisateurToCreate);

        //THEN
        assertThat(utilisateurCreated).isNotNull();
        assertThat(utilisateurCreated.getEmail()).isEqualTo("test@email.fr");
        assertThat(utilisateurCreated.getPrenom()).isEqualTo("Test");
        assertThat(utilisateurCreated.getNom()).isEqualTo("Test");
        assertThat(utilisateurCreated.getNaissance()).isEqualTo(LocalDate.of(2000,01,01));
        assertThat(utilisateurCreated.getMotdepasse()).isEqualTo("Test");
        assertThat(utilisateurCreated.getAdresseliv()).isEqualTo("1 rue de Test 1000 Ville");
        assertThat(utilisateurCreated.getAdressefac()).isEqualTo("1 rue de Test 1000 Ville");
        assertThat(utilisateurCreated.getAdmin()).isEqualTo(false);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE email ='test@email.fr'")) {
                assertThat(utilisateurCreated).isNotNull();
                assertThat(utilisateurCreated.getEmail()).isEqualTo("test@email.fr");
                assertThat(utilisateurCreated.getPrenom()).isEqualTo("Test");
                assertThat(utilisateurCreated.getNom()).isEqualTo("Test");
                assertThat(utilisateurCreated.getNaissance()).isEqualTo(LocalDate.of(2000,01,01));
                assertThat(utilisateurCreated.getMotdepasse()).isEqualTo("Test");
                assertThat(utilisateurCreated.getAdresseliv()).isEqualTo("1 rue de Test 1000 Ville");
                assertThat(utilisateurCreated.getAdressefac()).isEqualTo("1 rue de Test 1000 Ville");
                assertThat(utilisateurCreated.getAdmin()).isEqualTo(false);
            }
        }
    }
}









