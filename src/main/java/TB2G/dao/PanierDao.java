package TB2G.dao;

import TB2G.entities.Panier;

import java.util.List;

public interface PanierDao {

    Panier addP2P(Panier produit);

    List<Panier> listPanier(Integer id);

}
