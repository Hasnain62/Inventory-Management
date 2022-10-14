package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//Abstract class that all scenes are based on
public abstract class BasicMenu extends BorderPane
{
    protected GridPane topBar, centralGrid;
    protected ToolBar rightBar;
    protected Controller controller;
    protected ScreenController screenController;
    private Label userName, date;
    private Button home, logOut, back;
    public BasicMenu(Controller controller, ScreenController screenControl){
        centralGrid = new GridPane();
        this.setCenter(centralGrid);
        this.controller = controller;
        screenController = screenControl;
        this.setStyle("-fx-background-color: #2a2d33;");
        this.setPrefSize(750, 400);
        //top menu bar
        topBar = new GridPane();
        topBar.setStyle("-fx-background-color: #41464f;");
        topBar.setPrefSize(615, 50);
        for(int i = 0; i < 4; i++){
            ColumnConstraints column = new ColumnConstraints(100);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            topBar.getColumnConstraints().add(column);
        }
        topBar.addRow(0);
        this.setTop(topBar);
        //Username
        userName = new Label("   " + controller.getuserName());
        userName.setTextFill(Paint.valueOf("#dedede"));
        userName.setStyle("-fx-font: 18 system;");
        userName.setAlignment(Pos.CENTER_LEFT);
        userName.setPrefSize(100, 100);
        //logout button
        logOut = new Button("Log Out");
        logOut.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;");
        logOut.setTextFill(Paint.valueOf("dedede"));
        logOut.setPrefSize(70, 50);
        logOut.setOnAction(e->{
            controller.signOut();
            screenController.switchToLoginView();
        });
        HBox userBox = new HBox(logOut, userName);
        topBar.add(userBox, 0, 0);
        //date
        date = new Label(new SimpleDateFormat("dd/MM/yyy").format(Calendar.getInstance().getTime()));
        date.setTextFill(Paint.valueOf("#dedede"));
        date.setStyle("-fx-font: 18 system;");
        date.setAlignment(Pos.CENTER_LEFT);
        date.setPrefSize(300, 50);
        topBar.add(date, 1, 0);
        ////////////////////////////////////////////////////////////////////////////////////
        //right toolbar
        rightBar = new ToolBar();
        rightBar.setOrientation(Orientation.VERTICAL);
        rightBar.setStyle("-fx-background-color: #41464f;");
        rightBar.setPrefWidth(130);
        //Home button
        home = new RightBarButton("Home");
        home.setContentDisplay(ContentDisplay.TOP);
        home.setGraphic(new Icon("home_icon"));
        home.setOnAction(e-> screenControl.switchToMainMenu());
        //Back button
        back = new RightBarButton("Back");
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(new Icon("back_icon"));
        back.setOnAction(e->screenController.goBack());
        rightBar.getItems().addAll(home, back);
        this.setRight(rightBar);
        //Elements that exist on the top menu bar
    }


    //Creates toolbar on right side of window
    //Input: an arraylist of buttons that are put into the toolbar
    protected void addRightBarButtons(ArrayList<Button> buttons){
        rightBar.getItems().remove(home);
        rightBar.getItems().remove(back);
        rightBar.getItems().addAll(buttons);
        rightBar.getItems().addAll(home, back);
    }

}
