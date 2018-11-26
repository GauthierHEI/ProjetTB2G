package TB2G.entities;

public class Panier {

    private Integer idCo;
    private Produit produit;
    private Integer idUtil;
    private String taille;
    private Integer quantite;
    private boolean vendu;

    public Panier(Integer idCo, Integer idUtil, Produit produit, String taille, Integer quantite ,boolean vendu) {
        this.idCo = idCo;
        this.produit = produit;
        this.idUtil = idUtil;
        this.taille = taille;
        this.quantite = quantite;
        this.vendu = vendu;
    }

    public Integer getIdCo() {
        return idCo;
    }

    public void setIdCo(Integer idCo) {
        this.idCo = idCo;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Integer getIdUtil() {
        return idUtil;
    }

    public void setIdUtil(Integer idUtil) {
        this.idUtil = idUtil;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public boolean isVendu() {
        return vendu;
    }

    public void setVendu(boolean vendu) {
        this.vendu = vendu;
    }
}
