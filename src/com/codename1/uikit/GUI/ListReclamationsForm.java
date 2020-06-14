/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.Entities.Reclamationp;
import com.codename1.uikit.Services.ServiceReclamation;
import com.codename1.uikit.cleanmodern.EspaceParentForm;
import java.util.List;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class ListReclamationsForm extends EspaceParentForm{
    Form current ;
    public ListReclamationsForm(Resources res){
    super("", BoxLayout.y());
    current = this;
    List<Reclamationp> reclamationp = ServiceReclamation.getInstance().getAllReclamations();
    
    Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Events");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
    }
        
    
    
}
    
    
    

