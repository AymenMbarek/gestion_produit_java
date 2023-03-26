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
public class Facture {
    public int N_fact;
   public String nom_prenom_forn;
  public   float	montant_a_paye;
   public  float      montant_verser;
   public float montant_reste;
   public String date_versement;
   public String etat;

    public Facture(int N_fact,String nom_prenom_forn, float montant_a_paye, float montant_verser, float montant_reste, String date_versement, String etat) {
       this.N_fact = N_fact;
        this.nom_prenom_forn = nom_prenom_forn;
        this.montant_a_paye = montant_a_paye;
        this.montant_verser = montant_verser;
        this.montant_reste = montant_reste;
        this.date_versement = date_versement;
        this.etat = etat;
    }

    public int getN_fact() {
        return N_fact;
    }

    public String getNom_prenom_forn() {
        return nom_prenom_forn;
    }

    public float getMontant_a_paye() {
        return montant_a_paye;
    }

    public float getMontant_verser() {
        return montant_verser;
    }

    public float getMontant_reste() {
        return montant_reste;
    }

    public String getDate_versement() {
        return date_versement;
    }

    public String getEtat() {
        return etat;
    }
    
}
