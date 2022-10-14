package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchNameView extends ProductSearchView
{

    public ProductSearchNameView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
    }
    protected ObservableList<Product> search(String input) throws SQLException
    {
        if(controller!=null && input.length()>0){
            ObservableList<Product> products = FXCollections.observableArrayList(controller.getProductFromInventory(input));
            return products;
        }else{
            return FXCollections.observableArrayList(new ArrayList<>());

        }

    }
}
