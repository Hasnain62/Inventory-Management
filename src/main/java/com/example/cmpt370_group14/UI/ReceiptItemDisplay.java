package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.ReceiptItem;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

public class ReceiptItemDisplay extends GridPane
{
    private Controller controller;
    private POSView view;
    private ImageView productImage;
    private Label PID, name, price, qty, total;
    private Button remove;
    ReceiptItemDisplay(Controller controller, POSView view) throws SQLException
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
                controller.removeFromSale();
                view.updateProducts();
            } catch (NoSuchProviderException ex)
            {
                ex.printStackTrace();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //this.add(productImage, 0, 0);
        productImage = new ImageView();
        this.add(productImage, 0, 0);
        name = new Label();
        this.add(name, 0, 1);
        PID = new Label();
        this.add(PID, 0, 2);
        price = new Label();
        this.add(price, 0, 3);
        qty = new Label();
        this.add(qty, 0, 4);
        total = new Label();
        this.add(total, 0, 5);
        this.add(remove, 0, 6);
        this.setVisible(false);
    }

    public ReceiptItemDisplay(Controller controller)
    {
        this.controller = controller;
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
        productImage = new ImageView();
        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
        productImage.setImage(new Image(new File("src/main/icons/no_image.png").toURI().toString()));
        this.add(productImage, 0, 0);
        name = new Label();
        this.add(name, 0, 1);
        PID = new Label();
        this.add(PID, 0, 2);
        price = new Label();
        this.add(price, 0, 3);
        qty = new Label();
        this.add(qty, 0, 4);
        total = new Label();
        this.add(total, 0, 5);
        this.setVisible(false);
    }

    public void setProduct(ReceiptItem item) throws SQLException
    {
        this.setVisible(true);
        try
        {
            productImage.setImage(new Image(controller.getImagePath(item.getProduct().getProductID())));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        name.setText("Name: " + item.getProduct().getName());
        name.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        PID.setText("PID: " + item.getProduct().getProductID());
        PID.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        price.setText("Price:  $"+item.getProduct().getProductPrice());
        price.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        qty.setText("Qty:   x" + item.getTotalQuantity());
        qty.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        total.setText("Total: $"+item.getTotalPrice());
        total.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
    }

}
