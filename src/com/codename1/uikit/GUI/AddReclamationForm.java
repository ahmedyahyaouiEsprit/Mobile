/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.GUI;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Entities.Reclamationp;
import com.codename1.uikit.Services.ServiceReclamation;
import com.codename1.uikit.Services.ServiceUser;
import com.codename1.uikit.cleanmodern.EspaceParentForm;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author HP
 */
public class AddReclamationForm extends EspaceParentForm
{
    
  
    Form current;
    public AddReclamationForm(Resources theme){
        super(new BorderLayout());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        Reclamationp rec = new Reclamationp();

        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        List<Reclamationp> allReclamationp = ServiceReclamation.getInstance().getAllReclamations();
       
      TextArea tfContenu = new TextArea("Contenu");
        Button btnValider = new Button("Ajouter");
//        add(tfContenu);
        //add(btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Reclamationp rec=new Reclamationp(tfContenu.getText());
                if(tfContenu.getText()==null){
                    Dialog.show("Alert", "SVP remplir tous les champs", new Command("OK"));
                }else if(ServiceReclamation.getInstance().AddRec(rec))
                    Dialog.show("Ajouté avec succés", "Cet événement à été ajouté", "ok", null);
                System.out.println("c booonnnn");
                new ListEventsForm(theme).show();
            }
        });
        
        
        
    
    
   
    
    }
}
