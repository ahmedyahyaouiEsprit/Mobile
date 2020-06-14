/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Entities;

/**
 *
 * @author Sony
 */
public class Biblioteque {
      private int id_b;
    private String nom_b;
    private String type;
    private String nom_matiere;
    private String fichier;

    
    
     public Biblioteque() {
    }
    
    public Biblioteque(int id_b) {
      
        this.id_b = id_b;
    
   }
    
    
    public Biblioteque(String nom_b, String type, String nom_matiere, String fichier ) {
        
        this.nom_b = nom_b;
        this.type = type;
        this.nom_matiere = nom_matiere;
        this.fichier = fichier;

    }
    
    
    public Biblioteque(int id_b, String nom_b, String type, String nom_matiere, String fichier) {
        this.id_b = id_b;
        this.nom_b = nom_b;
        this.type = type;
        this.nom_matiere = nom_matiere;
        this.fichier = fichier;

    }

   
    
    
    /**
     * @return the id_b
     */
    public int getId_b() {
        return id_b;
    }

    /**
     * @param id_b the id_b to set
     */
    public void setId_b(int id_b) {
        this.id_b = id_b;
    }

    /**
     * @return the nom_b
     */
    public String getNom_b() {
        return nom_b;
    }

    /**
     * @param nom_b the nom_b to set
     */
    public void setNom_b(String nom_b) {
        this.nom_b = nom_b;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the nom_matiere
     */
    public String getNom_matiere() {
        return nom_matiere;
    }

    /**
     * @param nom_matiere the nom_matiere to set
     */
    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }

    /**
     * @return the fichier
     */
    public String getFichier() {
        return fichier;
    }

    /**
     * @param fichier the fichier to set
     */
    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    
    
     @Override
    public String toString() {
        return "bibliothèque{" + "id=" + id_b + ", nom=" + nom_b + ", type=" + type + 
                ", nom_matiere=" + nom_matiere + ", fichier=" + fichier + '}';
    }
}
