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
import com.codename1.uikit.Entities.Reclamationp;
import com.codename1.uikit.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceReclamation {
    public ArrayList<Reclamationp> reclamationp;
    public static ServiceReclamation instance = null;
    public boolean ResultOK;
    private ConnectionRequest req ;
    
    private ServiceReclamation(){
        req = new ConnectionRequest();
    }
    public static ServiceReclamation getInstance() {
    if(instance == null){
   instance = new ServiceReclamation();
    }
    return instance;
    }
    
    public ArrayList<Reclamationp> parseReclamationp(String jsonText){
    
        reclamationp = new ArrayList<>();
        JSONParser j = new JSONParser();
        try {
        Map<String, Object> reclamationlist = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationlist.get("root");
        for (Map<String , Object> obj : list) {
        Reclamationp r = new Reclamationp();
        float id = Float.parseFloat(obj.get("id").toString());
        //t.setId((int)id);
        r.setId((int)id);
        float parentId = Float.parseFloat(obj.get("parent").toString());
        r.setParent((int)id);
        r.setMessage(obj.get("message").toString());
        reclamationp.add(r);
        }
    }
        catch (IOException ex) {
    }
        return reclamationp;
    }
    
    public ArrayList<Reclamationp> getAllReclamations() {
    String url = Statics.BASE_URL + "/api/reclamation";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reclamationp = parseReclamationp(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamationp;
    }
    public boolean AddRec(Reclamationp r){
    String url = Statics.BASE_URL+"/api/Addreclamation?id="+r.getId()+"$message"+r.getMessage()+"$idparent"+r.getParent();
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            ResultOK=req.getResponseCode() == 200 ;
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return ResultOK;
    }
}