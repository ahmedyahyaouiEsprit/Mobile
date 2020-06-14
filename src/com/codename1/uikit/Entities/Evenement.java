/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Entities;

import com.codename1.ui.spinner.Picker;
import java.util.Objects;

/**
 *
 * @author Baha
 */
public class Evenement {

    private int idevenement;
    private String titre;
    private String description;
    private String date;
    private int etat;
    private int nbParticipantMax;
    private String lieu;
    private int frais;
    private String datefin;
    private int categorie;
    private int club;
    private String image_name;
    private String updated_at;

    public Evenement() {
    }

    public Evenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public Evenement(String titre, String description, String date, int etat, int nbParticipantMax, String lieu,
            int frais, String datefin, int categorie, int club) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.club = club;
    }

    public Evenement(String titre, String description, String date, int etat, int nbParticipantMax, String lieu,
            int frais, String datefin, int categorie) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public Evenement(int idevenement, String titre, String description, String date, int etat, int nbParticipantMax,
            String lieu, int frais, String datefin, int categorie, int club) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.club = club;
    }

    public Evenement(int idevenement, String titre, String description, String date, int etat, int nbParticipantMax,
            String lieu, int frais, String datefin, int categorie) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;

    }

    public Evenement(int idevenement, String titre, String description, String date, int etat, int nbParticipantMax,
            String lieu, int frais, String datefin, int categorie, String image_name, String updated_at) {
        this.idevenement = idevenement;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.nbParticipantMax = nbParticipantMax;
        this.lieu = lieu;
        this.frais = frais;
        this.datefin = datefin;
        this.categorie = categorie;
        this.image_name = image_name;
        this.updated_at = updated_at;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNbParticipantMax() {
        return nbParticipantMax;
    }

    public void setNbParticipantMax(int nbParticipantMax) {
        this.nbParticipantMax = nbParticipantMax;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idevenement=" + idevenement + ", titre=" + titre + ", description=" + description + ", date=" + date + ", etat=" + etat + ", nbParticipantMax=" + nbParticipantMax + ", lieu=" + lieu + ", frais=" + frais + ", datefin=" + datefin + ", categorie=" + categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.titre);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.date);
        hash = 23 * hash + this.etat;
        hash = 23 * hash + this.nbParticipantMax;
        hash = 23 * hash + Objects.hashCode(this.lieu);
        hash = 23 * hash + Float.floatToIntBits(this.frais);
        hash = 23 * hash + Objects.hashCode(this.datefin);
        hash = 23 * hash + this.categorie;
        hash = 23 * hash + this.club;
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
        final Evenement other = (Evenement) obj;
        if (this.idevenement != other.idevenement) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (this.nbParticipantMax != other.nbParticipantMax) {
            return false;
        }
        if (Float.floatToIntBits(this.frais) != Float.floatToIntBits(other.frais)) {
            return false;
        }
        if (this.categorie != other.categorie) {
            return false;
        }
        if (this.club != other.club) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.datefin, other.datefin)) {
            return false;
        }
        return true;
    }
    
    public static boolean isNumeric(final String str) {

        
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
    
    public static boolean isValidTitre(String titre) {
        return (titre.length() > 1) && (titre.charAt(0) >= 'A' && titre.charAt(0) <= 'Z') && !isNumeric(titre);
    }

    public static boolean isValidDescription(String description) {
        return (description.length() > 1) && (description.charAt(0) >= 'A' && description.charAt(0) <= 'Z');
    }

    public static boolean isValidDateDebAdd(String dateDeb) {
        int dE, mE, yE;
        Picker date = new Picker();
        System.out.println(dateDeb);
        if (dateDeb.indexOf("/") == 2) {
            dE = Integer.parseInt(dateDeb.substring(0, 2));
            mE = Integer.parseInt(dateDeb.substring(3, 5));
            yE = Integer.parseInt(dateDeb.substring(6, 8)) + 2000;
        } else {
            yE = Integer.parseInt(dateDeb.substring(0, 4));
            mE = Integer.parseInt(dateDeb.substring(5, 7));
            dE = Integer.parseInt(dateDeb.substring(8, 10)) + 2000;
        }
        System.out.println("Debut " + yE + "/" + mE + "/" + dE);
        int yN = Integer.parseInt(date.getText().substring(6, date.getText().length())) + 2000;
        int mN = Integer.parseInt(date.getText().substring(3, 5));
        int dN = Integer.parseInt(date.getText().substring(0, 2));
        System.out.println("Now " + yN + "/" + mN + "/" + dN);
        return (yE > yN || (yE == yN && mE > mN) || (yE == yN && mE == mN && dE >= dN));
    }

    public static boolean isValideDateFinAdd(String dateDeb, String dateFin) {
        int dE, mE, yE;
        int dN = Integer.parseInt(dateDeb.substring(0, 2));
        int mN = Integer.parseInt(dateDeb.substring(3, 5));
        int yN = Integer.parseInt(dateDeb.substring(6, 8)) + 2000;

        if (dateFin.length() > 6) {
            yE = Integer.parseInt(dateFin.substring(6, dateFin.length()));
            mE = Integer.parseInt(dateFin.substring(3, 5));
            dE = Integer.parseInt(dateFin.substring(0, 2));
        } else {
            yE = Integer.parseInt(dateFin.substring(0, 4));
            mE = Integer.parseInt(dateFin.substring(5, 7));
            dE = Integer.parseInt(dateFin.substring(8, 10));
        }
        if (yE < 2000) {
            yE += 2000;
        }
        System.out.println("Fin " + yE + "/" + mE + "/" + dE);
        return (yE > yN || (yE == yN && mE > mN) || (yE == yN && mE == mN && dE >= dN));
    }

    public static String format(String date) {
        int yN = Integer.parseInt(date.substring(0, 4));
        int mN = Integer.parseInt(date.substring(5, 7));
        int dN = Integer.parseInt(date.substring(8, 10));
        if (dN < 10) {
            return String.valueOf("0" + dN) + "/" + mN + "/" + yN;
        } else if (mN < 10) {
            return String.valueOf(dN) + "/0" + mN + "/" + yN;
        } else {
            return String.valueOf(dN) + "/" + mN + "/" + yN;
        }
    }

    public static boolean isValidNbPart(int nbPart) {
        return nbPart > 0;
    }

    public static boolean isValidFrais(int frais) {
        return frais >= 0;
    }

}
