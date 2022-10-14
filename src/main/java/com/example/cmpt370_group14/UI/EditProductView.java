package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import com.example.cmpt370_group14.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;
//for editing the values of an already existing product
public class EditProductView extends BasicMenu
{
    private int pid;
    private String name, category, location;
    private double price;
    private TextField pidField, nameField, categoryField, priceField, locationField;

    public EditProductView(Controller control, ScreenController screenControl, Product product) throws SQLException
    {
        super(control, screenControl);
        pid = product.getProductID();
        name = product.getName();
        location = product.getProductLocation();
        price = product.getProductPrice();
        category = product.getTags();
        for(int i = 0; i < 2; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(100);
            if (i == 0)
            {
                column.setHgrow(Priority.NEVER);
            }else{
                column.setHgrow(Priority.ALWAYS);
            }
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 6; i++){
            //set up rows
            RowConstraints row = new RowConstraints(100);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
            if(i < 5){
                //label
                Label label = new Label("label" + i);
                label.setTextFill(Paint.valueOf("#828282"));
                label.setStyle("-fx-font: 24 system;");
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefSize(100, 100);
                centralGrid.add(label,0, i);
                //textfield
                TextField textField = new MyTextField();
                centralGrid.add(textField, 1, i);
            }
        }
        centralGrid.setHgap(20);
        centralGrid.setPadding(new Insets(20));

        //set up the proper labels
        ObservableList<Node> children = centralGrid.getChildren();
        ((Label) children.get(0)).setText("PID");
        ((Label) children.get(2)).setText("Name");
        ((Label) children.get(4)).setText("Category");
        ((Label) children.get(6)).setText("Price");
        ((Label) children.get(8)).setText("Location");
        pidField = ((TextField) children.get(1));
        pidField.setText("" + pid);
        nameField = ((TextField) children.get(3));
        nameField.setText(name);
        categoryField = ((TextField) children.get(5));
        categoryField.setText(category);
        priceField = ((TextField) children.get(7));
        priceField.setText("" + price);
        locationField = ((TextField) children.get(9));
        locationField.setText(location);

        this.setCenter(centralGrid);
        //right toolbar buttons
        ArrayList<Button> toolButtons = new ArrayList<>();
        //save Edit Button
        Button save = new Button("Save Edit");
        save.setContentDisplay(ContentDisplay.TOP);
        save.setGraphic(new Icon("save_iconte"));
        save.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;");
        save.setTextFill(Paint.valueOf("#dedede"));
        save.setPrefSize(120, 100);
        save.setOnAction(e -> {
            if(pidField.getText().length()>0 && nameField.getText().length()>0 &&
                    categoryField.getText().length()>0 &&priceField.getText().length()>0 &&
                    locationField.getText().length()>0){
                if(!pidField.getText().matches("\\d+")||!priceField.getText().matches("[-+]?[0-9]*\\.?[0-9]+")){
                    //Error for invalid inputs in PID or Price
                    Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
                    error.showAndWait();
                }else{
                    try
                    {
                        //remove previous product and create new product with newly edited data
                        control.removeProductFromCategory(product, control.getCategory(category));
                        control.removeProductFromInventory(product);
                        control.addProductToInventory(Integer.parseInt(pidField.getText()), nameField.getText(),
                                Double.parseDouble(priceField.getText()), product.getQuantity(),
                                locationField.getText(), categoryField.getText());
                        //dialogue box confirming creation of product
                        Alert confirm = new Alert(Alert.AlertType.INFORMATION, name + " edited", ButtonType.OK);
                        confirm.showAndWait();
                        screenControl.switchToMainMenu();
                    } catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }

            }else{
                //Dialogue box for error where any fields are left empty
                Alert error = new Alert(Alert.AlertType.ERROR, "Can not leave any fields empty", ButtonType.OK);
                error.showAndWait();
            }
        });
        toolButtons.add(save);
        addRightBarButtons(toolButtons);
    }
}
