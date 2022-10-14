package com.example.cmpt370_group14;

import com.example.cmpt370_group14.UI.ScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException
    {
        Controller control = new Controller();
        ScreenController screenControl = new ScreenController(control, primaryStage);
        primaryStage.show();

    }
}
