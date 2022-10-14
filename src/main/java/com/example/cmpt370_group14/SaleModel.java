package com.example.cmpt370_group14;

import java.sql.SQLException;
import java.util.ArrayList;

public class SaleModel {
    private float totalCost;
    private ArrayList<ReceiptItem> receiptItems;
    private Controller controller;
    private SaleList S;
    //the item selected by the user to edit
    private ReceiptItem selectedItem;

    SaleModel(Controller controller, SaleList saleList) {
        this.controller = controller;
        receiptItems = new ArrayList<>();
        S = saleList;
    }

    //resets values to start a new sale
    public void newSale(){
        totalCost = 0;
        receiptItems = new ArrayList<>();
    }

    /**
     * Adds new item to sale
     *
     * @param id: product id to add to sale
     * @throws SQLException
     */
    public void addReceiptItem(int id) throws SQLException {
        ReceiptItem newItem = new ReceiptItem(controller.getProductFromInventory(id));
        //checks if a product with this id is already in the sale
        //Iterates the quantity if it is, otherwise it will add it to the list
        for (ReceiptItem item : receiptItems) {
            if (item !=null && item.getProduct().getProductID() == id) {
                totalCost-=item.getTotalPrice();
                item.setTotalQuantity(item.getTotalQuantity() + 1);
                totalCost += item.getTotalPrice();
                selectedItem = item;
                return;
            }
        }
        newItem.setTotalQuantity(1);
        receiptItems.add(newItem);
        selectedItem = newItem;
        totalCost += newItem.getTotalPrice();
    }

    public ArrayList<ReceiptItem> getReceiptItems() throws SQLException
    {
        return receiptItems;
    }
    public ReceiptItem getSelectedItem(){
        return selectedItem;
    }

    public void setSelectedItem(ReceiptItem item){
        selectedItem = item;
    }


    public void finalizeSale() throws SQLException {

        String Receipt_no = String.valueOf(S.generateReceiptNumber());
        Receipts receipt = new Receipts(Receipt_no, receiptItems);
        S.addReceipt(receipt);
        for (ReceiptItem item: receiptItems)
        {
            //products can and should be able to have a negative quantity in the inventory
            controller.getProductFromInventory(item.getProduct().getProductID()).removeQuantity(item.getTotalQuantity());
        }
    }

    public void remove_item() throws SQLException
    {
        totalCost-=selectedItem.getTotalPrice();
        receiptItems.remove(selectedItem);
    }


    public void changeQuantity(int qty) throws SQLException
    {
        totalCost -= selectedItem.getTotalPrice();
        selectedItem.setTotalQuantity(qty);
        totalCost += selectedItem.getTotalPrice();
    }
}