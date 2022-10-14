package com.example.cmpt370_group14;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShipmentModel
{
    private ArrayList<ReceiptItem> products;
    private Controller controller;
    private ReceiptItem selectedItem;

    ShipmentModel(Controller controller){
        this.controller = controller;
        products = new ArrayList<>();
    }

    /**
     * Adds new item to sale
     *
     * @param id: product id to add to shipment
     * @throws SQLException
     */
    public void addProduct(int id) throws SQLException {
        ReceiptItem newItem = new ReceiptItem(controller.getProductFromInventory(id));
        //checks if a product with this id is already in the sale
        //Iterates the quantity if it is, otherwise it will add it to the list
        for (ReceiptItem item : products) {
            if (item !=null && item.getProduct().getProductID() == id) {
                item.setTotalQuantity(item.getTotalQuantity() + 1);
                return;
            }
        }
        newItem.setTotalQuantity(1);
        products.add(newItem);
    }

    public ArrayList<ReceiptItem> getProducts() throws SQLException
    {
        return products;
    }
    public ReceiptItem getSelectedItem(){
        return selectedItem;
    }
    public void setSelectedItem(ReceiptItem item){
        selectedItem = item;
    }
    public void remove_item() throws SQLException
    {
        products.remove(selectedItem);
    }

    public void changeQuantity(int qty) throws SQLException
    {
        selectedItem.setTotalQuantity(qty);
    }

    public void finalizeShipment() throws SQLException
    {
        for(ReceiptItem item: products){
            Product prod = item.getProduct();
            controller.removeProductFromInventory(prod);
            controller.addProductToInventory(prod.getProductID(), prod.getName(), prod.getProductPrice(),
                    prod.getQuantity()+item.getTotalQuantity(), prod.getProductLocation(), prod.getTags());
        }
        products = new ArrayList<>();
    }
}
