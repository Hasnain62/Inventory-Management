package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Expiration;
import com.example.cmpt370_group14.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.text.ParseException;

public class NotificationsView extends BasicMenu
{
    private ListView<Product> items;

    public NotificationsView(Controller control, ScreenController screenControl) throws ParseException
    {
        super(control, screenControl);
        items = new ListView();
        items.setStyle("-fx-background-color: #2a2d33;-fx-text-fill: #828282");
        ObservableList<Product> products = FXCollections.observableArrayList(control.getTodayExpirations());
        items.setItems(products);
        items.setCellFactory(Product -> new ExpirationCell());
    }
}
