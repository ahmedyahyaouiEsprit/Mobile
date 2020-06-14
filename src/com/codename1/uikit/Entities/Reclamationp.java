/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Entities;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Reclamationp {
    int id;
    int parent;
    String message;

    public Reclamationp() {
    }
    

    public Reclamationp(int id) {
        this.id = id;
    }

    public Reclamationp(String message) {
        this.message = message;
    }

    public Reclamationp(int parent, String message) {
        this.parent = parent;
        this.message = message;
    }

    public Reclamationp(int id, int parent, String message) {
        this.id = id;
        this.parent = parent;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Reclamationp other = (Reclamationp) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.parent != other.parent) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamationp{" + "id=" + id + ", parent=" + parent + ", message=" + message + '}';
    }
    
    
}
