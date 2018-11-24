package TB2G.entities;

import java.time.LocalDate;

public class Utilisateur {

    private Integer id;
    private String email;
    private String prenom;
    private String nom;
    private LocalDate naissance;
    private String motdepasse;
    private String adresseliv;
    private String adressefac;
    private Boolean admin;

    public Utilisateur(Integer id, String email, String prenom, String nom, LocalDate naissance,
                       String motdepasse, String adresseliv, String adressefac, Boolean admin) {
        this.id = id;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.naissance = naissance;
        this.motdepasse = motdepasse;
        this.adresseliv = adresseliv;
        this.adressefac = adressefac;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getAdresseliv() {
        return adresseliv;
    }

    public void setAdresseliv(String adresseliv) {
        this.adresseliv = adresseliv;
    }

    public String getAdressefac() {
        return adressefac;
    }

    public void setAdressefac(String adressefac) {
        this.adressefac = adressefac;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
