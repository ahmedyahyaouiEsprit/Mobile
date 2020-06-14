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
public class Type {
    
    private int idL;
    private String libelle;
    
     public Type() {
    }

    public Type(int idL, String libelle) {
        this.idL = idL;
        this.libelle = libelle;
    }

    public Type(String libelle) {
        this.libelle = libelle;
    }
    
     public int getIdL() {
        return idL;
    }

    public void setIdL(int idL) {
        this.idL = idL;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
     @Override
    public String toString() {
        return libelle;
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
        final Type other = (Type) obj;
        if (this.idL != other.idL) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }
    
}
