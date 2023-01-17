package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static class Person {
        private Object name;
        private Object surname;
        private Object phone;

    public Person(Object name, Object surname, Object phone) {
            this.name = name;
            this.surname = surname;
            this.phone = phone;
        }

        public Object getName() {
            return name;
        }
        public Object getPhone() {
            return phone;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}