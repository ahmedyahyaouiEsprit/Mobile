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
package com.codename1.uikit.cleanmodern;

import com.codename1.uikit.GUI.ListEventsForm;
import com.codename1.components.FloatingHint;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.GUI.ListEventsFrontForm;
import com.codename1.uikit.GUI.modules;
import com.codename1.uikit.Utils.SessionManager;
import com.codename1.uikit.Utils.Statics;
import java.io.IOException;
import java.util.Map;
import com.codename1.ui.Form;
import com.codename1.uikit.GUI.AddReclamationForm;
import com.codename1.uikit.Services.ServiceUser;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseFormAdmin {

    ConnectionRequest connectionRequest;
   public static int UID;    

    public SignInForm(Resources res) {
        
        super(new BorderLayout());

       
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("logo-2.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        username.setNextFocusDown(password);
        password.setSingleLineTextArea(false);
        
        Button signIn = new Button("Sign In");
        password.setNextFocusDown(signIn);
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();

        signIn.addActionListener(e -> {
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                String url = Statics.BASE_URL + "/api/login";
                connectionRequest = new ConnectionRequest(url, false);
                connectionRequest.addArgument("username", username.getText());
                connectionRequest.addArgument("password", password.getText());
                connectionRequest.addResponseListener((action) -> {
                    try {

                        JSONParser j = new JSONParser();
                        String json = new String(connectionRequest.getResponseData()) + "";
                        if (json.equals("failed")) {
                            Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                        } else {

                            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            float id = Float.parseFloat(user.get("id").toString());
                         
                            UID=(int) id;
                            SessionManager.setId((int) id);
                            SessionManager.setPass(password.getText());

                            if (user.size() > 0) {
                                SessionManager.setEmail(user.get("email").toString());
                                SessionManager.setPassword(password.getText());
                                SessionManager.setUserName(user.get("username").toString());
                                System.out.println("done");
                               
                                   new modules(res).show();
                                   
                                   
                                
                               
                               //else if (user.get("roles").toString().contains("ROLE_PARENT")) {
                                    //new ListEventsFrontForm(res).show();
                                   // System.out.println("je suis parent");
                               // }
                            }
                        }
                    } catch (IOException ex) {
                    }
                });
            } else {
                Dialog.show("Erreur", "Saisissez votre username et votre mot de passe", "ok", null);
                return;
            }
            NetworkManager.getInstance().addToQueue(connectionRequest);
        }
        );
    }
}
