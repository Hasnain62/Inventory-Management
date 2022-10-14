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

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffCreateView extends BasicMenu
{
    private TextField ID, name, username, accessLevel, password, reEnterPassword;
    private MenuButton pictureButton;
    public StaffCreateView(Controller controller, ScreenController screenControl)
    {
        super(controller, screenControl);
        for(int i = 0; i < 2; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(100);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            if (i == 0)
            {
                column.setMinWidth(300);
                column.setHgrow(Priority.NEVER);
            }else{
                column.setHgrow(Priority.ALWAYS);
            }
            centralGrid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 7; i++){
            //set up rows
            RowConstraints row = new RowConstraints(100);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
            if(i < 6){
                //labels
                Label label = new Label("label" + i);
                label.setTextFill(Paint.valueOf("#dedede"));
                label.setStyle("-fx-font: 24 system;");
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefSize(200, 200);
                centralGrid.add(label,0, i);
                //textfields
                if(i >= 4){
                    TextField textField = new PasswordField();
                    textField.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;-fx-text-fill: #ffffff");
                    textField.setMaxWidth(600);
                    centralGrid.add(textField, 1, i);
                }else{
                    TextField textField = new MyTextField();
                    textField.setMaxWidth(600);
                    centralGrid.add(textField, 1, i);
                }
            }
        }
        centralGrid.setHgap(20);
        centralGrid.setPadding(new Insets(20));

        ObservableList<Node> children = centralGrid.getChildren();
        ((Label) children.get(0)).setText("ID");
        ((Label) children.get(2)).setText("Name");
        ((Label) children.get(4)).setText("Access Level");
        ((Label) children.get(6)).setText("Username");
        ((Label) children.get(8)).setText("Password");
        ((Label) children.get(10)).setText("Re-enter Password");
        ID = ((TextField) children.get(1));
        name = ((TextField) children.get(3));
        accessLevel = ((TextField) children.get(5));
        username = ((TextField) children.get(7));
        password = ((TextField) children.get(9));
        reEnterPassword = ((TextField) children.get(11));

        this.setCenter(centralGrid);
        //right toolbar buttons
        ArrayList<Button> toolButtons = new ArrayList<>();
        Button createUser = new Button("Create");
        createUser.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;");
        createUser.setTextFill(Paint.valueOf("#dedede"));
        createUser.setContentDisplay(ContentDisplay.TOP);
        ImageView createIcon = new ImageView(new Image(new File("src/main/icons/new_user_icon.png").toURI().toString()));
        createIcon.setFitWidth(50);
        createIcon.setFitHeight(50);
        createUser.setGraphic(createIcon);
        createUser.setPrefSize(120, 100);
        //set up create button functionality
        createUser.setOnAction(e -> {
            if(ID.getText().length()>0 && name.getText().length()>0 &&accessLevel.getText().length()==1
                    &&username.getText().length()>0 &&password.getText().length()>0&&reEnterPassword.getText().length()>0){
                if(!ID.getText().matches("\\d+"))
                {
                    //Error for invalid inputs in PID or Price
                    Alert error = new Alert(Alert.AlertType.ERROR, "Invalid input for ID", ButtonType.OK);
                    error.showAndWait();
                }else if(!password.getText().equals(reEnterPassword.getText()))
                {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Passwords don't match", ButtonType.OK);
                    error.showAndWait();
                }else{
                    try
                    {
                        controller.addStaffToDatabase(Integer.parseInt(ID.getText()), name.getText(), username.getText(),
                                password.getText(),Integer.parseInt(accessLevel.getText()));
                        //clear text fields upon product creation
                        for(int i = 1; i < 12; i+=2){
                            ((TextField)children.get(i)).clear();
                        }
                        //dialogue box confirming creation of product
                        Alert confirm = new Alert(Alert.AlertType.INFORMATION,
                                "User " + username.getText() + " created", ButtonType.OK);
                        confirm.showAndWait();
                    }
                    catch (SQLException ex)
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
        toolButtons.add(createUser);
        addRightBarButtons(toolButtons);
    }
}
