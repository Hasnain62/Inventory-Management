package com.example.cmpt370_group14;

import java.sql.SQLException;
/**
 * ReceiptItem Class`
 * by Demi
 */
public class ReceiptItem {

    /**
     * a Product
     */
    private Product product;
    /**
     * quantity of product
     */
    private int quantity;

    /**
     * Initialize an instance of ReceiptItem with a given Product and quantity
     * @param product - a product
     */

    public ReceiptItem(Product product){

        // Here Upfate the products column in receipt table
        this.product = product;

    }
    /**
     * returns the total price of a product
     * @return totalPrice of a product
     */
    public double getTotalPrice() throws SQLException {

        //if there is no product, then total price is 0
        if(this.product == null){
            return 0;
        }
        return this.product.getProductPrice() * this.quantity;
    }

    /**
     *returns the number of products
     * @return the number of products
     */
    public int getTotalQuantity(){
        return this.quantity;
    }
    /**
     *sets the number of products
     * @param q the number of products
     */
    public void setTotalQuantity(int q){
        this.quantity = q;
    }

    /**
     *returns product
     * @return a product
     */
    public Product getProduct(){
        if(this.product == null){
            throw new RuntimeException("Error: There is no product stored in ReceiptItem!");
        }

        return this.product;
    }


}
