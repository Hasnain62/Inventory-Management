package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ProductSearchMenuView extends BasicMenu
{
    private Button searchPID, searchCategory, searchName;

    public ProductSearchMenuView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        for (int i = 0; i < 2; i++)
        {
            //set up columns
            ColumnConstraints column = new ColumnConstraints(100);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
            //set up rows
            RowConstraints row = new RowConstraints(100);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
            //add buttons
            for (int j = 0; j < 2; j++)
            {
                MenuButton button = new MenuButton();
                centralGrid.add(button, i, j);
            }
        }
        centralGrid.setHgap(25);
        centralGrid.setVgap(25);
        centralGrid.setPadding(new Insets(25));
        this.setCenter(centralGrid);
        //Buttons
        ObservableList<Node> children = centralGrid.getChildren();
        //Search by Name button
        searchName = (Button) children.get(0);
        searchName.setText("Search By Name");
        searchName.setOnAction(e -> screenControl.switchToProductSearchName());
        //Search by Category button
        searchCategory = (Button) children.get(1);
        searchCategory.setText("Search By Category");
        searchCategory.setOnAction(e -> screenControl.switchToProductSearchCategory());
        //Search by PID button
        searchPID = (Button) children.get(2);
        searchPID.setText("Search By ID");
        searchPID.setOnAction(e -> screenControl.switchToProductSearchPID());
        centralGrid.getChildren().remove(3);
    }
}
