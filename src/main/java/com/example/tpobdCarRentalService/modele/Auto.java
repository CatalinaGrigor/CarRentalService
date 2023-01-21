

//class modele Auto, avec constructeur, getter, setter

package com.example.tpobdCarRentalService.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Auto {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    int id_produit;
    int annee;
    int kilometrage;
    String modele;
    String inmatriculation;
    Boolean loue;
    double prix;

    public Auto() {
    }

    public Auto(int annee, int kilometrage, String modele, String inmatriculation, Boolean loue, double prix) {
        this.id_produit = id_produit;
        this.annee = annee;
        this.kilometrage = kilometrage;
        this.modele = modele;
        this.inmatriculation = inmatriculation;
        this.loue = loue;
        this.prix = prix;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getInmatriculation() {
        return inmatriculation;
    }

    public void setInmatriculation(String inmatriculation) {
        this.inmatriculation = inmatriculation;
    }

    public Boolean getLoue() {
        return loue;
    }

    public void setLoue(Boolean loue) {
        this.loue = loue;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id_produit=" + id_produit +
                ", annee=" + annee +
                ", kilometrage=" + kilometrage +
                ", modele='" + modele + '\'' +
                ", inmatriculation='" + inmatriculation + '\'' +
                ", loue=" + loue +
                ", prix=" + prix +
                '}';
    }
}
