package TB2G.dao;

import TB2G.entities.Utilisateur;

import java.util.List;

public interface UtilisateurDao {

    Utilisateur addUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> listUtilisateur();

    Utilisateur getUtilisateurByMail(String mail);

    void ModificationMdp(Utilisateur utilisateur,String newMdp);

    void ModificationAdresse(Utilisateur utilisateur,String newAdresse);

    void ModificationEmail(Utilisateur utilisateur,String newEmail);
}