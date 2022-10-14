package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.ReceiptItem;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.SQLException;

public class ShipmentItemDisplay extends GridPane
{
    private Controller controller;
    private ReceiveInventoryView view;
    private ImageView productImage;
    private Label PID, name, price, qty;
    private Button remove;
    ShipmentItemDisplay(Controller controller, ReceiveInventoryView view) throws SQLException
    {
        this.controller = controller;
        this.view = view;
        this.setPadding(new Insets(20));
        //set up column
        ColumnConstraints column = new ColumnConstraints(50);
        column.setHgrow(Priority.ALWAYS);
        column.setMaxWidth(USE_COMPUTED_SIZE);
        this.getColumnConstraints().add(column);
        for(int i = 0; i < 7; i ++)
        {
            //set up rows
            RowConstraints row = new RowConstraints(50);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            this.getRowConstraints().add(row);
        }
        ImageView productImage = new ImageView();
        remove = new Button("Remove");
        remove.setStyle("-fx-background-color: #1e74c9;-fx-font: 18 system;-fx-text-fill: #ffffff");
        remove.setGraphic(new Icon("trash_icon"));
        remove.setOnAction(e->
        {
            try
            {
                this.setVisible(false);
                controller.removeItemFromShipment();
                view.updateProducts();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //this.add(productImage, 0, 0);
        name = new Label();
        this.add(name, 0, 1);
        PID = new Label();
        this.add(PID, 0, 2);
        price = new Label();
        this.add(price, 0, 3);
        qty = new Label();
        this.add(qty, 0, 4);
        this.add(remove, 0, 6);
        this.setVisible(false);
    }

    public void setProduct(ReceiptItem item) throws SQLException
    {
        this.setVisible(true);
        name.setText("Name: " + item.getProduct().getName());
        name.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        PID.setText("PID: " + item.getProduct().getProductID());
        PID.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        price.setText("Price:  $"+item.getProduct().getProductPrice());
        price.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        qty.setText("Qty:   x" + item.getTotalQuantity());
        qty.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
    }
}
