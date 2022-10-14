package com.example.cmpt370_group14.UI;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class MyTextField extends TextField
{
    MyTextField(){
        this.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;-fx-text-fill: #ffffff");
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMaxWidth(700);
        this.setMinWidth(400);
    }
}
