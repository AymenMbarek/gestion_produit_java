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
public class Client {
    public String nom_prenom;
   public String telephone ;
   public String adresse;

    public Client(String nom_prenom, String telephone, String adresse) {
        this.nom_prenom = nom_prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    
    
}
