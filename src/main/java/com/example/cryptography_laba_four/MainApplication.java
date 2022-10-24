package com.example.cryptography_laba_four;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("head.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bigram encryption");
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("title_1.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}