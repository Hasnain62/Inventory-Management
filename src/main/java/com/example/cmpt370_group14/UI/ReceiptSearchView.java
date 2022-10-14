package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Receipts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ReceiptSearchView extends BasicSearchView
{

    public ReceiptSearchView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        try{
            items.setItems(todaysSales());
            items.setCellFactory(Receipts -> new ReceiptCell(screenController));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        searchButton.setOnAction(e-> {
            try
            {
                items.setItems(search(searchBar.getText()));
                items.setCellFactory(Receipts -> new ReceiptCell(screenController));
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
    }

    public ObservableList<Receipts> todaysSales() throws SQLException
    {
        return FXCollections.observableArrayList(controller.todaysSales());
    }

    public ObservableList<Receipts> search(String num) throws SQLException
    {
        return FXCollections.observableArrayList(controller.getReceipt(num));
    }
}
