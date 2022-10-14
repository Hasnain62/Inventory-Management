package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import com.example.cmpt370_group14.ReceiptItem;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class ReceiptItemCell extends ListCell<ReceiptItem>
{
    private Controller controller;
    private POSView view;
    private ReceiveInventoryView inventoryView;
    private ReceiptView receiptView;
    public ReceiptItemCell(Controller controller, POSView view){
        this.controller = controller;
        this.view = view;
    }
    public ReceiptItemCell(Controller controller, ReceiveInventoryView inventoryView){
        this.controller = controller;
        this.inventoryView = inventoryView;
    }

    public ReceiptItemCell(Controller controller, ReceiptView receiptView)
    {
        this.controller = controller;
        this.receiptView = receiptView;
    }

    public void updateItem(ReceiptItem receiptItem, boolean empty){
        super.updateItem(receiptItem, empty);
        this.setTextFill(Paint.valueOf("#dedede"));
        this.setStyle("-fx-font: 32 system;-fx-control-inner-background: #41464f;-fx-control-inner-background-alt: #2a2d33;");
        if(!empty && receiptItem!=null){
            try
            {
                //adds button to cell that allows user to view product
                Button selectProduct = new Button("Select");
                selectProduct.setStyle("-fx-background-color: #1e74c9;-fx-font: 24 system;-fx-text-fill: #ffffff");
                selectProduct.setOnAction(e->{
                    controller.selectSaleItem(receiptItem);
                    try
                    {
                        if(view!= null){
                            controller.selectSaleItem(receiptItem);
                            view.displayProduct();

                        }else if(inventoryView!=null){
                            controller.selectShipmentProduct(receiptItem);
                            inventoryView.displayProduct();
                        }else{
                            receiptView.displayProduct(receiptItem);
                        }
                    } catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                });
                setGraphic(selectProduct);
                Product prod = receiptItem.getProduct();
                this.setText("Name: " + prod.getName() + "  Price: $"
                        + (prod.getProductPrice()*receiptItem.getTotalQuantity())
                        + "  qty: " + receiptItem.getTotalQuantity());
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
