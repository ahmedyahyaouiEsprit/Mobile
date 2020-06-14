/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.GUI;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Entities.Livres;

/**
 *
 * @author Sony
 */
    public abstract class SideMenuBaseForm extends Form {


    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
       

        Container sidemenuTop = new Container(BoxLayout.y());
        sidemenuTop.setUIID("SidemenuTop");
        getToolbar().addComponentToSideMenu(sidemenuTop);
      
           
            
            getToolbar().addMaterialCommandToSideMenu("  Bibiothéque ", FontImage.MATERIAL_BOOK, e -> {
                new AfficherLivres(res).show();
                 
                
            });
            getToolbar().addMaterialCommandToSideMenu("  Mes Empreintes", FontImage.MATERIAL_BOOK, e -> {
                new ShowResBooks(res).show();
               
            });
           
    }

}

