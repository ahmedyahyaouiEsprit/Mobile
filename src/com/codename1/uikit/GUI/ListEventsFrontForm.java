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
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.util.UIBuilder.registerCustomComponent;
import com.codename1.uikit.Entities.Evenement;
import com.codename1.uikit.Services.ServiceEvenement;
import com.codename1.uikit.cleanmodern.BaseFormEleve;
import java.util.List;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ListEventsFrontForm extends BaseFormEleve {
    Form current;
    
    public ListEventsFrontForm(Resources res) {
        super("", BoxLayout.y());

        current = this;

        setTitle("Home");
        setLayout(BoxLayout.y());

        registerCustomComponent("Picker", com.codename1.ui.spinner.Picker.class);
        TextField rech = new TextField();
        rech.setHint("Taper une clé");
        Picker date = new Picker();

        List<Evenement> events = ServiceEvenement.getInstance().getAllEvents();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Events");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("events.png"), spacer2, "  ", "", "");
        
  
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        for(Evenement e : events)
             {
            int yE = Integer.parseInt(e.getDate().substring(0, 4));
            int mE = Integer.parseInt(e.getDate().substring(5, 7));
            int dE = Integer.parseInt(e.getDate().substring(8, 10));
            
            int yN = Integer.parseInt(date.getText().substring(6, date.getText().length())) + 2000;
            int mN = Integer.parseInt(date.getText().substring(3, 5));
            int dN = Integer.parseInt(date.getText().substring(0, 2));
            
            if (e.getEtat() == 1 && (yE > yN || (yE == yN && mE > mN) || (yE == yN && mE == mN && dE >= dN)))
                addButton(e, res);
        }
            
     }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }
    
    private void addTab(Tabs swipe, Evenement e, Label spacer, Resources theme) {
        Image img;
        if (!"null".equals(e.getImage_name())) {
            String url = "http://localhost/PIDEV_integration/public/images/events";
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            URLImage imgserv = URLImage.createToStorage(enc, "test1"+ e.getIdevenement(), url + "/" + e.getImage_name());
            img = imgserv;

        } else {
            img = theme.getImage("pas_image.png");
        }
        String text = e.getTitre();
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
//        Label likes = new Label(likesStr);
//        Style heartStyle = new Style(likes.getUnselectedStyle());
//        heartStyle.setFgColor(0xff2d55);
//        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
//        likes.setIcon(heartImage);
//        likes.setTextPosition(RIGHT);

//        Label comments = new Label(commentsStr);
//        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
//        
        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(),
                                        spacer
                                )
                        )
                );
        image.addPointerReleasedListener(l-> new DetailsEventFrontForm(theme, e, this).show());
        page1.setLeadComponent(image);
        getContentPane().addPullToRefresh(() -> {
            new ListEventsFrontForm(theme).show();
        });
        swipe.addTab("", page1);
    }
    
    private void addButton(Evenement e, Resources theme) {
        Image img;
        if (!"null".equals(e.getImage_name())) {
            String url = "http://localhost/PIDEV_integration/public/images/events";
            EncodedImage enc = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            URLImage imgserv = URLImage.createToStorage(enc, "test1"+ e.getIdevenement(), url + "/" + e.getImage_name());
            img = imgserv;

        } else {
            img = theme.getImage("pas_image.png");
        }
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(e.getTitre());
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        
       //FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
        add(cnt);
        //image.addActionListener(ev -> ToastBar.showMessage(e.getTitre(), FontImage.MATERIAL_INFO));
        image.addActionListener(l -> new DetailsEventFrontForm(theme, e, current).show());
    }

    private void addButton(Image img, String title) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

//       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
//       likes.setTextPosition(RIGHT);
//       if(!liked) {
//           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//       } else {
//           Style s = new Style(likes.getUnselectedStyle());
//           s.setFgColor(0xff2d55);
//           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//           likes.setIcon(heartImage);
//       }
//       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
//       cnt.add(BorderLayout.CENTER, 
//               BoxLayout.encloseY(
//                       ta,
//                       BoxLayout.encloseX(likes, comments)
//               ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
