package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.ReceiptItem;
import com.example.cmpt370_group14.Receipts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.SQLException;

public class ReceiptView extends BasicMenu
{
    private TextField productTextField;
    private ListView<ReceiptItem> productList;
    private ReceiptItemDisplay selectedItemDisplay;
    private Receipts receipt;
    private ReceiptItem selectedItem;

    public ReceiptView(Controller controller, ScreenController screenControl, Receipts receipt) throws SQLException
    {
        super(controller, screenControl);
        this.receipt = receipt;
        for (int i = 0; i < 2; i++)
        {
            //set up columns
            ColumnConstraints column = new ColumnConstraints(50);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
            //set up rows
            RowConstraints row = new RowConstraints(50);
            if (i == 0)
            {
                row.setVgrow(Priority.ALWAYS);
            } else
            {
                row.setVgrow(Priority.NEVER);
            }

            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
        }
        centralGrid.getRowConstraints().get(1).setMaxHeight(100);

        productList = new ListView<>();
        productList.setStyle("-fx-background-color: #2a2d33;");
        centralGrid.add(productList, 0, 0);

        productList.setItems(getItems());
        productList.setCellFactory(ReceiptItem -> new ReceiptItemCell(controller, this));
        selectedItemDisplay = new ReceiptItemDisplay(controller);
        centralGrid.add(selectedItemDisplay, 1, 0);
    }

    public void updateProducts() throws SQLException
    {
        productList.setItems(getItems());
        productList.setCellFactory(ReceiptItem -> new ReceiptItemCell(controller, this));
    }

    private ObservableList<ReceiptItem> getItems() throws SQLException
    {
        return FXCollections.observableArrayList(receipt.getItems());
    }

    public void displayProduct(ReceiptItem item) throws SQLException
    {
        selectedItem = item;
        selectedItemDisplay.setProduct(item);
    }
}