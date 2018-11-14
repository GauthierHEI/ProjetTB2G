package TB2G.managers;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.UtilisateurDaoImpl;

import java.util.List;

public class UtilisateurSource {

    private static class UtilisateurSourceHolder {
        private final static UtilisateurSource instance = new UtilisateurSource();
    }

    public static UtilisateurSource getInstance(){
        return UtilisateurSourceHolder.instance;
    }

    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    private UtilisateurSource() {
    }

    public List<Utilisateur> listUtilisateur() {return utilisateurDao.listUtilisateur(); }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.addUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateurByMail(String mail) {

        return utilisateurDao.getUtilisateurByMail(mail);
    }

}
