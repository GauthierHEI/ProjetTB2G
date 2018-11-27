package TB2G.managers;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.UtilisateurDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private UtilisateurSource() {
    }

    public List<Utilisateur> listUtilisateur() {return utilisateurDao.listUtilisateur(); }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.addUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateurByMail(String mail) {

        return utilisateurDao.getUtilisateurByMail(mail);
    }


    public void editAdmin(Integer utilisateur_id, boolean role){
        utilisateurDao.editAdmin(utilisateur_id,role);
    }
    
    public void ModificationMdp(Utilisateur util, String mdp){
        if(mdp==null || "".equals(mdp)){
            throw new IllegalArgumentException("Le mot de passe ne peut pas être nulle");
        }else {
            utilisateurDao.ModificationMdp(util, mdp);
        }
    }

    public void ModificationAdresse(Utilisateur util, String adresse){
        if(adresse==null || "".equals(adresse)){
            throw new IllegalArgumentException("L'adresse ne peut pas être nulle");
        }else {
            utilisateurDao.ModificationAdresse(util, adresse);
        }
    }

    public void ModificationEmail(Utilisateur util, String email){
        if(email==null || "".equals(email)){
            throw new IllegalArgumentException("L'email ne peut pas être nulle");
        }else {
            utilisateurDao.ModificationEmail(util, email);
        }
    }

    public void deleteUtilisateur(Integer utilisateur_id){
            utilisateurDao.deleteUtilisateur(utilisateur_id);
            LOG.info("l'utilisateur a été supprimé");
    }

}
