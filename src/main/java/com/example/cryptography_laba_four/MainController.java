package com.example.cryptography_laba_four;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {
    @FXML
    private TextField TextCode;

    @FXML
    private TextField TextDecode;

    @FXML
    private TextField TextKey;

    @FXML
    private TextArea codeArea;

    @FXML
    private TextArea codeAreaDecode;

    @FXML
    private TextArea decodeArea;

    @FXML
    private TextArea decodeAreaCode;

    @FXML
    private Pane window;
    private List<List<String>> key;
    private WorkingFiles workingFiles = new WorkingFiles();
    private File fileCode;
    private File fileDecode;
    private File fileKey;

    @FXML
    void ActionInfoTeam(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//значек информации
        alert.initOwner((Stage) window.getScene().getWindow().getScene().getWindow());// создается окно, на основы панели
        alert.setTitle("Создатели");// названия окна

        // Header Text: null
        alert.setHeaderText(null);//текст заголовка, в данном случаи он нам не нужен
        alert.setContentText("Группа ПМИ-01\nБриганда 8\nЗубань Ярослав\nПавлович Владислав");// текст который будет выводиться в окне

        alert.showAndWait();//Показывает диалоговое окно и ожидает ответа пользователя
    }

    @FXML
    void ActionKey(ActionEvent event) throws IOException {
        SearchFile.search(fileKey, TextKey);
        key = workingFiles.readingKey(TextKey.getText());
    }

    @FXML
    void codeAction(ActionEvent event) throws IOException {
        if (TextKey.getText().equals(""))
            Error.error("Введите пожалуйста ключ.", window);
        else {
            SearchFile.search(fileCode, TextCode);
            String line = workingFiles.readingLine(TextCode.getText());
            List<Integer> countSpace = count(line);
            codeArea.setText(line.toUpperCase());

            String codeLine = wordsSpaces(Algorithm.code(line, key), countSpace);

            codeAreaDecode.setText(codeLine);

            workingFiles.writing(codeLine, "Закодированный текст");
        }
    }

    @FXML
    void decodeAction(ActionEvent event) throws IOException {
        if (TextKey.getText().equals(""))
            Error.error("Введите пожалуйста ключ.", window);
        else {
            SearchFile.search(fileDecode, TextDecode);
            String line = workingFiles.readingLine(TextDecode.getText());
            List<Integer> countSpace = count(line);
            decodeArea.setText(line);

            String codeLine = wordsSpaces(Algorithm.decode(line, key),countSpace);
            decodeAreaCode.setText(codeLine);

            workingFiles.writing(codeLine, "Декодированный текст");
        }
    }

    @FXML
    void infoKey(ActionEvent event) {
        if (!TextKey.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//значек информации
            alert.initOwner((Stage) window.getScene().getWindow().getScene().getWindow());// создается окно, на основы панели
            alert.setTitle("Создатели");// названия окна

            StringBuilder line = new StringBuilder("Ключ равен:\n");

            for (int i = 0; i < key.size(); i++) {
                for (int j = 0; j < key.get(i).size(); j++) {
                    line.append(key.get(i).get(j)).append("  ");
                }

                line.append("\n");
            }
            // Header Text: null
            alert.setHeaderText(null);//текст заголовка, в данном случаи он нам не нужен
            alert.setContentText(String.valueOf(line));// текст который будет выводиться в окне

            alert.showAndWait();//Показывает диалоговое окно и ожидает ответа пользователя
        }
    }

    private List<Integer> count(String line) {
        List<Integer> array = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                array.add(count);
                count = 0;
            } else {
                count++;
            }
        }

        return array;
    }

    private String wordsSpaces(String line, List<Integer> number) {
        StringBuilder stringBuilder = new StringBuilder(line);
        for (int i = 0; i < number.size(); i++) {
            stringBuilder.insert(number.get(i), "_");
        }

        return String.valueOf(stringBuilder);
    }
}