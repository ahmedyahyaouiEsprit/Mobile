/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Services;

import com.codename1.uikit.Entities.ReservationLivre;
import static com.codename1.uikit.Services.ServiceUser.userC;
import com.codename1.uikit.Utils.Statics1;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.Entities.Livres;
import static com.codename1.uikit.cleanmodern.SignInForm.UID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Sony
 */
public class ReservationService {
    
    public static ServicesLivres instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ReservationService() {
         req = new ConnectionRequest();
    }

    public static ServicesLivres getInstance() {
        if (instance == null) {
            instance = new ServicesLivres();
        }
        return instance;
    }
    
    public boolean findEmp(ReservationLivre p)
    {
        for (int i=0;i<getAll().size();i++)
        {
            if((p.getId_livre()==getAll().get(i).getId_livre()) &&(p.getId_eleve()==getAll().get(i).getId_eleve())  )
            {
                 return true;       
            }
        }
        return false;
    
    }
    public ArrayList<Livres> Afficheresliv() {
      ArrayList<Livres> livres = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl(Statics1.BASE_URL+"biblio/ListeReserv");
      con.addResponseListener((NetworkEvent evt) -> {
          //listOffres = getListOffre(new String(con.getResponseData()));
          JSONParser jsonp = new JSONParser();
          try {
              Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
              System.out.println("eawfs"+restaurant);
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
    
    public ArrayList<ReservationLivre > getAll() {
       
        ArrayList<ReservationLivre> listParticipation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics1.BASE_URL+"biblio/AllReserv");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        
                        ReservationLivre p=new ReservationLivre();
                        
                        p.setId((int) Float.parseFloat(obj.get("id").toString()));
                        p.setId_eleve((int) Float.parseFloat(obj.get("ideleve").toString()));
                        p.setId_livre((int) Float.parseFloat(obj.get("idlivre").toString()));

                        listParticipation.add(p);
                        
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listParticipation;
    }
    
    public void empreinter(ReservationLivre a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/iSchool3/web/app_dev.php/biblio/AddReservss?idLivre="+a.getId_livre()+"&id="+UID ;//userC.getId();
       
        con.setUrl(Url);



        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str) ;//nrarrab run?oui

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public void rendre(ReservationLivre a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/iSchool3/web/app_dev.php/biblio/deleteReservss?idLivre="+a.getId_livre()+"&id="+UID ;//userC.getId();
        con.setUrl(Url); 
    

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     public int nbraimer(int id)
    {
        int nbr=0;
        for (int i=0;i<getAll().size();i++)
        {
           if(getAll().get(i).getId_livre()==id)
           {
               nbr++;
           }
        }
        return nbr;
    }
    
}
