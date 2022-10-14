package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ProductSearchPIDView extends ProductSearchView
{
    public ProductSearchPIDView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
    }
    protected ObservableList<Product> search(String input) throws SQLException
    {
        if(input.length()>0){
            ObservableList<Product> products = FXCollections.observableArrayList(controller.getProductFromInventory(Integer.parseInt(input)));
            return products;
        }
        return null;
    }
}
