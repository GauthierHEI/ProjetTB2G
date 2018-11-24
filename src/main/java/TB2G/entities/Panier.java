package TB2G.entities;

public class Panier {

    private Integer idCo;
    private Integer id;
    private String nameproduit;
    private String taille;
    private Integer quantite;
    private float prixTot;
    private float prixUni;
    private boolean vendu;

    public Panier(Integer idCo, Integer id, String nameproduit, String taille, Integer quantite, float prixUni ,boolean vendu) {
        this.idCo = idCo;
        this.id = id;
        this.nameproduit = nameproduit;
        this.taille = taille;
        this.quantite = quantite;
        this.prixUni = prixUni;
        this.vendu = vendu;
    }

    public float getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(float prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getIdCo() {
        return idCo;
    }

    public void setIdCo(Integer idCo) {
        this.idCo = idCo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameproduit() {
        return nameproduit;
    }

    public void setNameproduit(String nameproduit) {
        this.nameproduit = nameproduit;
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

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(float prixTot) {
        this.prixTot = prixTot;
    }

    public boolean isVendu() {
        return vendu;
    }

    public void setVendu(boolean vendu) {
        this.vendu = vendu;
    }
}
