package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.ReceiptItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiveInventoryView extends BasicMenu
{
    private TextField   PIDField;
    private ListView<ReceiptItem> productListView;
    private ShipmentItemDisplay selectedItemDisplay;
    public ReceiveInventoryView(Controller control, ScreenController screenControl) throws SQLException
    {
        super(control, screenControl);

        for(int i = 0; i < 2; i ++){
            //columns
            ColumnConstraints column = new ColumnConstraints(100);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
            //rows
            RowConstraints row = new RowConstraints(50);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            if(i == 0){
                row.setVgrow(Priority.ALWAYS);
            }else{
                row.setVgrow(Priority.NEVER);
            }

            centralGrid.getRowConstraints().add(row);
        }
        selectedItemDisplay = new ShipmentItemDisplay(controller, this);
        centralGrid.add(selectedItemDisplay, 1, 0);
        //Set up search bar and button
        PIDField = new MyTextField();
        PIDField.setOnKeyPressed(e->{
            try
            {
                if(e.getCode().equals(KeyCode.ENTER)){
                    controller.addShipmentProduct(Integer.parseInt(PIDField.getText()));
                    productListView.setItems(updateProducts());
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //setup Listview
        productListView = new ListView();
        productListView.setStyle("-fx-background-color: #2a2d33;-fx-text-fill: #dedede");
        productListView.setCellFactory(ReceiptItem -> new ReceiptItemCell(controller, this));

        centralGrid.add(productListView, 0, 0);
        centralGrid.add(PIDField, 0, 1);

        RightBarButton finishButton = new RightBarButton("Finish");
        finishButton.setContentDisplay(ContentDisplay.TOP);
        finishButton.setGraphic(new Icon("checkmark_icon"));
        finishButton.setOnAction(e->
        {
            try
            {
                controller.finalizeShipment();
                //dialogue box confirming creation of product
                Alert confirm = new Alert(Alert.AlertType.INFORMATION, "Shipment received", ButtonType.OK);
                confirm.showAndWait();
                screenController.switchToMainMenu();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(finishButton);
        addRightBarButtons(buttons);

    }

    ObservableList<ReceiptItem> updateProducts() throws SQLException
    {
        return FXCollections.observableArrayList(controller.getShipmentProducts());
    }
    public void displayProduct() throws SQLException
    {
        selectedItemDisplay.setProduct(controller.getSelectSaleItem());
    }
}
