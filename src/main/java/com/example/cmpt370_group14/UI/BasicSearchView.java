package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

//Abstract class for all windows that search for some object in the database
public abstract class BasicSearchView extends BasicMenu
{
    protected ListView items;
    protected TextField searchBar;
    protected Button searchButton;

    public BasicSearchView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        //set up center view

        for(int i = 0; i < 2; i ++){
            ColumnConstraints column = new ColumnConstraints(100);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
            //set up rows
            RowConstraints row = new RowConstraints(40);
            if(i == 0){
                row.setVgrow(Priority.NEVER);
            }
            else{
                row.setVgrow(Priority.ALWAYS);
            }
            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
        }
        //Set up search bar and button
        HBox searchBox = new HBox();
        searchBar = new MyTextField();
        searchButton = new Button("");
        searchButton.setStyle("-fx-background-color: #1e74c9;-fx-font: 18 system;-fx-text-fill: #ffffff");
        Icon searchIcon = new Icon("search_icon");
        searchIcon.setFitHeight(20);
        searchIcon.setFitWidth(20);
        searchButton.setGraphic(searchIcon);
        searchButton.setPrefSize(30, 30);
        searchBox.getChildren().addAll(searchBar, searchButton);
        centralGrid.add(searchBox, 0, 0);
        items = new ListView();
        items.setStyle("-fx-background-color: #2a2d33;-fx-text-fill: #dedede");
        centralGrid.add(items, 0, 1);
        this.setCenter(centralGrid);
    }


}
