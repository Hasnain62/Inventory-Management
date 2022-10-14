package com.example.cmpt370_group14.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Icon extends ImageView
{
    Icon(String name){
        super(new Image(new File("src/main/icons/" + name +".png").toURI().toString()));
        this.setFitWidth(50);
        this.setFitHeight(50);
    }
}
