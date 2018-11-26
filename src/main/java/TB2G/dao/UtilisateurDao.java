package TB2G.dao;

import TB2G.entities.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface UtilisateurDao {

    Utilisateur addUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> listUtilisateur();

    Utilisateur getUtilisateurByMail(String mail);

    void editAdmin(Integer utilisateur_id, boolean role);

    void ModificationMdp(Utilisateur utilisateur,String newMdp);

    void deleteUtilisateur(Integer utilisateur_id);

}