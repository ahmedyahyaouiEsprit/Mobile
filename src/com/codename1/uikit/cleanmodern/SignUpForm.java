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

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Entities.User;
import com.codename1.uikit.Services.ServiceUser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseFormAdmin {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        ComboBox<String> roles = new ComboBox<>();
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        roles.addItem("Choisissez un role");
        roles.addItem("Parent");
        roles.addItem("Eleve");
        roles.addItem("Enseignant");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                roles
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {

            ServiceUser ser = ServiceUser.getInstance();
            if (username.getText().isEmpty()) {
                Dialog dlg = new Dialog(" saisir votre username ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (email.getText().isEmpty()) {

                Dialog dlg = new Dialog(" saisir votre adresse mail ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (password.getText().isEmpty()) {

                Dialog dlg = new Dialog("saisir votre mot de passe ");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (roles.getSelectedIndex() <= 0) {
                Dialog dlg = new Dialog("Choississez un role");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            } else if (!password.getText().equals(confirmPassword.getText())) {
                Dialog dlg = new Dialog("Mots de passe non identiques");
                Button ok = new Button(new Command("OK"));
                dlg.add(ok);
                dlg.showDialog();
                return;
            }

            User t = new User(username.getText(), password.getText(), email.getText());
            if (roles.getSelectedItem().equals("Parent")) {
                t.setRole("ROLE_PARENT");
            } //else if (roles.getSelectedItem().equals("Eleve")) {
               // t.setRole("ROLE_ELEVE");
           // } 
                else if (roles.getSelectedItem().equals("Enseignant")) {
                t.setRole("ROLE_ENSEIGNANT");
            }

            System.out.println("a" + t.toString());

            ser.ajoutUser(t);
            Button ok = new Button(new Command("OK"));

            Dialog dlg = new Dialog("Votre compte a ete cree" + " " + t.getUsername());

            TextArea taa = new TextArea("Bienvenue  ");
            taa.setEditable(false);
            taa.setUIID("DialogBody");
            taa.getAllStyles().setFgColor(0);
            dlg.add(taa);

            ok.getAllStyles().setBorder(Border.createEmpty());
            ok.getAllStyles().setFgColor(0);
            ok.getUnselectedStyle().setFgColor(100000);

            dlg.add(ok);
            dlg.showDialog();
            
            new SignInForm(res).show();
        });
    }

}
