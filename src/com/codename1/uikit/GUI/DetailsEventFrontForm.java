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

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Entities.Categorie;
import com.codename1.uikit.Entities.Evenement;
import com.codename1.uikit.Services.ServiceEvenement;
import com.codename1.uikit.Utils.SessionManager;
import com.codename1.uikit.cleanmodern.BaseFormEleve;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class DetailsEventFrontForm extends BaseFormEleve {

    List<Categorie> allCategories = ServiceEvenement.getInstance().getAllCategories();
    List<Map<Integer, String>> allClubs = ServiceEvenement.getInstance().getAllClubs();

    public DetailsEventFrontForm(Resources theme, Evenement e, Form previous) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(theme);

        tb.addSearchCommand(l -> {
        });

        Image img;
        if (!"null".equals(e.getImage_name())) {
            String url = "http://localhost/PIDEV_integration/public/images/events";
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            URLImage imgserv = URLImage.createToStorage(enc, "test1" + e.getIdevenement(), url + "/" + e.getImage_name());
            img = imgserv;

        } else {
            img = theme.getImage("pas_image.png");
        }
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        add(LayeredLayout.encloseIn(sl));

        Label titre = new Label(e.getTitre());
        titre.setUIID("Form");
        add(titre);

        SpanLabel desc = new SpanLabel();
        desc.setText(e.getDescription());
        desc.setUIID("Form");
        add(desc);

        TextField dateDeb = new TextField(e.getDate());
        dateDeb.setEditable(false);
        dateDeb.setUIID("TextFieldBlack");
        addStringValue("Date début", dateDeb);

        TextField dateFin = new TextField(e.getDatefin());
        dateFin.setEditable(false);
        dateFin.setUIID("TextFieldBlack");
        addStringValue("Date Fin", dateFin);

        TextField frais = new TextField(String.valueOf(e.getFrais()));
        frais.setEditable(false);
        frais.setUIID("TextFieldBlack");
        addStringValue("Frais", frais);

        TextField nbPart = new TextField(String.valueOf(e.getNbParticipantMax()));
        nbPart.setEditable(false);
        nbPart.setUIID("TextFieldBlack");
        addStringValue("Nombre Max participants", nbPart);

        TextField lieu = new TextField(e.getLieu());
        lieu.setEditable(false);
        lieu.setUIID("TextFieldBlack");
        addStringValue("Lieu", lieu);

        String categ = null;
        for (Categorie c : allCategories) {
            if (e.getCategorie() == c.getIdcategorie()) {
                categ = c.getLibelle();
                break;
            }
        }
        TextField cat = new TextField(categ);
        cat.setEditable(false);
        cat.setUIID("TextFieldBlack");
        addStringValue("Catégorie", cat);

        String clubL = null;
        if (e.getClub() > 0) {
            for (Map<Integer, String> c : allClubs) {
                Iterator iterator = c.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) iterator.next();
                    if ((int) mapentry.getKey() == e.getClub()) {
                        clubL = (String) mapentry.getValue();
                        break;
                    }
                }
            }
        }
        if (e.getClub() > 0) {
            TextField club = new TextField(clubL);
            club.setEditable(false);
            club.setUIID("TextFieldBlack");
            addStringValue("Club", club);
        }
        Button participer = new Button("Participer");
        Button annuler = new Button("Annuler participation");
        if (!ServiceEvenement.getInstance().isParticipating(e.getIdevenement(), SessionManager.getId())) {
            add(participer);
        } else {
            add(annuler);
        }

        participer.addActionListener(l -> {
            ServiceEvenement.getInstance().participer(e, SessionManager.getId());
            participer.remove();
            add(annuler);
        });

        annuler.addActionListener(l -> {
            ServiceEvenement.getInstance().annulerParticipation(e, SessionManager.getId());
            annuler.remove();
            add(participer);
        });
        getContentPane().addPullToRefresh(() -> {
            new DetailsEventFrontForm(theme, e, previous).show();
        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
