package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.ReceiptItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.SQLException;
import java.util.ArrayList;

public class POSView extends BasicMenu
{
    private TextField productTextField;
    private ListView<ReceiptItem> productList;
    private ReceiptItemDisplay selectedItemDisplay;

    public POSView(Controller control, ScreenController screenControl) throws SQLException
    {
        super(control, screenControl);
        for(int i = 0; i < 2; i ++)
        {
            //set up columns
            ColumnConstraints column = new ColumnConstraints(50);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
            //set up rows
            RowConstraints row = new RowConstraints(50);
            if(i == 0){
                row.setVgrow(Priority.ALWAYS);
            }else{
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
        //Textfield at bottom of view to enter product id to add it to sale
        productTextField = new MyTextField();
        productTextField.setOnKeyPressed(e->
        {
            try
            {
                controller.handleKeyPressedEvent(e, productTextField.getText(), "", this);
                if(controller.getSaleItems().size()>0)
                {
                    productList.setItems(getItems());
                    productList.setCellFactory(ReceiptItem -> new ReceiptItemCell(controller, this));
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        centralGrid.add(productTextField, 0, 1);
        selectedItemDisplay = new ReceiptItemDisplay(controller, this);
        centralGrid.add(selectedItemDisplay, 1, 0);
        //checkout button
        RightBarButton checkoutB = new RightBarButton("Checkout");
        checkoutB.setContentDisplay(ContentDisplay.TOP);
        checkoutB.setGraphic(new Icon("checkout_icon"));
        checkoutB.setOnAction(e->
        {
            try
            {
                controller.finalizeSale();
                Alert confirm = new Alert(Alert.AlertType.INFORMATION, " Sale Completed", ButtonType.OK);
                confirm.showAndWait();
                screenControl.switchToMainMenu();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        });
        //RightBarButton quantityB = new RightBarButton("Change Quantity");
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(checkoutB);
        //buttons.add(quantityB);
        addRightBarButtons(buttons);

    }

    public void updateProducts() throws SQLException
    {
        productList.setItems(getItems());
        productList.setCellFactory(ReceiptItem -> new ReceiptItemCell(controller, this));
    }
    private ObservableList<ReceiptItem> getItems() throws SQLException
    {
        return FXCollections.observableArrayList(controller.getSaleItems());
    }

    public void displayProduct() throws SQLException
    {
        selectedItemDisplay.setProduct(controller.getSelectSaleItem());
    }
}
