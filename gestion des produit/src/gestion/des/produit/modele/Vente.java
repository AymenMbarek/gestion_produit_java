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
public class Vente {

    int N_vente;
    String nom_prenom;
    String commentaire;
    String marchendise;
    float prix_a_payez;
    float benefice;
    String date;

    public Vente(int N_vente, String nom_prenom, String commentaire, String marchendise, float prix_a_payez, float benefice, String date) {
        this.N_vente = N_vente;
        this.nom_prenom = nom_prenom;
        this.commentaire = commentaire;
        this.marchendise = marchendise;
        this.prix_a_payez = prix_a_payez;
        this.benefice = benefice;
        this.date = date;
    }

    public int getN_vente() {
        return N_vente;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getMarchendise() {
        return marchendise;
    }

    public float getPrix_a_payez() {
        return prix_a_payez;
    }

    public float getBenefice() {
        return benefice;
    }

    public String getDate() {
        return date;
    }
    

}