package TB2G.entities;

public class Produit {

    private Integer id;
    private String nameproduit;
    private Integer dispoS;
    private Integer dispoM;
    private Integer dispoL;
    private Float prix;
    private Integer cat;
    private String couleur;
    private String image;
    private String hexcouleur;

    public Produit(Integer id, String nameproduit, Integer dispoS, Integer dispoM, Integer dispoL, Float prix, Integer cat, String couleur, String image, String hexcouleur) {
        this.id = id;
        this.nameproduit = nameproduit;
        this.dispoS = dispoS;
        this.dispoM = dispoM;
        this.dispoL = dispoL;
        this.prix = prix;
        this.cat = cat;
        this.couleur = couleur;
        this.image = image;
        this.hexcouleur = hexcouleur;
    }

    public Produit(Integer id, String nameproduit, Float prix, String image) {
        this.id = id;
        this.nameproduit = nameproduit;
        this.prix = prix;
        this.image = image;
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

    public Integer getDispoS() {
        return dispoS;
    }



    public void setDispoS(Integer dispoS) {
        this.dispoS = dispoS;
    }

    public Integer getDispoM() {
        return dispoM;
    }

    public void setDispoM(Integer dispoM) {
        this.dispoM = dispoM;
    }

    public Integer getDispoL() {
        return dispoL;
    }

    public void setDispoL(Integer dispoL) {
        this.dispoL = dispoL;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHexcouleur() { return hexcouleur; }

    public void setHexcouleur(String hexcouleur) { this.hexcouleur = hexcouleur;}
}
