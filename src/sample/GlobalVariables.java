package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalVariables {
    //These are my global variables that are used throughout the project
    public static DBConnect DD = new DBConnect();
    //The observable list that is updated and eventual displayed on the table view
    public static ObservableList finTask = FXCollections.observableArrayList();
    //This holds the string that is being selected from the tableview
    public static String selectedStr = "";
}
