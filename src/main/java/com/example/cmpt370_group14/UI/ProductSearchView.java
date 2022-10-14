package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public abstract class ProductSearchView extends BasicSearchView
{
    protected Product selectedProduct;
    protected ProductView productView;
    public ProductSearchView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        productView = new ProductView(controller, screenController);
        centralGrid.add(productView, 1, 1);
        try{
            items.setItems(getFullInventory());
            items.setCellFactory(Product -> new ProductCell(screenControl, this));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        searchButton.setOnAction(e-> {
            try
            {
                items.setItems(search(searchBar.getText()));
                items.setCellFactory(Product -> new ProductCell(screenControl, this));
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });

    }

    private ObservableList<Product> getFullInventory() throws SQLException {
        return FXCollections.observableArrayList(controller.getFullInventory());
    }

    public void setSelectedProduct(Product product) throws SQLException
    {
        selectedProduct = product;
        productView.setProduct(product);
    }

    protected abstract ObservableList<Product> search(String input) throws SQLException;
}
