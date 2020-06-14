package com.codename1.uikit.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.Entities.Reclamationp;
import com.codename1.uikit.Entities.User;
import com.codename1.uikit.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author solta
 */
public class ServiceUser {

    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    public static User userC;

    public void ajoutUser(User ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL +"/api/register?email=" + ta.getEmail()
                + "&password=" + ta.getPassword() + "&username=" + ta.getUsername() +"&role=" + ta.getRole();
        con.setUrl(Url);
        System.out.println(ta.toString());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    public boolean addTask(Reclamationp t) {
        //String url = Statics.BASE_URL +"/new/"+ t.getNom() + "/" + t.getDescription()+ "/" + t.getAdresse()+ "/" + t.getDomaine(); //création de l'URL
        //String url = Statics.BASE_URL+"/newService?menu_name="+t.getMenu_name()+"&price="+t.getPrice();
        String url = Statics.BASE_URL+"/api/reclamation?message=" +t.getMessage()+"&parent"+t.getParent();
     
       
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }  

    public ArrayList<User> parseListUsersJson(String json) {

        ArrayList<User> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();

                float id = Float.parseFloat(obj.get("id").toString());

                //e.setId((int) id);
                e.setUsername(obj.get("username").toString());
                e.setEmail(obj.get("email").toString());
                e.setId((int) id);
                e.setPassword(obj.get("password").toString());
                System.out.println(e);
                listTasks.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listTasks);
        return listTasks;

    }

    ArrayList<User> listTasks = new ArrayList<>();

    public ArrayList<User> getList2(String x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/alll?username=" + x);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listTasks = ser.parseListUsersJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listTasks.isEmpty()) {

        } else {
            return listTasks;
        }
        return null;
    }

    public boolean Modifierprofile(User u) {
        String url = Statics.BASE_URL + "/json/modifyProfile?username=" + u.getUsername() +
                    "&password=" + u.getPassword() + "&email=" + u.getEmail();
        ConnectionRequest request = new ConnectionRequest(url, false);
            request.setUrl(url);
            request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
                
            }
        });
            NetworkManager.getInstance().addToQueueAndWait(request);
       return resultOK;
    }
}
