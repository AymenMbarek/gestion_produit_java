/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.des.produit.modele;

/**
 *
 * @author AmineGasa
 */
public class Stock {
    int N_prod;
    String designation;
    String categorie;
    String unite;
    String date_exp;
    int qte_totale;
    int qte_vente;
    int qte_reste;
    float prix_vent;
    float prix_unt;
    float gain;

    public Stock(int N_prod, String designation, String categorie, String unite, String date_exp, int qte_totale, int qte_vente,  float prix_vent, float prix_unt) {
        this.N_prod = N_prod;
        this.designation = designation;
        this.categorie = categorie;
        this.unite = unite;
        this.date_exp = date_exp;
        this.qte_totale = qte_totale;
        this.qte_vente = qte_vente;
        this.qte_reste = this.qte_totale-this.qte_vente;
        this.prix_vent = prix_vent;
        this.prix_unt = prix_unt;
        this.gain = this.prix_vent-this.prix_unt;
    }

    public Stock(int N_prod, String designation, int qte_totale, int qte_vente, int qte_reste,float prix_ven, float gain) {
        this.N_prod = N_prod;
        this.designation = designation;
        this.qte_totale = qte_totale;
        this.qte_vente = qte_vente;
        this.qte_reste = qte_reste;
        this.prix_vent=prix_ven;
        this.gain = gain;
    }
    

    public int getN_prod() {
        return N_prod;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getUnite() {
        return unite;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public int getQte_totale() {
        return qte_totale;
    }

    public int getQte_vente() {
        return qte_vente;
    }

    public int getQte_reste() {
        return qte_reste;
    }

    public float getPrix_vent() {
        return prix_vent;
    }

    public float getPrix_unt() {
        return prix_unt;
    }

    public float getGain() {
        return gain;
    }


}
