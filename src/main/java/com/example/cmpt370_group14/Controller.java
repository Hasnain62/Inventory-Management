package com.example.cmpt370_group14;

import com.example.cmpt370_group14.UI.LoginView;
import com.example.cmpt370_group14.UI.POSView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


public class Controller {

    // only one inventory and categoryList each
    private Inventory inventory;
    private CategoryList categoryList;
    private ExpirationList expirationList;
    private Employees_Database employees_database;
    private SaleList saleslist;
    private Login login;
    private SaleModel saleModel;
    private ShipmentModel shipmentModel;

    // constructor connect Inventory and CategoryList with Controller
    public Controller() throws SQLException
    {
        inventory = new Inventory();
        categoryList = new CategoryList();
        expirationList = new ExpirationList();
        employees_database = new Employees_Database();
        saleslist = new SaleList();
        login = new Login();
        saleModel = new SaleModel(this, saleslist);
        shipmentModel = new ShipmentModel(this);
    }
    /////////////////////////////////////Category Functions////////////////////////////////////////////
    // RuntimeException throws, may pop up something
    public Category getCategory(String name) throws RuntimeException, SQLException {
        Category categoryTemp=categoryList.getCategory(name);
        if (categoryTemp==null){
            throw new RuntimeException("Category "+name+" not in Category list");
        }
        return categoryTemp;
    }

    public void addProductToCategory(Product product, Category category) throws SQLException {
        category.addToList(product);
    }

    public void removeProductFromCategory(Product product, Category category) throws SQLException {

        category.removeFromList(product);
    }

    public String getCategoryName(Category category){
        return category.getName();
    }

    public int getCategorySize(Category category){
        return category.getCategorySize();
    }

    public void addCategoryToList(Category category) throws SQLException {
        categoryList.addCategory(category);
    }
    public void removeCategoryFromList(Category category) throws SQLException {
        categoryList.removeCategory(category);
    }

    public void sortCategoryList(){
        categoryList.sortCategory();
    }
    //Input: a string corresponding to a category
    //output: an arraylist of all products in that category
    public ArrayList<Product> getCategoryProducts(String category) throws SQLException {
        return categoryList.getCategory(category).getProductList();
    }
    /////////////////////////////////////Inventory Functions////////////////////////////////////////////
    //TODO Remember change of deleteing addnewProduct in INventory
    public void addProductToInventory(int PID ,String name, double price, int qty, String location, String tags) throws SQLException {
        Product prod = new Product(PID, name, price, qty, location, tags);
        Ext_Product data_prod = new Ext_Product(PID, name , price ,qty ,location, tags);
       // inventory.addNewProduct(prod);
        if(!categoryList.checkCategory(tags)){
            Category cat = new Category(tags);
            categoryList.addCategory(cat);
            cat.addToList(prod);
        } else{
            categoryList.getCategory(tags).addToList(prod);
        }
    }

    public void setImage(int ID, String imagePath) throws SQLException
    {
        inventory.add_image(ID, imagePath);
    }

    public String getImagePath(int ID) throws SQLException
    {
        return inventory.get_image(ID);
    }

    public void removeProductFromInventory(Product product) throws SQLException {
        inventory.removeProduct(product);
    }

    // catch RuntimeException when product not in Inventory, may pop up something
    public Product getProductFromInventory(String name) throws SQLException {
        Product productTemp = null;
        try {
            productTemp = inventory.getProduct(name);
        }catch (Exception e){
            //TODO
        }
        return productTemp;
    }

    public Product getProductFromInventory(int PID) throws SQLException {
        Product productTemp = null;
        try {
            productTemp = inventory.getProduct(PID);
        }catch (Exception e){
            //TODO
        }
        return productTemp;
    }

    public ArrayList<Product> getFullInventory() throws SQLException {

        return inventory.getAllproducts();
    }

    /////////////////////////////////////Expiration Functions////////////////////////////////////////////

    //gets all products that expire today
    public ArrayList<Product> getTodayExpirations() throws ParseException
    {
        return expirationList.getTodayExpirations();
    }
    //adds new expiration to expiration list
    public void addExpiration(Product product, String date) throws SQLException {
        expirationList.addExpiration(product, date);
    }

    /////////////////////////////////////Staff Functions////////////////////////////////////////////
    public void addStaffToDatabase(int ID , String name , String userName, String password, int level ) throws SQLException{
        employees_database.addStaff(ID,name,level);
        Register_a_user reg = new Register_a_user(ID, userName, password);
    }
    public void removeStaffFromDatabase(int id) throws SQLException {
        employees_database.removeStaff(id);
    }

    public Staff getStaff(String name) throws SQLException {

        return employees_database.getStaff(name);
    }

    public Staff getStaff(int ID) throws SQLException {

        return employees_database.getStaff(ID);
    }

    public boolean hasStaffMember(int ID) throws SQLException {

        return employees_database.hasStaffmember(ID);
    }

    public String getCurrentUser(){
        return login.getCurrentUser();
    }
    /////////////////////////////////////Sale Functions////////////////////////////////////////////

    /**
     * finishes a sale, creating a receipt
     * @throws SQLException
     */
    public void finalizeSale() throws SQLException {
        saleModel.finalizeSale();
        saleModel.newSale();
    }

    /**
     * refreshes the sale screen
     */
    public void newSale(){
        saleModel.newSale();
    }

    /**
     * adds new product to sale
     * @param PID: PID of product to add
     * @throws SQLException
     */
    public void addToSale(int PID) throws SQLException
    {
         saleModel.addReceiptItem(PID);
    }

    /**
     * removes an item from the sale entirely
     * @throws NoSuchProviderException
     * @throws SQLException
     */
    public void removeFromSale() throws NoSuchProviderException, SQLException
    {
        saleModel.remove_item();
    }

    /**
     *
     * @returna list of all items currently in the sale
     * @throws SQLException
     */
    public ArrayList<ReceiptItem> getSaleItems() throws SQLException
    {
        return saleModel.getReceiptItems();
    }

    /**
     * @param Date: Desired sale date
     * @return: a double containing the total sale price of a day
     * @throws SQLException
     * @throws SQLException
     */
    public double DailySaleAmount(String Date) throws SQLException, SQLException{
       return saleslist.DailySale(Date);
    }

    /**
     *
     * @param qty: an int to set the selected sale item to
     * @throws SQLException
     */
    public void setSaleItemQuantity(int qty) throws SQLException
    {
        saleModel.changeQuantity(qty);
    }

    /**
     * selects an item from the sale to be edited
     * @param item: desired selected item
     */
    public void selectSaleItem(ReceiptItem item){
        saleModel.setSelectedItem(item);
    }

    /**
     * @return selected item from saleModel
     */
    public ReceiptItem getSelectSaleItem(){
        return saleModel.getSelectedItem();
    }
    /////////////////////////////////////Receipt Functions////////////////////////////////////////////
    public ArrayList<Receipts> todaysSales() throws SQLException
    {
        return saleslist.todaysSales();
    }
    public Receipts getReceipt(String num) throws SQLException
    {
        return saleslist.getReceipt(num);
    }
    /////////////////////////////////////Shipment Functions////////////////////////////////////////////
    public ArrayList<ReceiptItem> getShipmentProducts() throws SQLException
    {
        return shipmentModel.getProducts();
    }

    public void addShipmentProduct(int PID) throws SQLException
    {
        shipmentModel.addProduct(PID);
    }

    public void finalizeShipment() throws SQLException
    {
        shipmentModel.finalizeShipment();
    }

    public void removeItemFromShipment() throws SQLException
    {
        shipmentModel.remove_item();
    }
    public void selectShipmentProduct(ReceiptItem receiptItem){
        shipmentModel.setSelectedItem(receiptItem);
    }

    /////////////////////////////////////Log In Functions////////////////////////////////////////////
    public void signIn(String uname, String password) throws SQLException {

        login.signIn(uname,password);
    }

    public void signOut(){

        login.signOut();
    }

    public boolean  isSignedIn(){

      return   login.isSignedIn();
    }

    public String getuserName() {

        return login.getuserName();
    }

    public String getName() throws SQLException {

        return login.getName();
    }

    public int getuserlevel() throws SQLException {

        return login.getuserlevel();
    }

    public int getuserID() throws SQLException {

        return login.getuserID();
    }
        //TODO : getreceipt to be added

    /////////////////////////////////////key events Functions////////////////////////////////////////////
    public void handleKeyPressedEvent(KeyEvent key, String text1, String text2, Pane pane) throws SQLException
    {
        if(key.getCode().equals(KeyCode.ENTER)){
            if(pane instanceof POSView){
                addToSale(Integer.parseInt(text1));
            }else if(pane instanceof LoginView){
                signIn(text1, text2);
            }
        }
    }
}
