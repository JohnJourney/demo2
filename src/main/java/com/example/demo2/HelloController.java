package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.demo2.HelloApplication.Person;

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
    private TextField textFieldWayToBase;

//    protected void buttonHello() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    // get column by it's id
    private <T> TableColumn<T, ?> getTableColumnById(TableView<T> tableView, String id) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getId().equals(id)) return col;
        return null;
    }

    public void buttonAddTestRows() {

        nameColumn = getTableColumnById(myTable, "Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        surnameColumn = getTableColumnById(myTable, "Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));

        Person person1 = new Person("Евгений", "Школьников","");
        myTable.getItems().add(person1);

        Person person2 = new Person("Анатолий", "Савин", "");
        myTable.getItems().add(person2);

    }

    public void buttonGetDataFromSQL(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        myTable.getItems().clear();

        String waytoDataBase = textFieldWayToBase.getText();

       // ArrayList listNames = DataFromSQL.qetDataFromSQLDataBase(waytoDataBase);

        HashMap mapColumnNames_and_listResultSet = DataFromSQL.qetDataFromSQLDataBase(waytoDataBase);

        // todo >>> how to compare sql data and tablewiew using column names from sql and fx column id ?

        ArrayList columnNames = (ArrayList) mapColumnNames_and_listResultSet.get("columnNames");
        var namesIterator = columnNames.iterator();

        ArrayList listPersons = (ArrayList) mapColumnNames_and_listResultSet.get("dataList");



        for (Object mapName : listPersons) {

            Object currentName = ((HashMap) mapName).get("Name");
            Object currentPhone = ((HashMap) mapName).get("Phone");

            Person person = new Person(currentName, "", currentPhone);
            myTable.getItems().add(person);

        }




        while (namesIterator.hasNext()) {

            String columnName = (String) namesIterator.next();

            TableColumn currentColumn = getTableColumnById(myTable, columnName);
            currentColumn.setCellValueFactory(new PropertyValueFactory<>(columnName));


//            Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
//                    = (TableColumn<Person, String> param) -> new EditableCell();
//
//            currentColumn.setCellFactory(cellFactory);
//
//            currentColumn.setOnEditCommit(
//                    (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> ((Person) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())
//                    ).setName(t.getNewValue()));



        }
//        nameColumn = getTableColumnById(myTable, "nameColumn");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        phoneColumn = getTableColumnById(myTable, "phoneColumn");
//        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
//
        // var iteratorListNames = listNames.iterator();

//        ArrayList listPersons = (ArrayList) mapColumnNames_and_listResultSet.get("dataList");
//
//        for (Object mapName : listPersons) {
//
//            Object currentName = ((HashMap) mapName).get("Name");
//            Object currentPhone = ((HashMap) mapName).get("Phone");
//
//            Person person = new Person(currentName, "", currentPhone);
//            myTable.getItems().add(person);
//
//        }

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
                = (TableColumn<Person, String> param) -> new EditableCell();

        nameColumn.setCellFactory(cellFactory);

        nameColumn.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue()));


        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory1
                = (TableColumn<Person, String> param) -> new EditableCell();

        phoneColumn.setCellFactory(cellFactory1);

        phoneColumn.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Person, String>>) t -> ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPhone(t.getNewValue()));



   }

}
        /*public void nameOnEditStart (TableColumn.CellEditEvent < Person, String > EdditableCell){

            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            Person personSelected = (Person) myTable.getSelectionModel().getSelectedItem();

            personSelected.setName("WARMER");
            //personSelected.setName((String) EdditableCell.getNewValue());
            nameColumn.setText("test");

        }*/

   /* public void nameOnEditCommit(TableColumn.CellEditEvent<Person, String> CellEditEvent) {
    public void nameOnEditCommit(TableColumn.CellEditEvent<Person, String> EdditableCell) {

        Person personSelected = (Person) myTable.getSelectionModel().getSelectedItem();

        personSelected.setName("GOT IT");

        //personSelected.setName((String) EdditableCell.getNewValue());

    }*/
