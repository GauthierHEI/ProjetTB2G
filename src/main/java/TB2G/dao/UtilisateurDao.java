package TB2G.dao;

import TB2G.entities.Utilisateur;

import java.util.List;

public interface UtilisateurDao {

    Utilisateur addUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> listUtilisateur();

}