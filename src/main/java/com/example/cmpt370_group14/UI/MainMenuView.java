package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.SQLException;
import java.text.ParseException;

//Layout for main menu
public class MainMenuView extends BasicMenu
{
    private Button salesB, receiptsB, staffB, notificationsB, receiveInventory, searchInventory, addExpiration, createProd;;
    public MainMenuView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        //grid
        GridPane buttonGrid = new GridPane();
        GridPane topGrid = new GridPane();
        for(int i = 0; i < 3; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(100);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            buttonGrid.getColumnConstraints().add(column);
            //set up rows
            RowConstraints row = new RowConstraints(100);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            if(i <2){
                buttonGrid.getRowConstraints().add(row);
                topGrid.getColumnConstraints().add(column);
            }
            if(i == 0){
                topGrid.getRowConstraints().add(row);
                centralGrid.getColumnConstraints().add(column);
                centralGrid.getRowConstraints().add(row);
                centralGrid.getRowConstraints().add(row);
            }
        }
        centralGrid.setHgap(25);
        centralGrid.setVgap(25);
        centralGrid.setPadding(new Insets(25));
        topGrid.setVgap(25);
        topGrid.setHgap(25);
        buttonGrid.setHgap(25);
        buttonGrid.setVgap(25);
        this.setCenter(centralGrid);
        //Buttons
        //top 2 buttons
        salesB = new MenuButton();
        salesB.setMaxHeight(400);
        searchInventory = new MenuButton();
        searchInventory.setMaxHeight(400);
        //bottom 6 buttons
        receiptsB = new MenuButton();
        receiveInventory = new MenuButton();
        notificationsB = new MenuButton();
        staffB = new MenuButton();
        createProd = new MenuButton();
        addExpiration = new MenuButton();;

        //button layout
        centralGrid.add(topGrid, 0, 0);
        topGrid.add(salesB, 0, 0);
        topGrid.add(searchInventory, 1, 0);
        centralGrid.add(buttonGrid, 0, 1);
        buttonGrid.add(receiveInventory, 0, 0);
        buttonGrid.add(receiptsB, 2, 0);
        buttonGrid.add(notificationsB, 1, 0);
        buttonGrid.add(staffB, 2, 1);
        buttonGrid.add(createProd, 0, 1);
        buttonGrid.add(addExpiration, 1, 1);
        //Sales button
        salesB.setText("POS");
        salesB.setContentDisplay(ContentDisplay.TOP);
        Icon posIcon = new Icon("cash_register_icon");
        posIcon.setFitWidth(100);
        posIcon.setFitHeight(100);
        salesB.setGraphic(posIcon);
        salesB.setOnAction(e->
        {
            try
            {
                screenControl.switchToPOSView();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //Receipts button
        receiptsB.setText("Receipts");
        receiptsB.setContentDisplay(ContentDisplay.TOP);
        receiptsB.setGraphic(new Icon("receipt_icon"));
        receiptsB.setOnAction(e->
        {
            try
            {
                screenControl.switchToReceiptSearchView();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //staff button
        staffB.setText("Create Staff");
        staffB.setContentDisplay(ContentDisplay.TOP);
        staffB.setGraphic(new Icon("staff_icon"));
        staffB.setOnAction(e->{
            try
            {
                if(control.getuserlevel()==1){
                    screenControl.switchToStaffCreateView();
                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR, "Access level denied", ButtonType.OK);
                    error.showAndWait();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //notifications button
        notificationsB.setText("Notifications");
        notificationsB.setContentDisplay(ContentDisplay.TOP);
        notificationsB.setGraphic(new Icon("bell_icon"));
        notificationsB.setOnAction(e->
        {
            try
            {
                screenControl.switchToNotifactionView();
            } catch (ParseException ex)
            {
                ex.printStackTrace();
            }
        });
        //Create Product button
        createProd.setText("Create New Product");
        createProd.setContentDisplay(ContentDisplay.TOP);
        createProd.setGraphic(new Icon("add_product_icon"));
        createProd.setOnAction(e->screenControl.switchToCreateProductView());
        //Add expiration button
        addExpiration.setText("Add Expiration");
        addExpiration.setContentDisplay(ContentDisplay.TOP);
        addExpiration.setGraphic(new Icon("expiration_icon"));
        addExpiration.setOnAction(e-> screenControl.switchToExpirationCreateView());
        //Search Inventory button
        searchInventory.setText("Search Inventory");
        searchInventory.setContentDisplay(ContentDisplay.TOP);
        Icon searchIcon = new Icon("search_icon");
        searchIcon.setFitWidth(100);
        searchIcon.setFitHeight(100);
        searchInventory.setGraphic(searchIcon);
        searchInventory.setOnAction(e-> screenControl.switchToProductSearchMenuView());
        //Receive Inventory button
        receiveInventory.setText("Receive Inventory");
        receiveInventory.setContentDisplay(ContentDisplay.TOP);
        receiveInventory.setGraphic(new Icon("inventory_icon"));
        receiveInventory.setOnAction(e->
        {
            try
            {
                screenControl.switchToReceiveInventoryView();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        this.setRight(null);
    }


}
