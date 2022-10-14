package com.example.cmpt370_group14.UI;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class MenuButton extends Button
{
    MenuButton(){
        super();
        this.setStyle("-fx-background-color: #1e74c9;-fx-font: 36 system;");
        this.setTextFill(Paint.valueOf("#ffffff"));
        this.setPrefSize(10000, 10000);
    }
}
