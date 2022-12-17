package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView myTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn surnameColumn;

//    public HelloController() {
//        this.myTable = myTable;
//    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // get column by it's id
    private <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getId().equals(name)) return col ;
        return null ;
    }

    public void onHideButtonClick() {
        welcomeText.setText("");
        //myTable.getColumns();



        //nameColumn = (TableColumn) myTable.getColumns().get(0);
        nameColumn = getTableColumnByName(myTable, "nameColumn");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        surnameColumn = (TableColumn) myTable.getColumns().get(1);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        HelloApplication.Person person1 = new HelloApplication.Person("Евгений", "Школьников");
        myTable.getItems().add(person1);

        HelloApplication.Person person2 = new HelloApplication.Person("Анатолий", "Савин");
        myTable.getItems().add(person2);

        myTable.setEditable(true);
        surnameColumn.setEditable(true);

    }
}