/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.GUI;

import com.codename1.components.InfiniteProgress;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.util.UIBuilder.registerCustomComponent;
import com.codename1.uikit.Entities.Categorie;
import com.codename1.uikit.Entities.Evenement;
import com.codename1.uikit.Services.ServiceEvenement;
import com.codename1.uikit.Utils.Statics;
import com.codename1.uikit.cleanmodern.BaseFormAdmin;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class EditEventForm extends BaseFormAdmin {

    Form current;
    private static final String HTML_API_KEY = "AIzaSyBafoSXzjj4oTxrXb-Xq9ZDIp9KjsIch4I";
    MultipartRequest requete = new MultipartRequest();

    public EditEventForm(Resources theme, Evenement e) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", ev -> previous.showBack());
        setUIID("SignIn");

        registerCustomComponent("Picker", com.codename1.ui.spinner.Picker.class);
        registerCustomComponent("NumericSpinner", com.codename1.ui.spinner.NumericSpinner.class);
        registerCustomComponent("ImageViewer", com.codename1.components.ImageViewer.class);

        List<Categorie> allCategories = ServiceEvenement.getInstance().getAllCategories();
        List<Map<Integer, String>> allClubs = ServiceEvenement.getInstance().getAllClubs();

        Container cnt1 = new Container(BoxLayout.yCenter());

        TextField ttitre = new TextField(e.getTitre(), "Titre", 20, TextField.ANY);
        ttitre.setUIID("");
        //ttitre.setText(e.getTitre());
        TextField tdesc = new TextField(e.getDescription(), "Description", 20, TextField.ANY);
        tdesc.setSingleLineTextArea(false);
        tdesc.setUIID("");
        //tdesc.setText(e.getDescription());
        cnt1.addAll(ttitre, tdesc);
        Form F1 = new Form(BorderLayout.absolute());
        add(CENTER, cnt1);
        Button suivant1 = new Button("Suivant");
        add(BOTTOM, suivant1);

        Container cnt2 = new Container(BoxLayout.yCenter());
        Label ldate = new Label("Date de début");
        Picker dateDeb = new Picker();
        dateDeb.setUIID("");
        int yE = Integer.parseInt(e.getDate().substring(0, 4));
        int mE = Integer.parseInt(e.getDate().substring(5, 7));
        int dE = Integer.parseInt(e.getDate().substring(8, 10));
        dateDeb.setText(Evenement.format(e.getDate()));
        Label ldateFin = new Label("Date de Fin");
        Picker dateFin = new Picker();
        dateFin.setUIID("");
        dateFin.setText(Evenement.format(e.getDatefin()));
        cnt2.addAll(ldate, dateDeb, ldateFin, dateFin);
        Form F2 = new Form("Modification de l'événement", BorderLayout.absolute());
        F2.getTitleArea().setUIID("Container");
        F2.setUIID("SignIn");
        F2.add(CENTER, cnt2);
        Button suivant2 = new Button("Suivant");
        suivant1.addActionListener(l1 -> {
            if (ttitre.getText().isEmpty() || !Evenement.isValidTitre(ttitre.getText())) {
                Dialog.show("Titre invalide",
                        " Le titre doit commencer par une lettre majuscule et ne doit contenir que des alphabets", "Ok", null);
            } else if (tdesc.getText().isEmpty() || !Evenement.isValidDescription(tdesc.getText())) {
                Dialog.show("Desription invalide",
                        " La description doit commencer par une lettre majuscule et ne doit pas être vide", "Ok", null);
            } else {
                e.setTitre(ttitre.getText());
                e.setDescription(tdesc.getText());
                removeAll();
                F2.show();
            }
        });
        F2.add(BOTTOM, suivant2);

        Container cnt3 = new Container(BoxLayout.yCenter());
        Label lblieu = new Label("Veuillez choisir le lieu");
        TextField tlieu = new TextField();
        tlieu.setUIID("");
        tlieu.setText(e.getLieu());
        cnt3.addAll(lblieu, tlieu);
        Form F3 = new Form("Modification de l'événement", BorderLayout.absolute());
        F3.getTitleArea().setUIID("Container");
        F3.setUIID("SignIn");
        F3.add(TOP, cnt3);
        Button suivant3 = new Button("Suivant");
        suivant2.addActionListener(l2 -> {
            if (!Evenement.isValidDateDebAdd(dateDeb.getText())) {
                Dialog.show("Date début invalide",
                        "La date de début de l'événement doit être supérieure à la date d'aujourdhui", "Ok", null);
            } else if (!Evenement.isValideDateFinAdd(dateDeb.getText(), dateFin.getText())) {
                Dialog.show("Date Fin invalide",
                        "La date de fin de l'événement doit être supérieure ou égale à la date de début", "Ok", null);
            } else {
                e.setDate(dateDeb.getDate().toString());
                e.setDatefin(dateFin.getDate().toString());
                removeAll();
                F3.show();
            }
        });
        ComboBox<String> places = new ComboBox<>();
        cnt3.add(places);
        F3.add(BOTTOM, suivant3);

        Container cnt4 = new Container(BoxLayout.yCenter());
        TextField nbPart = new TextField();
        nbPart.setUIID("");
        nbPart.setHint("Nombre de participants");
        nbPart.setText(String.valueOf(e.getNbParticipantMax()));
        TextField frais = new TextField();
        frais.setHint("Frais");
        frais.setUIID("");
        frais.setText(String.valueOf(e.getFrais()));
        ComboBox<String> categories = new ComboBox<>();

        for (Categorie c : allCategories) {
            categories.addItem(c.getLibelle());
        }

        for (Categorie c : allCategories) {
            if (e.getCategorie() == c.getIdcategorie()) {
                categories.setSelectedItem(c.getLibelle());
                break;
            }
        }

        ComboBox<String> clubs = new ComboBox<>();
        clubs.addItem("Club");

        for (Map<Integer, String> c : allClubs) {
            Iterator iterator = c.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapentry = (Map.Entry) iterator.next();
                clubs.addItem((String) mapentry.getValue());
            }

        }

        if (e.getClub() > 0) {
            for (Map<Integer, String> c : allClubs) {
                Iterator iterator = c.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) iterator.next();
                    if ((int) mapentry.getKey() == e.getClub()) {
                        clubs.setSelectedItem(mapentry.getValue().toString());
                    }
                }
            }
        }

        cnt4.addAll(nbPart, frais, categories, clubs);
        Form F4 = new Form("Modification de l'événement", BorderLayout.absolute());
        F4.getTitleArea().setUIID("Container");
        F4.setUIID("SignIn");
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        Location location = LocationManager.getLocationManager().getCurrentLocationSync();
        cnt.setCameraPosition(new Coord(location.getLatitude(), location.getLongitude()));

        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(ev -> {
            cnt.setCameraPosition(new Coord(36.901008, 10.190341));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        Button btnAddMarker = new Button("Chercher dans ce rayon");
        btnAddMarker.addActionListener(ev -> {
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?query="
                    + "" + "&location=" + cnt.getCoordAtPosition(ev.getX(), ev.getY()).getLatitude() + ","
                    + "" + cnt.getCoordAtPosition(ev.getX(), ev.getY()).getLongitude() + "&radius=100&key=" + HTML_API_KEY;
            ConnectionRequest req = new ConnectionRequest();
            req.setUrl(url);
            req.setPost(false);
            System.out.println(url);
            req.addResponseListener(a -> {
                try {
                    try {
                        String reponse = new String(req.getResponseData());
                        JSONParser j = new JSONParser();
                        Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(reponse.toCharArray()));
                        System.out.println(reponse);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("results");
                        for (Map<String, Object> obj : list) {
                            places.addItem(obj.get("name").toString());
                        }

                        tlieu.setText(list.get(0).get("name").toString());
                        places.addActionListener(l -> tlieu.setText(places.getSelectedItem()));
                    } catch (IOException ex) {
                    }
                } catch (IndexOutOfBoundsException IOB) {
                    Dialog.show("Zoomer", "Zoomer encore", "ok", null);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker)
                )
        );

        F3.add(BorderLayout.CENTER, root);

        F4.add(TOP, cnt4);
        Button suivant4 = new Button("Suivant");
        suivant3.addActionListener(l3 -> {
            if (tlieu.getText().isEmpty()) {
                Dialog.show("Lieu invalide",
                        " Le Lieu ne doit pas être vide", "Ok", null);
            } else {
                e.setLieu(tlieu.getText());
                removeAll();
                F4.show();
            }
        });
        F4.add(BOTTOM, suivant4);

        Container cnt5 = new Container(BoxLayout.y());
        Button img = new Button("choisir une image");
        Button save = new Button("Modifier");
        cnt5.add(img);
        InfiniteProgress iprogess = new InfiniteProgress();
        Dialog progress = iprogess.showInfiniteBlocking();
        ActionListener callback = ev -> {
            save.setEnabled(false);
            if (ev != null && ev.getSource() != null) {
                String filePath1 = ev.getSource().toString();
                System.out.println(filePath1);
                if (Dialog.show("Confirmation", "Vous voulez ajouter cette image à l'événement ?", "Oui", "Non")) {

                    requete.setUrl(Statics.BASE_URL + "/json/upload");
                    System.out.println(requete.getUrl());
                    requete.setPost(true);
                    try {

                        requete.addData("image", filePath1, "image/png");
                        requete.addResponseListener(a -> {
                            try {
                                String reponse = new String(requete.getResponseData());
                                System.out.println(reponse);
                                JSONParser j = new JSONParser();
                                Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(reponse.toCharArray()));
                                List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
                                e.setImage_name(list.get(0).get("file_name").toString());
                                System.out.println(e.getImage_name());
                            } catch (IOException ex) {

                            }
                        });

                    } catch (IOException exep) {
                        Dialog.show("Erreur", exep.getMessage(), "Ok", null);
                    }
                }
                save.setEnabled(true);
            }
        };

        img.addActionListener(ev -> {
            save.setEnabled(false);
            Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
        });

        Form F5 = new Form("Modification de l'événement", BorderLayout.absolute());
        F5.getTitleArea().setUIID("Container");
        F5.setUIID("SignIn");

        F5.add(CENTER, cnt5);
        suivant4.addActionListener(l4 -> {
            if (nbPart.getText().isEmpty() || !Evenement.isNumeric(nbPart.getText()) || !Evenement.isValidNbPart(Integer.parseInt(nbPart.getText()))) {
                Dialog.show("Nombre invalide",
                        " Le nombre de participants maximal doit etre un nombre positif", "Ok", null);
            } else if (frais.getText().isEmpty() || !Evenement.isNumeric(frais.getText()) || !Evenement.isValidFrais(Integer.parseInt(frais.getText()))) {
                Dialog.show("Frais invalide",
                        " Les frais doivent être supérieur ou égale à 0", "Ok", null);
            }
            else {
                e.setNbParticipantMax(Integer.parseInt(nbPart.getText()));
                e.setFrais(Integer.parseInt(frais.getText()));
                int catId = 1;
                for (Categorie c : allCategories) {
                    if (categories.getSelectedItem().equals(c.getLibelle())) {
                        catId = c.getIdcategorie();
                        break;
                    }
                }

                e.setCategorie(catId);
                int clubId = 0;
                if (clubs.getSelectedIndex() > 0) {
                    for (Map<Integer, String> c : allClubs) {
                        Iterator iterator = c.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry mapentry = (Map.Entry) iterator.next();
                            if (mapentry.getValue().equals(clubs.getSelectedItem())) {
                                clubId = (int) mapentry.getKey();
                                break;
                            }

                        }
                    }
                }
                e.setClub(clubId);
                removeAll();
                F5.show();
            }
        });
        F5.add(BOTTOM, save);
        save.addActionListener(l5 -> {
            if (requete.getUrl() != null) {
                requete.setDisposeOnCompletion(progress);
                NetworkManager.getInstance().addToQueueAndWait(requete);
            }
            if (ServiceEvenement.getInstance().updateEvent(e)) {
                Dialog.show("Modifié avec succés", "Cet événement à été modifié", "ok", null);
                new DetailsEventForm(theme, e, this).show();
            }
        });
    }

}
