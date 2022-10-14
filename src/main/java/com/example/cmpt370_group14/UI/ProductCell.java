package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Product;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class        ProductCell extends ListCell<Product>
{
    private ScreenController screenController;
    private ProductSearchView view;
    public ProductCell(ScreenController sc, ProductSearchView view){
        screenController = sc;
        this.view = view;
    }
    public ProductCell(ScreenController sc){
        screenController = sc;
    }
    public void updateItem(Product prod, boolean empty){
        super.updateItem(prod, empty);
        this.setTextFill(Paint.valueOf("#dedede"));
        this.setStyle("-fx-font: 24 system;-fx-control-inner-background: #41464f;");
        if(!empty && prod!=null){
            try
            {
                //adds button to cell that allows user to view product
                Button selectProduct = new Button("Select");
                selectProduct.setStyle("-fx-background-color: #1e74c9;-fx-font: 24 system;-fx-text-fill: #ffffff");
                selectProduct.setOnAction(e->
                        {
                            if (view != null)
                            {
                                try
                                {
                                    view.setSelectedProduct(prod);
                                } catch (SQLException ex)
                                {
                                    ex.printStackTrace();
                                }
                            }
                        }
                );
                setGraphic(selectProduct);
                this.setText("Name: " + prod.getName() + "  Price: $" + prod.getProductPrice()
                        + "  qty: " + prod.getQuantity() + " Location: " + prod.getProductLocation());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else{
            this.setText(null);
        }
    }
}
