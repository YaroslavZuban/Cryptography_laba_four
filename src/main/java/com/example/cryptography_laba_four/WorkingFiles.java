package com.example.cryptography_laba_four;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingFiles {
    public List<List<String>> readingKey(String files) throws IOException {//функция считывает символы и выдает исключения если что-то не так пойдет
        String line = reading(files).toUpperCase();

        if (line.equals(""))
            return null;

        List<String> arr = List.of(line.split("="));

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).length() != arr.get(i + 1).length()) {
                return null;
            }
        }

        List<List<String>> key = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            key.add(List.of(arr.get(i).split(",")));
        }

        return key;
    }

    public void writing(String line, String name) {//данный метод записывает в файл декодированный код
        try (FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/" + name + ".txt", false)) {//создаем файл на рабочем столе cipher_of_code.txt и указываем что его можно перезаписать
            writer.write(line);//записываем в файл строку
            writer.flush();//закрывает файл
        } catch (IOException ex) {
            System.out.println(ex.getMessage());//вывод об ошибках
        }
    }

    public String readingLine(String files) throws IOException {
        File file = new File(files);//открывает файл по пути
        FileReader fr = new FileReader(file);//вставляет файл который нужно считать
        BufferedReader br = new BufferedReader(fr);//Читает текст из потока ввода символов, буферизуя символы, чтобы обеспечить эффективное чтение символов, массивов и строк.

        String line;//создание строки которая равна null
        String sourceAlphabet = "";//создается пустая строка


        while ((line = br.readLine()) != null) {//считавет с файла строк
            sourceAlphabet += line;//присвоение строки
        }

        br.close();//закрытие буфера потока ввода символов
        fr.close();//закрытие буфера чтения файла

        return sourceAlphabet;//выдает строку
    }

    private String reading(String files) throws IOException {//считывание с файла  принимает строку пути файла
        File file = new File(files);//открывает файл по пути
        FileReader fr = new FileReader(file);//вставляет файл который нужно считать
        BufferedReader br = new BufferedReader(fr);//Читает текст из потока ввода символов, буферизуя символы, чтобы обеспечить эффективное чтение символов, массивов и строк.

        String line;//создание строки которая равна null
        String sourceAlphabet = "";//создается пустая строка


        while ((line = br.readLine()) != null) {//считавет с файла строк
            sourceAlphabet += line + "=";//присвоение строки
        }

        br.close();//закрытие буфера потока ввода символов
        fr.close();//закрытие буфера чтения файла

        return sourceAlphabet;//выдает строку
    }
}
