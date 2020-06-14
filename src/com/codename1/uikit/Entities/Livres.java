/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Entities;

import java.util.Objects;

/**
 *
 * @author Sony
 */
public class Livres {

  private  int id; 
          
    String nom;
    String description;
    String auteur;
    int quantite; 
    String image;
    int id_type; 
    int nbPersonnes;
    private int likes ;
    //Type id;

    public Livres() {
    }

    public Livres(int id, String nom, String description, String auteur, int quantite, String image, int id_type, int nbPersonnes) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.quantite = quantite;
        this.image = image;
        this.id_type = id_type;
        this.nbPersonnes = nbPersonnes;
    }
     

    public Livres(String nom, String description, String auteur, int quantite, String image, Type id_type, int nbPersonnes) {
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.quantite = quantite;
        this.image = image;
       // this.id_type = id_type;
        this.nbPersonnes = nbPersonnes;
    }

    
    

    public Livres(int id, String nom, String description, String auteur, int quantite, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.quantite = quantite;
        this.image = image;
    }
    

    public Livres(int id, String nom, String description, String auteur, int quantite, String image, int id_type) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.quantite = quantite;
        this.image = image;
        //this.id_type = id_type;
    }

   

    public Livres(String nom, String description, String auteur, int quantite, int id_type) {
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.quantite = quantite;
        this.id_type = id_type;
    }

    public Livres(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }



    
    

  
    

   
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", auteur=" + auteur + ", quantite=" + quantite + ", image=" + image + ", id_type=" + id_type + ", nbPersonnes=" + nbPersonnes + '}';
    }
    
    

    


    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    

   

       
 }

