package com.example.cmpt370_group14.UI;

import com.example.cmpt370_group14.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;

//Window for creating expiration dates
public class ExpirationCreateView extends BasicMenu
{

    private TextField pid, day, month, year;
    public ExpirationCreateView(Controller control, ScreenController screenControl)
    {
        super(control, screenControl);
        centralGrid.setHgap(20);
        centralGrid.setPadding(new Insets(20));
        this.setCenter(centralGrid);
        for(int i = 0; i < 2; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(200);
            if (i == 0)
            {
                column.setHgrow(Priority.NEVER);
            }else{
                column.setHgrow(Priority.ALWAYS);
            }
            column.setMaxWidth(USE_COMPUTED_SIZE);
            centralGrid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < 3; i++){
            //set up rows
            RowConstraints row = new RowConstraints(100);
            row.setVgrow(Priority.ALWAYS);
            row.setMaxHeight(USE_COMPUTED_SIZE);
            centralGrid.getRowConstraints().add(row);
            if(i < 2){
                //labels
                Label label = new Label("label" + i);
                label.setTextFill(Paint.valueOf("#dedede"));
                label.setStyle("-fx-font: 24 system;");
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefSize(200, 200);
                centralGrid.add(label,0, i);
            }
        }
        pid = new MyTextField();
        centralGrid.add(pid, 1, 0);
        //Expiration date entry fields
        Label dd = new Label("dd");
        Label mm = new Label("mm");
        Label yyyy = new Label("yyyy");
        Label[] labels = new Label[3];
        labels[0] = dd;
        labels[1] = mm;
        labels[2] = yyyy;
        for(int i = 0; i < 3; i++){
            labels[i].setTextFill(Paint.valueOf("#dedede"));
            labels[i].setStyle("-fx-font: 24 system;");
            labels[i].setAlignment(Pos.CENTER);
            labels[i].setPrefSize(200, 200);
        }
        //entry fields for date
        day = new MyTextField();
        month = new MyTextField();
        year = new MyTextField();
        GridPane dateGrid = new GridPane();
        for(int i = 0; i < 6; i ++){
            //set up columns
            ColumnConstraints column = new ColumnConstraints(10);
            column.setHgrow(Priority.ALWAYS);
            column.setMaxWidth(USE_COMPUTED_SIZE);
            dateGrid.getColumnConstraints().add(column);
        }
        //add elements to date entry fields
        dateGrid.addRow(0);
        dateGrid.add(dd, 0, 0);
        dateGrid.add(mm, 2, 0);
        dateGrid.add(yyyy, 4, 0);
        dateGrid.add(day, 1, 0);
        dateGrid.add(month, 3, 0);
        dateGrid.add(year, 5, 0);
        DatePicker datePicker = new DatePicker();
        centralGrid.add(datePicker, 1, 1);
        //set labels
        ObservableList<Node> children = centralGrid.getChildren();
        ((Label) children.get(0)).setText("PID");
        ((Label) children.get(1)).setText("Expiration Date");
        //right toolbar buttons
        ArrayList<Button> toolButtons = new ArrayList<>();
        Button create = new Button("Create");
        create.setContentDisplay(ContentDisplay.TOP);
        create.setGraphic(new Icon("add_icon"));
        create.setStyle("-fx-background-color: #41464f; -fx-border-color: #828282; -fx-border-width: 1;-fx-font: 18 system;");
        create.setTextFill(Paint.valueOf("#dedede"));
        create.setPrefSize(120, 100);
        //set up create button functionality
        create.setOnAction(e -> {
            //check that inputs are correct length
            if(pid.getText().length()>0 && datePicker.getValue().toString().length()>0)
            {
                //check for valid date inputs, currently the max day is 31 regardless of month
                if(!pid.getText().matches("\\d+"))
                {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Invalid PID", ButtonType.OK);
                    error.showAndWait();
                }
                else
                {
                    try
                    {
                        String expDate = year.getText() + "-" + month.getText() + "-" + day.getText();
                        //check if given PId matches a valid product in database
                        if(control.getProductFromInventory(Integer.parseInt(pid.getText()))!=null){
                            System.out.println(datePicker.getValue().toString());
                            control.addExpiration(control.getProductFromInventory(Integer.parseInt(pid.getText())), datePicker.getValue().toString());
                            //switch to main menu after creation
                            screenControl.switchToMainMenu();
                            Alert confirm = new Alert(Alert.AlertType.INFORMATION,"Expiration Created", ButtonType.OK);
                            confirm.showAndWait();
                        }
                        else
                        {
                            Alert error = new Alert(Alert.AlertType.ERROR, "PID does not exist", ButtonType.OK);
                            error.showAndWait();
                        }
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
            else
            {
                Alert error = new Alert(Alert.AlertType.ERROR, "Can not leave fields blank", ButtonType.OK);
                error.showAndWait();
            }
        });
        //Add create button to right side toolbar
        toolButtons.add(create);
        addRightBarButtons(toolButtons);
    }
}
