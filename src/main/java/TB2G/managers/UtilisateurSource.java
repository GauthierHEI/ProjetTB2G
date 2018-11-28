package TB2G.managers;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.UtilisateurDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurSource {
    static final Logger LOG = LoggerFactory.getLogger(UtilisateurSource.class);


    private static class UtilisateurSourceHolder {
        private final static UtilisateurSource instance = new UtilisateurSource();
    }

    public static UtilisateurSource getInstance(){
        return UtilisateurSourceHolder.instance;
    }

    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    public List<Utilisateur> listUtilisateur() {return utilisateurDao.listUtilisateur(); }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        if (utilisateur.getNaissance() == null) {
            throw new IllegalArgumentException("La date est absente.");
        }
        return utilisateurDao.addUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateurByMail(String mail) {
        if (mail != null && mail != "")
            return utilisateurDao.getUtilisateurByMail(mail);
        else {
            throw new IllegalArgumentException("Mail is null or void");
        }
    }


    public void editAdmin(Integer utilisateur_id, Boolean role){
        if (role == null) {
            throw new IllegalArgumentException("Le role est null.");
        }
        if (utilisateur_id == null) {
            throw new IllegalArgumentException("L'Id est null.");
        }
        utilisateurDao.editAdmin(utilisateur_id,role);
    }
    
    public void ModificationMdp(Utilisateur utilisateur, String newMdp){
        if ((utilisateur == null) || (utilisateur.getId() == null) || (newMdp == null)){
            LOG.error("Un parametre ou plus est null");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if ("".equals(newMdp)) {
            throw new IllegalArgumentException("Le MDP est void");
        }
        else {
            utilisateurDao.ModificationMdp(utilisateur, newMdp);
        }
    }

    public void ModificationAdresse(Utilisateur utilisateur, String adresse){
        if ((utilisateur == null) || (utilisateur.getId() == null) || (adresse == null)){
            LOG.error("Un parametre ou plus est null");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if ("".equals(adresse)){
            throw new IllegalArgumentException("Adresse est void");
        }
        else {
            utilisateurDao.ModificationAdresse(utilisateur, adresse);
        }
    }

    public void ModificationEmail(Utilisateur utilisateur, String newEmail){
        if ((utilisateur == null) || (utilisateur.getId() == null) || (newEmail == null)){
            LOG.error("Un parametre ou plus est null");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if("".equals(newEmail)) {
            throw new IllegalArgumentException("L'email ne peut pas être void");
        }
        else {
            utilisateurDao.ModificationEmail(utilisateur, newEmail);
        }
    }

}
