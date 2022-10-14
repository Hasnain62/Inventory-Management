package com.example.cmpt370_group14.UI;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class RightBarButton extends Button
{
    RightBarButton(String text){
        super(text);
        this.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;");
        this.setTextFill(Paint.valueOf("#dedede"));
        this.setPrefSize(120, 100);
    }
}
