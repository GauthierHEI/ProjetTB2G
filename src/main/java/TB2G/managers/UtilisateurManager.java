package TB2G.managers;

import TB2G.dao.UtilisateurDao;
import TB2G.entities.Utilisateur;
import TB2G.dao.Impl.UtilisateurDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UtilisateurManager {
    static final Logger LOG = LoggerFactory.getLogger(UtilisateurManager.class);


    private static class UtilisateurManagerHolder {
        private final static UtilisateurManager instance = new UtilisateurManager();
    }

    public static UtilisateurManager getInstance(){
        LOG.info("get instance of UtilisateurManagerHolder");
        return UtilisateurManagerHolder.instance;
    }

    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    public List<Utilisateur> listUtilisateur() {
        LOG.info("run method listUtilisateur");
        return utilisateurDao.listUtilisateur(); }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        LOG.info("run method addUtilisateur");
        if (utilisateur.getNaissance() == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("La date est absente.");
        }
        return utilisateurDao.addUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateurByMail(String mail) {
        LOG.info("run method getUtilisateurByMail");
        if (mail != null && mail != "")
        {   LOG.info("mail is different from null or empty String");
            return utilisateurDao.getUtilisateurByMail(mail);}
        else {
            LOG.error("An exception is expected :");
            throw new IllegalArgumentException("Mail is null or void");
        }
    }


    public void editAdmin(Integer utilisateur_id, Boolean role){
        LOG.info("run method editAdmin");
        if (role == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Le role est null.");
        }
        if (utilisateur_id == null) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("L'Id est null.");
        }
        utilisateurDao.editAdmin(utilisateur_id,role);
    }

    public void ModificationMdp(Utilisateur utilisateur, String newMdp){
        LOG.info("run method ModificationMdp");
        if ((utilisateur == null) || (utilisateur.getId() == null) || (newMdp == null)){
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if ("".equals(newMdp)) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Le MDP est void");
        }
        else {
            LOG.info("each paramater is different from null and mdp's String is not empty");
            utilisateurDao.ModificationMdp(utilisateur, newMdp);
        }
    }

    public void ModificationAdresse(Utilisateur utilisateur, String adresse){
        LOG.info("run method ModificationAdresse");
        if ((utilisateur == null) || (utilisateur.getId() == null) || (adresse == null)){
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if ("".equals(adresse)){
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("Adresse est void");
        }
        else {
            LOG.info("each paramater is different from null and adresse's String is not empty");
            utilisateurDao.ModificationAdresse(utilisateur, adresse);
        }
    }

    public void ModificationEmail(Utilisateur utilisateur, String newEmail){
        LOG.info("run method ModificationEmail");
        if ((utilisateur == null) || (utilisateur.getId() == null) || (newEmail == null)){
            LOG.error("An exception is expected");
            throw new IllegalArgumentException("Un parametre ou plus est null");
        }
        else if("".equals(newEmail)) {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("L'email ne peut pas être void");
        }
        else {
            LOG.info("each paramater is different from null and newEmail's String is not empty");
            utilisateurDao.ModificationEmail(utilisateur, newEmail);
        }
    }

    public void deleteUtilisateur(Integer utilisateur_id){
        LOG.info("run method deleteUtilisateur");
        if(utilisateur_id != null) {
            LOG.info("utilisateur_id is different from null");
            utilisateurDao.deleteUtilisateur(utilisateur_id);
        }
        else {
            LOG.error("An exception is expected : ");
            throw new IllegalArgumentException("ID cannot be null.");
        }
    }

}
