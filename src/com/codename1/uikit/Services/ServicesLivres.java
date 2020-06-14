/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Services;

/**
 *
 * @author Sony
 */
import com.codename1.uikit.Entities.Livres;
import com.codename1.uikit.Utils.Statics1;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServicesLivres {
    public ArrayList<Livres> livres;
    
    public static ServicesLivres instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicesLivres() {
         req = new ConnectionRequest();
    }

    public static ServicesLivres getInstance() {
        if (instance == null) {
            instance = new ServicesLivres();
        }
        return instance;
    }
    public ArrayList<Livres> AfficheLivre() {
      ArrayList<Livres> livres = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl(Statics1.BASE_URL+"biblio/AllBooks");
      con.addResponseListener((NetworkEvent evt) -> {
          //listOffres = getListOffre(new String(con.getResponseData()));
          JSONParser jsonp = new JSONParser();
          try {
              Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
              
              List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
              for (Map<String, Object> obj : list) {
                  Livres t = new Livres();
                  float id = Float.parseFloat(obj.get("idLivre").toString());
                  t.setId((int)id);
                  t.setNom(obj.get("nom").toString());
                  t.setAuteur(obj.get("auteur").toString());
                  t.setDescription(obj.get("description").toString());
                  t.setImage(obj.get("image").toString());

                  
                  livres.add(t);
              }
          } catch (IOException ex) {
          }
      });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return livres;
    }
    
    public ArrayList<Livres> AfficheLivreRes() {
      ArrayList<Livres> livres = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl(Statics1.BASE_URL+"biblio/ListeReserv");
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {  
                        Livres t = new Livres();
                
                float id = Float.parseFloat(obj.get("idLivre").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setAuteur(obj.get("auteur").toString());
                t.setDescription(obj.get("description").toString());                                
                t.setImage(obj.get("image").toString());                
                t.setQuantite(((int)Float.parseFloat(obj.get("quantite").toString())));
                t.setNbPersonnes(((int)Float.parseFloat(obj.get("nbpersonnes").toString())));
   
                livres.add(t);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return livres;
    }
}