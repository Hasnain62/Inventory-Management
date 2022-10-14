package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Receipts;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class ReceiptCell extends ListCell<Receipts>
{
    ScreenController screenController;
    public ReceiptCell(ScreenController screenController){
        this.screenController = screenController;
    }
    public void updateItem(Receipts receipt, boolean empty){
        super.updateItem(receipt, empty);
        this.setTextFill(Paint.valueOf("#dedede"));
        this.setStyle("-fx-font: 24 system;-fx-control-inner-background: #41464f;");
        if(!empty && receipt!=null){
            try
            {
                Button selectProduct = new Button("View");
                selectProduct.setStyle("-fx-background-color: #1e74c9;-fx-font: 24 system;-fx-text-fill: #ffffff");
                selectProduct.setOnAction(e->
                {
                    try
                    {
                        screenController.switchToReceiptView(receipt);
                    } catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                });
                setGraphic(selectProduct);
                this.setText("ID: " + receipt.getReceiptNum() + "    total: " + receipt.getTotalPrice());
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
