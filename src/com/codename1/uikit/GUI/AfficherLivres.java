/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Entities.Livres;
import com.codename1.uikit.Entities.ReservationLivre;
import com.codename1.uikit.Services.ReservationService;
import com.codename1.uikit.Services.ServiceUser;
import com.codename1.uikit.Services.ServicesLivres;
import com.codename1.uikit.cleanmodern.BaseFormAdmin;
import com.codename1.uikit.cleanmodern.CleanModern;
import static com.codename1.uikit.cleanmodern.SignInForm.UID;
import java.util.ArrayList;

/**
 *
 * @author Sony
 */
public class AfficherLivres extends SideMenuBaseForm {
    
    Livres r = new Livres();
    TextArea tn;
    TextArea ts;
    TextArea tt;
    Form f = new Form("Liste des Livres");
    
    public AfficherLivres(Resources res) {
        
        super(BoxLayout.y());
        Toolbar.setGlobalToolbar(true);
        Toolbar tb = getToolbar();

        Button menuButton = new Button();
        setupSideMenu(res);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container titleComponent = BorderLayout.north(BorderLayout.west(menuButton));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        
       //  f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ProfileForm(res).show());
       
         
        
        //f.add(lb);
        Button up = new Button();
        int height = Display.getInstance().convertToPixels(65f);
        int width = Display.getInstance().convertToPixels(70f);
        ServicesLivres ser = new ServicesLivres();
        //  lb.setText(ser.rechercheSkill().toString());
        ArrayList<Livres> l = ser.AfficheLivre();
        System.out.println("lllllllll"+l);
        for (int i = 0; i < l.size(); i++) {
           
            System.out.println("llllllllllllllll" + l);
            
            r = l.get(i);

            Container c = new Container(BoxLayout.y());
            
            //c.getStyle().setBorder(Border.createLineBorder(1));
            
            ImageViewer image = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(CleanModern.theme.getImage("book.png"), false);
            image.setImage(URLImage.createToStorage(placeholder, "http://localhost/iSchool3/web/images/" +r.getImage() ,"http://localhost/iSchool3/web/images/" +r.getImage()).scaled(height, width));
            System.out.println("http://localhost/iSchool3/web/images/" +r.getImage());
            Container c1 = new Container(BoxLayout.x());
            tn = new TextArea("Nom");
            Label l1 = new Label(r.getNom());
            tn.setUIID("TextAreaBlack");
            c1.add(tn);
            c1.add(l1);

            Container c2 = new Container(BoxLayout.x());
            ts = new TextArea("Auteur");
            Label l2 = new Label(r.getAuteur());
            ts.setUIID("TextAreaBlack");
            c2.add(ts);
            c2.add(l2);

            Container c3 = new Container(BoxLayout.x());
            tt = new TextArea("Description");
            Label l3 = new Label(" " + r.getDescription());
            tt.setUIID("TextAreaBlack");
            
            ShareButton fbbutton = new ShareButton();
            fbbutton.setTextToShare(r.getNom());
            
        Button Reserver = new Button("");
        
        Livres e = new Livres();
        ReservationLivre ae = new ReservationLivre(UID, r.getId());
        ReservationService aes=new ReservationService();
      
        Label ress = new Label(r.getQuantite()+" disponibles ");
        
        if (aes.findEmp(ae)) {
            Reserver.setText("Rendre");
            
        } else {
            Reserver.setText("Empreinter");
            
        }            
        
        int qte = r.getQuantite();
        Reserver.addActionListener((ActionEvent ev) -> {
            if (Reserver.getText().equals("Empreinter")) {
                
                System.out.println("is s7i7?????? "+r.getId());
                Reserver.setText("Rendre");
                aes.empreinter(ae);              
                System.out.println("empreinte");
                ress.setText(qte +" disponibles ");
                System.out.println(qte);
                //API.SMS.create( "AC1fd3d1ed6923bd59959018c3fde2638c", "4a2c4f676e47f9034f44397ce62bed00", "+2169079827").sendSmsSync(userC.getPhone(), "2169079827", "cc bb");
                
                new AfficherLivres(res).show();
            } else {
                Reserver.setText("Empreinter");
                aes.rendre(ae);                
                ress.setText(qte +" disponibles ");
                System.out.println("renduuu");
                System.out.println(qte);
                new AfficherLivres(res).show();
            }        
        
        });

        
        
        //like.setText(r.getQuantite()+ " disponibles");
            System.out.println(r.getQuantite());
            c3.add(tt);
            c3.add(l3);

            c.add(image);                    
            c.add(c1);
            c.add(c2);
            c.add(c3);
           // c.add(Aime);
           c.add(Reserver);
            c.add(ress);
            c.add(fbbutton);
           
            add(c);
            
            

            f.show();
          
     }
                        
       
    }
    

}
