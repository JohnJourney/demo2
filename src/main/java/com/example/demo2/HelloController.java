package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // get column by it's id
    private <T> TableColumn<T, ?> getTableColumnById(TableView<T> tableView, String id) {
        for (TableColumn<T, ?> col : tableView.getColumns())
            if (col.getId().equals(id)) return col ;
        return null ;
    }

    public void onHideButtonClick() {
        welcomeText.setText("");

        nameColumn = getTableColumnById(myTable, "nameColumn");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        surnameColumn = getTableColumnById(myTable, "surnameColumn");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        HelloApplication.Person person1 = new HelloApplication.Person("Евгений", "Школьников");
        myTable.getItems().add(person1);

        HelloApplication.Person person2 = new HelloApplication.Person("Анатолий", "Савин");
        myTable.getItems().add(person2);

        myTable.setEditable(true);
        surnameColumn.setEditable(true);

    }

    public void onTestButtonClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {



        sqlConnection sqlConnection = new sqlConnection();
        ResultSet resSet = com.example.demo2.sqlConnection.qetConnection();

        while(resSet.next())
        {
            String name = resSet.getString("Name");
            System.out.println(name);
        }

    }


}