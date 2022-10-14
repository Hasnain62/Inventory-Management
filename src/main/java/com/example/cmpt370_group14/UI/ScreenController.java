package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import com.example.cmpt370_group14.Receipts;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ScreenController
{
    private ArrayList<Parent> previousScenes;
    protected Controller controller;
    private Stage stage;
    private Parent currentLayout, mainLayout;

    public ScreenController(Controller control, Stage s){
        controller = control;
        stage = s;
        previousScenes = new ArrayList<>();
        Scene scene = new Scene((currentLayout = new LoginView(controller, this)));
        stage.setScene(scene);
        switchToLoginView();
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    public void swapScenes(Parent newLayout){
        previousScenes.add(currentLayout);
        currentLayout = newLayout;
        stage.getScene().setRoot(newLayout);
    }

    //returns you to the previous screen
    public void goBack(){
        Parent prev = previousScenes.get(previousScenes.size()-1);
        previousScenes.remove(previousScenes.size()-1);
        currentLayout = prev;
        stage.getScene().setRoot(currentLayout);
    }

    public void switchToLoginView(){
        controller.newSale();
        previousScenes.clear();
        swapScenes(new LoginView(controller, this));
    }

    public void switchToMainMenu(){
        previousScenes.clear();
        swapScenes(new MainMenuView(controller, this));
    }

    public void switchToCreateProductView(){
        swapScenes(new CreateProductView(controller, this));
    }
    public void switchToProductSearchMenuView(){
        swapScenes(new ProductSearchMenuView(controller, this));
    }

    public void switchToProductSearchName(){
        swapScenes(new ProductSearchNameView(controller, this));
    }
    public void switchToProductSearchCategory(){
        swapScenes(new ProductSearchCategoryView(controller, this));
    }
    public void switchToProductSearchPID(){
        swapScenes(new ProductSearchPIDView(controller, this));
    }

    public void switchToProductView(Product product) throws SQLException
    {
        swapScenes(new ProductView(controller, this));
    }

    public void switchToEditProductView(Product product) throws SQLException
    {
        swapScenes(new EditProductView(controller, this, product));
    }

    public void switchToNotifactionView() throws ParseException
    {
        swapScenes(new NotificationsView(controller, this));
    }

    public void switchToExpirationCreateView(){
        swapScenes(new ExpirationCreateView(controller, this));
    }

    public void switchToPOSView() throws SQLException
    {
        swapScenes(new POSView(controller, this));
    }

    public void switchToReceiveInventoryView() throws SQLException
    {
        swapScenes(new ReceiveInventoryView(controller, this));
    }

    public void switchToStaffCreateView()
    {
        swapScenes(new StaffCreateView(controller, this));
    }

    public void switchToReceiptView(Receipts receipt) throws SQLException
    {
        swapScenes(new ReceiptView(controller, this, receipt));
    }
    public void switchToReceiptSearchView() throws SQLException
    {
        swapScenes(new ReceiptSearchView(controller, this));
    }
}
