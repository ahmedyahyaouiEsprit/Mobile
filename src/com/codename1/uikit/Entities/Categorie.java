/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Entities;

import java.util.Objects;

/**
 *
 * @author Baha
 */
public class Categorie {

    private int idcategorie;
    private String libelle;

    public Categorie(int idcategorie, String libelle) {
        this.idcategorie = idcategorie;
        this.libelle = libelle;
    }

    public Categorie(String libelle, String description) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idcategorie=" + idcategorie + ", libelle=" + libelle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        if (this.idcategorie != other.idcategorie) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }
}
