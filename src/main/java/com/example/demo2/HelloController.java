package com.example.demo2;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
    private Person Person;

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

        Person person1 = new Person("Евгений", "Школьников","");
        myTable.getItems().add(person1);

        Person person2 = new Person("Анатолий", "Савин", "");
        myTable.getItems().add(person2);

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
                = (TableColumn<Person, String> param) -> new EditingCell();

        nameColumn.setCellFactory(cellFactory);
        
    }

    public void onTestButtonClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        ArrayList listNames = DataFromSQL.qetDataFromSQLDataBase();

        nameColumn = getTableColumnById(myTable, "nameColumn");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        phoneColumn = getTableColumnById(myTable, "phoneColumn");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        for (Object mapName : listNames) {

            Object currentName = ((HashMap) mapName).get("name");
            Object currentPhone = ((HashMap) mapName).get("phone");

            Person person = new Person(currentName, "", currentPhone);
            myTable.getItems().add(person);

        }

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
                = (TableColumn<Person, String> param) -> new EditingCell();

        nameColumn.setCellFactory(cellFactory);

//        nameColumn.setOnEditCommit(
//                (TableColumn.CellEditEvent<Person, String> t) -> {
//                    ((Person) myTable.getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
//
//                });



    }

//    public void nameOnEditStart(TableColumn.CellEditEvent<Person, String> EdditableCell) {
//
////        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
////
////
////
////
////     Person personSelected = (Person) myTable.getSelectionModel().getSelectedItem();
//////
////      personSelected.setName("WARMER");
//////        //personSelected.setName((String) EdditableCell.getNewValue());
//////        nameColumn.setText("test");
//
//    }

//    //public void nameOnEditCommit(TableColumn.CellEditEvent<Person, String> CellEditEvent) {
//    public void nameOnEditCommit(TableColumn.CellEditEvent<Person, String> EdditableCell) {
//
//        Person personSelected = (Person) myTable.getSelectionModel().getSelectedItem();
//
//        personSelected.setName("GOT IT");
//
//
//
//
//        //personSelected.setName((String) EdditableCell.getNewValue());
//
//    }

    class EditingCell extends TableCell<Person, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

}