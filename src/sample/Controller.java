package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static sample.GlobalVariables.DD;
import static sample.GlobalVariables.finTask;
import static sample.GlobalVariables.selectedStr;


public class Controller implements Initializable {
    @FXML
    public TextField Txt_Name;
    @FXML
    public TextField Txt_Descr;
    @FXML
    public TextField Txt_Imp;
    @FXML
    public TextField Txt_Status;
    @FXML
    public Button Btn_Create;
    @FXML
    public Button Btn_Delete;
    @FXML
    public Stage stage;
    @FXML
    public TableView<Tasks> TV_Info;
    @FXML
    public TableColumn<Tasks, String> Col_ID;
    @FXML
    public TableColumn<Tasks, String> Col_Name;
    @FXML
    public TableColumn<Tasks, String> Col_Descr;
    @FXML
    public TableColumn<Tasks, String> Col_Imp;
    @FXML
    public TableColumn<Tasks, String> Col_Status;

    //Creates the task with given information from the text fields and
    //creates a product to display on the tableview
    public void HandleCreate(ActionEvent event) {
        String name = Txt_Name.getText();
        String description = Txt_Descr.getText();
        String importance = Txt_Imp.getText();
        String status = Txt_Status.getText();
        finTask.add(new Tasks(name, description, importance, status));
        DD.insertIntoTaskTable(name, description, importance, status);
        TV_Info.setItems(finTask);
    }

    //Deletes the selected task from tableview and database
    public void HandleDelete(ActionEvent event) {
        System.out.println("Delete");
        System.out.println(selectedStr);
        DD.deleteFromTable(selectedStr);
        TV_Info.getItems().clear();
        finTask = getGroup();
        TV_Info.setItems(finTask);

    }

    public void initialize(URL location, ResourceBundle resources) {
        Col_ID.setCellValueFactory(new PropertyValueFactory<Tasks, String>(
                "ID"));
        Col_Name.setCellValueFactory(new PropertyValueFactory<Tasks, String>(
                "Name"));
        Col_Descr.setCellValueFactory(new PropertyValueFactory<Tasks, String>(
                "Description"));
        Col_Imp.setCellValueFactory(new PropertyValueFactory<Tasks, String>(
                "Importance"));
        Col_Status.setCellValueFactory(new PropertyValueFactory<Tasks, String>(
                "Status"));
        TV_Info.setItems(getGroup());

        TV_Info.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 1) {
                    //Use ListView's getSelected Item
                    Tasks currentItemSelected = TV_Info.getSelectionModel()
                            .getSelectedItem();
                    selectedStr = currentItemSelected.getDescription();
                    System.out.println(selectedStr);
                    //use this to do whatever you want to. Open Link etc.
                    try {
                        System.out.println("Hi");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public ObservableList<Tasks> getGroup() {

        try {
            Connection conn = DD.getConn();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM TASKTABLE");
            while (res.next()) {
                Tasks tempTask = new Tasks(res.getString("NAME"), res.getString("DESCRIPTION")
                        , res.getString("IMPORTANCE"), res.getString("STATUS"));
                finTask.add(tempTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finTask;
    }
}
