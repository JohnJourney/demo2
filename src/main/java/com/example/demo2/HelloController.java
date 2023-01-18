package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView myTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn surnameColumn;
    @FXML
    private TableColumn phoneColumn;

    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    // get column by it's id
    private <T> TableColumn<T, ?> getTableColumnById(TableView<T> tableView, String id) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getId().equals(id)) return col;
        return null;
    }

    public void onHideButtonClick() {
        welcomeText.setText("");

        nameColumn = getTableColumnById(myTable, "nameColumn");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        surnameColumn = getTableColumnById(myTable, "surnameColumn");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        HelloApplication.Person person1 = new HelloApplication.Person("Евгений", "Школьников","");
        myTable.getItems().add(person1);

        HelloApplication.Person person2 = new HelloApplication.Person("Анатолий", "Савин", "");
        myTable.getItems().add(person2);

        myTable.setEditable(true);
        surnameColumn.setEditable(true);

    }

    public void onTestButtonClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        // DataFromSQL sqlConnection = new DataFromSQL();
        ArrayList listNames = com.example.demo2.DataFromSQL.qetDataFromSQLDataBase();

        nameColumn = getTableColumnById(myTable, "nameColumn");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //nameColumn.setSortType(TableColumn.SortType.ASCENDING);
//        surnameColumn = getTableColumnById(myTable, "surnameColumn");
//        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        phoneColumn = getTableColumnById(myTable, "phoneColumn");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        for (Object mapName : listNames) {

            Object currentName = ((HashMap) mapName).get("name");
            Object currentPhone = ((HashMap) mapName).get("phone");
            //System.out.println(currentName);

            HelloApplication.Person person = new HelloApplication.Person(currentName, "", currentPhone);
            myTable.getItems().add(person);

        }

//        myTable.setEditable(true);
//        nameColumn.setEditable(true);
//        surnameColumn.setEditable(true);

    }

    public void onNameEditCommit() {

        HelloApplication.Person personSelected = (HelloApplication.Person) myTable.getSelectionModel().getSelectedItem();

//        myTable.setEditable(true);
//        nameColumn.setEditable(true);
//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        myTable.edit(nameColumn );nameColumn


        nameColumn.setText("test");

//        nameColumn.setOnEditCommit(e -> {
//           e.getNewValue() + " :: " + e.getRowValue());
//            e.getRowValue().setCity(e.getNewValue());
//                }



        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        welcomeText.setText("123");

    }

    public void changeName(CellEditEvent EdditableCell) {

        myTable.setEditable(true);
        nameColumn.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<HelloApplication.Person, String>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

       HelloApplication.Person personSelected = (HelloApplication.Person) myTable.getSelectionModel().getSelectedItem();
       personSelected.setName((String) EdditableCell.getNewValue());
     //  personSelected.setName("test");

        // personSelected.setName(Edd)

        //personSelected.
    }

    public void changeNameEditStart(CellEditEvent EdditableCell) {

        myTable.setEditable(true);
        nameColumn.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<HelloApplication.Person, String>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        HelloApplication.Person personSelected = (HelloApplication.Person) myTable.getSelectionModel().getSelectedItem();
        //personSelected.setName((String) EdditableCell.getNewValue());


    }

}