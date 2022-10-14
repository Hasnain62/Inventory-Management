package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Product;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

//the format that an expiration will appear in for a list
public class ExpirationCell extends ListCell<Product>
{
    ExpirationCell(){}

    public void updateItem(Product product, boolean empty){
        super.updateItem(product, empty);
        //sets text style
        this.setTextFill(Paint.valueOf("#828282"));
        this.setStyle("-fx-font: 24 system;");
        if(!empty && product!=null){
            try
            {
                this.setText("Product: "+ product.getName() + " PID: " + product.getProductID());
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
