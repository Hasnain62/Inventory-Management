package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class LoginView extends StackPane
{
    private Controller controller;
    private ScreenController screenController;
    private MyTextField usernameField;
    private Button logInButton;
    private PasswordField passwordField;

    LoginView(Controller controller, ScreenController screenController)
    {
        this.controller = controller;
        this.screenController = screenController;
        this.setStyle("-fx-background-color: #2a2d33;");
        this.setPrefSize(750, 400);
        logInButton = new Button("Log In");
        logInButton.setStyle("-fx-background-color: #1e74c9;-fx-font: 18 system;");
        logInButton.setTextFill(Paint.valueOf("#ffffff"));
        Label username = new Label("Username ");
        Label password = new Label("Password ");
        usernameField = new MyTextField();
        passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;-fx-text-fill: #ffffff");
        passwordField.setAlignment(Pos.CENTER_LEFT);
        VBox labelBox = new VBox(username, password);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPadding(new Insets(10));
        labelBox.setSpacing(15);
        username.setTextFill(Paint.valueOf("#dedede"));
        username.setStyle("-fx-font: 18 system;");
        password.setTextFill(Paint.valueOf("#dedede"));
        password.setStyle("-fx-font: 18 system;");
        VBox textfieldBox = new VBox(usernameField, passwordField);
        textfieldBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(labelBox, textfieldBox, logInButton);
        this.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        logInButton.setOnAction(e->{
            try
            {
                controller.signIn(usernameField.getText(), passwordField.getText());
                screenController.switchToMainMenu();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        passwordField.setOnKeyPressed(e->
        {
            try
            {
                controller.handleKeyPressedEvent(e, usernameField.getText(), passwordField.getText(), this);
                if(controller.getCurrentUser() != null){
                    screenController.switchToMainMenu();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        usernameField.setOnKeyPressed(e->
        {
            try
            {
                controller.handleKeyPressedEvent(e, usernameField.getText(), passwordField.getText(), this);
                if(controller.getCurrentUser() != null){
                    screenController.switchToMainMenu();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });

    }
}
