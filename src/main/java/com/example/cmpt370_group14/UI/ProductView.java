package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.sql.SQLException;

public class ProductView extends GridPane
{
    private Controller controller;
    private ScreenController screenController;
    private ImageView productImage;
    private Label PID, name, price, qty, location;
    private Button edit;
    private Product selectedProduct;
    ProductView(Controller controller, ScreenController screenController)
    {
        this.controller = controller;
        this.screenController = screenController;
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
        edit = new Button("Edit");
        edit.setStyle("-fx-background-color: #1e74c9;-fx-font: 18 system;-fx-text-fill: #ffffff");
        edit.setGraphic(new Icon("edit_icon"));
        edit.setOnAction(e->
        {
                this.setVisible(false);
            try
            {
                if(controller.getuserlevel() <= 2)
                {
                    screenController.switchToEditProductView(selectedProduct);
                }else{
                    Alert error = new Alert(Alert.AlertType.ERROR, "Access level denied", ButtonType.OK);
                    error.showAndWait();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
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
        location = new Label();
        this.add(location, 0, 5);
        this.add(edit, 0, 6);
        this.setVisible(false);
    }

    public void setProduct(Product item) throws SQLException
    {
        this.setVisible(true);
        selectedProduct = item;
        try{
            productImage.setImage(new Image(controller.getImagePath(selectedProduct.getProductID())));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        name.setText("Name: " + item.getName());
        name.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        PID.setText("PID: " + item.getProductID());
        PID.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        price.setText("Price:  $"+item.getProductPrice());
        price.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        qty.setText("Qty:   x" + item.getQuantity());
        qty.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
        location.setText("Location: "+item.getProductLocation());
        location.setStyle("-fx-font: 32 system;-fx-text-fill: #dedede");
    }
}
