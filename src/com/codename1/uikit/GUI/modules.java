/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseFormAdmin;

/**
 *
 * @author Sony
 */
public class modules extends BaseFormAdmin {
    
    public modules (Resources res){
        
        
        
        
        
        
        
        
      
            super(new LayeredLayout());
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
     //   setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs modulesC = new Tabs();
        modulesC.setUIID("Container");
        modulesC.getContentPane().setUIID("Container");
        modulesC.getTabsContainer().setUIID("Container");
        modulesC.hideTabs();
        
        
        
        Image elearning = res.getImage("learning.png");
       
        
        Label elearningPlaceholder = new Label("","ProfilePic");
        Label elearningLabel = new Label(elearning, "ProfilePic");
        Component.setSameHeight(elearningLabel, elearningPlaceholder);
        Component.setSameWidth(elearningLabel, elearningPlaceholder);
       
        
       Button ToElearning = new Button("iBooks");
        ToElearning.setUIID("ToElearning");
        ToElearning.addActionListener(e -> new AfficherLivres(res).show());
        
       
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(elearningPlaceholder,ToElearning));
           
        tab1.setUIID("modulesTab1");
        
        
        Image livres = res.getImage("book.png");
        Label livresPlaceholder = new Label("","ProfilePic");
        Label livresLabel = new Label(livres, "ProfilePic");
        Component.setSameHeight(livresLabel, livresPlaceholder);
        Component.setSameWidth(livresLabel, livresPlaceholder);
        
        
//        ToBooks.setUIID("ToBooks");
        ToElearning.addActionListener(e -> new AfficherLivres(res).show());
        
      //  Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(livresPlaceholder,ToBooks));
        //tab2.setUIID("modulesTab2");

        this.addAll(tab1);

        
        
        // visual effects in the first show
        addShowListener(e -> {
            elearningPlaceholder.getParent().replace(elearningPlaceholder, elearningLabel, CommonTransitions.createFade(1500));
        });
        
    }    

    
    
}
