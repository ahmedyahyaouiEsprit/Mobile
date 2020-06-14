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
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.Entities.Categorie;
import com.codename1.uikit.Entities.Evenement;
import com.codename1.uikit.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Baha
 */
public class ServiceEvenement {

    public ArrayList<Evenement> events;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private int nbPart;
    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addEvent(Evenement e) {
        String url;
        if (e.getImage_name() != null && !(e.getClub() > 0)) {
            url = Statics.BASE_URL + "/json/add?idevenement=" + e.getIdevenement() + ""
                    + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                    + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                    + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                    + "&club=null&imageName=" + e.getImage_name() + "&updatedAt=";
        } else if (e.getClub() > 0 && e.getImage_name() == null) {
            url = Statics.BASE_URL + "/json/add?idevenement=" + e.getIdevenement() + ""
                    + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                    + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                    + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                    + "&club=" + e.getClub() + "&imageName=null&updatedAt=";
        } else if (e.getImage_name() != null && e.getClub() > 0) {
            url = Statics.BASE_URL + "/json/add?idevenement=" + e.getIdevenement() + ""
                    + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                    + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                    + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                    + "&club=" + e.getClub() + "&imageName=" + e.getImage_name() + "&updatedAt=";
        } else {
            url = Statics.BASE_URL + "/json/add?idevenement=" + e.getIdevenement() + ""
                    + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                    + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                    + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                    + "&club=null&imageName=null&updatedAt=";
        }

        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateEvent(Evenement e) {
        String url = Statics.BASE_URL + "/json/edit?idevenement=" + e.getIdevenement() + ""
                + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                + "&club=" + e.getClub() + "&imageName=" + e.getImage_name() + "&updatedAt=";
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteEvent(Evenement e) {
        String url = Statics.BASE_URL + "/json/delete?idevenement=" + e.getIdevenement();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                System.out.println("supprimé");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Evenement> searchEvent(Evenement e) {
        String url = Statics.BASE_URL + "/json/find?idevenement=" + e.getIdevenement() + ""
                + "&titre=" + e.getTitre() + "&description=" + e.getDescription() + ""
                + "&date=" + e.getDate() + "&dateFin=" + e.getDatefin() + "&nbParticipantMax=" + e.getNbParticipantMax() + ""
                + "&lieu=" + e.getLieu() + "&frais=" + e.getFrais() + "&categorie=" + e.getCategorie() + ""
                + "&club=" + e.getClub() + "&imageName=" + e.getImage_name() + "&updatedAt=";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                e.setIdevenement((int) Float.parseFloat(obj.get("idevenement").toString()));
                
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());

                e.setDate(obj.get("date").toString());
                e.setDatefin(obj.get("dateFin").toString());

                e.setEtat((int) Float.parseFloat(obj.get("etat").toString()));
                e.setNbParticipantMax((int) Float.parseFloat(obj.get("nbParticipantMax").toString()));
                e.setFrais((int) Float.parseFloat(obj.get("frais").toString()));

                e.setLieu(obj.get("lieu").toString());

                e.setCategorie((int) Float.parseFloat(obj.get("categorie").toString()));

                if (!"null".equals(obj.get("club").toString())) {
                    e.setClub((int) Float.parseFloat(obj.get("club").toString()));
                }

                e.setImage_name(obj.get("imageName").toString());
                e.setUpdated_at(obj.get("updatedAt").toString());

                events.add(e);
            }
        } catch (IOException ex) {

        }
        return events;
    }

    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/json/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Categorie> getAllCategories() {
        String url = Statics.BASE_URL + "/json/categories";
        req.setUrl(url);
        req.setPost(false);
        ArrayList<Categorie> categories = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    String jsonText = new String(req.getResponseData());
                    Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Categorie c = new Categorie(
                                (int) Float.parseFloat(obj.get("idcategorie").toString()),
                                obj.get("libelle").toString()
                        );
                        categories.add(c);

                    }
                } catch (IOException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public List<Map<Integer, String>> getAllClubs() {
        String url = Statics.BASE_URL + "/json/clubs";
        req.setUrl(url);
        req.setPost(false);
        Map<Integer, String> clubs = new HashMap<>();
        List<Map<Integer, String>> liste = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    String jsonText = new String(req.getResponseData());
                    Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        clubs.put((int) Float.parseFloat(obj.get("id").toString()), obj.get("nom").toString());
                    }
                    liste.add(clubs);
                } catch (IOException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return liste;
    }

    public boolean participer(Evenement e, int ideleve) {
        if (nbParticipant(e) == e.getNbParticipantMax()) return false;
        String url = Statics.BASE_URL + "/json/participer?idevenement=" + e.getIdevenement() + "&ideleve=" + ideleve;
        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public int nbParticipant(Evenement e) {
        String url = Statics.BASE_URL + "/json/nbpart?idevenement=" + e.getIdevenement();
        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser j = new JSONParser();
                    String jsonText = new String(req.getResponseData());
                    Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
                    nbPart = (int) Float.parseFloat(list.get(0).get("nb").toString());
                    req.removeResponseListener(this);
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nbPart;
    }

    public boolean annulerParticipation(Evenement e, int ideleve) {
        String url = Statics.BASE_URL + "/json/annuler?idevenement=" + e.getIdevenement() + "&ideleve=" + ideleve;
        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean isParticipating(int idevent, int ideleve) {
        String url = Statics.BASE_URL + "/json/isparticipating?idevenement=" + idevent + "&ideleve=" + ideleve;
        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String jsonText = new String(req.getResponseData());
                resultOK = jsonText.equals("true");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
