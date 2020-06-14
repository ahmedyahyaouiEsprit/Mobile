/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Utils;

import com.codename1.db.Database;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class Statics1 {
     Database db;
         public Database getdb(){
        return db;
    }
          public Statics1() {
        try {
            
            db = Database.openOrCreate("MaDb.db");
            db.execute("create table IF NOT EXISTS Personne (id INTEGER PRIMARY KEY,UserID INTEGER);");
            } catch (IOException ex) {
            System.out.println("Probleme de BAse!!");
        }
        
    }
   public static final String BASE_URL="http://localhost/iSchool3/web/app_dev.php/";
    
}




    

   

  

