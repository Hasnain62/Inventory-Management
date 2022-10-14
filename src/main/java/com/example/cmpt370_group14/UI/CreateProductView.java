package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateProductView extends BasicMenu
{
    private TextField pid, name, category, price, location;
    private MenuButton pictureButton;
    private String imagePath;
    private FileChooser fileChooser;

    public CreateProductView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        for(int i = 0; i < 3; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(100);
            if (i == 0)
            {
                column = new ColumnConstraints(200);
            }
            else if (i <= 1)
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
                //labels
                Label label = new Label("label" + i);
                label.setTextFill(Paint.valueOf("#dedede"));
                label.setStyle("-fx-font: 24 system;");
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefSize(100, 100);
                centralGrid.add(label,1, i);
                //textfields
                TextField textField = new MyTextField();
                textField.setPrefWidth(700);
                centralGrid.add(textField, 2, i);
            }
        }
        centralGrid.setHgap(20);
        centralGrid.setPadding(new Insets(20));
        //select image for product
        pictureButton = new MenuButton();
        pictureButton.setText("+");
        pictureButton.setMaxWidth(200);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        pictureButton.setOnAction(e->{
            selectImage();
        });
        centralGrid.add(pictureButton, 0, 0);

        ObservableList<Node> children = centralGrid.getChildren();
        ((Label) children.get(0)).setText("PID");
        ((Label) children.get(2)).setText("Name");
        ((Label) children.get(4)).setText("Category");
        ((Label) children.get(6)).setText("Price");
        ((Label) children.get(8)).setText("Location");
        pid = ((TextField) children.get(1));
        name = ((TextField) children.get(3));
        category = ((TextField) children.get(5));
        price = ((TextField) children.get(7));
        location = ((TextField) children.get(9));

        this.setCenter(centralGrid);
        //right toolbar buttons
        ArrayList<Button> toolButtons = new ArrayList<>();
        Button createProduct = new RightBarButton("Create");
        createProduct.setContentDisplay(ContentDisplay.TOP);
        createProduct.setGraphic(new Icon("add_icon"));
        //set up create button functionality
        createProduct.setOnAction(e -> {
                    if(pid.getText().length()>0 && name.getText().length()>0 &&category.getText().length()>0
                            &&price.getText().length()>0 &&location.getText().length()>0){
                        if(!pid.getText().matches("\\d+")||!price.getText().matches("[-+]?[0-9]*\\.?[0-9]+")){
                            //Error for invalid inputs in PID or Price
                            Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
                            error.showAndWait();
                        }else{
                            try
                            {
                                control.addProductToInventory(Integer.parseInt(pid.getText()), name.getText(),
                                        Double.parseDouble(price.getText()), 0, location.getText(),
                                        category.getText());
                                control.setImage(Integer.parseInt(pid.getText()), imagePath);
                                //clear text fields upon product creation
                                for(int i = 1; i < 10; i+=2){
                                    ((TextField)children.get(i)).clear();
                                }
                                //dialogue box confirming creation of product
                                Alert confirm = new Alert(Alert.AlertType.INFORMATION, name.getText() + " created", ButtonType.OK);
                                confirm.showAndWait();
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
        //Add create button to right side toolbar
        toolButtons.add(createProduct);
        addRightBarButtons(toolButtons);
    }

    private void selectImage(){
        Stage stage = new Stage();
        File pictureFile = fileChooser.showOpenDialog(stage);
        imagePath = pictureFile.toURI().toString();
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        pictureButton.setGraphic(imageView);
        pictureButton.setStyle("fx-background-color: transparent;");
        pictureButton.setText("");
    }
}
