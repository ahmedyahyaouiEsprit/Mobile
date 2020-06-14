/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.uikit.Entities.Biblioteque;
import com.codename1.uikit.Entities.Livres;
import com.codename1.uikit.Utils.Statics1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sony
 */
public class servicesBiblioteque {
     public ArrayList<Biblioteque> Biblioteque;
    
    public static servicesBiblioteque instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public servicesBiblioteque() {
         req = new ConnectionRequest();
    }

    public static servicesBiblioteque getInstance() {
        if (instance == null) {
            instance = new servicesBiblioteque();
        }
        return instance;
    }
    public ArrayList<Biblioteque> AfficheLivre() {
      ArrayList<Biblioteque> Biblioteque = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl(Statics1.BASE_URL+"Elearning/AllBib");
      con.addResponseListener((NetworkEvent evt) -> {
          //listOffres = getListOffre(new String(con.getResponseData()));
          JSONParser jsonp = new JSONParser();
          try {
              Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
              
              List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
              for (Map<String, Object> obj : list) {
                  Biblioteque b = new Biblioteque();
                  float id = Float.parseFloat(obj.get("id_b").toString());
                  b.setId_b((int)id);
                  b.setNom_b(obj.get("nom_b").toString());
                  b.setNom_matiere(obj.get("nom_matiere").toString());
                  b.setFichier(obj.get("fichier").toString());
                  
                  
                 
                
              
    
                  Biblioteque.add(b);
              }
          } catch (IOException ex) {
          }
      });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Biblioteque;
    }
}
