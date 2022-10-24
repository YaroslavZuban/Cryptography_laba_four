package com.example.cryptography_laba_four;

import java.util.ArrayList;

import java.util.List;

public class Algorithm {
    private static int xFirst;
    private static int yFirst;

    private static int xSecond;
    private static int ySecond;

    public static String code(String line, List<List<String>> key) {
        line = parity(line, key);

        StringBuilder value = new StringBuilder();

        for (int i = 0; i < line.length() - 1; i += 2) {

            String temp=valueSearch(line, key, i);

            if(temp.equals("Вы ввели не корректный ключ или строку")){
                return "Вы ввели не корректный ключ или строку";
            }

            if (xFirst == xSecond) {
                if (yFirst + 1 >= key.get(0).size()) {
                    yFirst = 0;
                } else {
                    yFirst += 1;
                }

                if (ySecond + 1 >= key.get(0).size()) {
                    ySecond = 0;
                } else {
                    ySecond += 1;
                }

                value.append(key.get(xFirst).get(yFirst)).append(key.get(xSecond).get(ySecond));

            } else if (yFirst == ySecond) {
                if (xFirst + 1 >= key.size()) {
                    xFirst = 0;
                } else {
                    xFirst += 1;
                }

                if (xSecond + 1 >= key.size()) {
                    xSecond = 0;
                } else {
                    xSecond += 1;
                }

                value.append(key.get(xFirst).get(yFirst)).append(key.get(xSecond).get(ySecond));

            } else {
                value.append(key.get(xSecond).get(yFirst)).append(key.get(xFirst).get(ySecond));
            }
        }

        return value.toString();
    }

    public static String decode(String line, List<List<String>> key) {
        line = parity(line, key);

        StringBuilder value = new StringBuilder();

        for (int i = 0; i < line.length() - 1; i += 2) {
            String temp=valueSearch(line, key, i);

            if(temp.equals("Вы ввели не корректный ключ или строку")){
                return "Вы ввели не корректный ключ или строку";
            }
            if (xFirst == xSecond) {
                if (yFirst - 1 < 0) {
                    yFirst = key.get(0).size() - 1;
                } else {
                    yFirst -= 1;
                }

                if (ySecond - 1 < 0) {
                    ySecond = key.get(0).size() - 1;
                } else {
                    ySecond -= 1;
                }

                value.append(key.get(xFirst).get(yFirst)).append(key.get(xSecond).get(ySecond));

            } else if (yFirst == ySecond) {
                if (xFirst - 1 < 0) {
                    xFirst = key.size() - 1;
                } else {
                    xFirst -= 1;
                }

                if (xSecond - 1 < 0) {
                    xSecond = key.size() - 1;
                } else {
                    xSecond -= 1;
                }

                value.append(key.get(xFirst).get(yFirst)).append(key.get(xSecond).get(ySecond));

            } else {
                value.append(key.get(xSecond).get(yFirst)).append(key.get(xFirst).get(ySecond));
            }
        }

        return value.toString();
    }

    private static String parity(String line, List<List<String>> key) {
        line = line.replaceAll("\\s+", "").toUpperCase();

        if (line.length() % 2 != 0) {

            if (!elementSearch(" ", key).equals(""))
                line = line + " ";
            else
                line = line + key.get(key.size() - 1).get(key.get(key.size() - 1).size() - 1);
        }

        return line;
    }

    private static String valueSearch(String line, List<List<String>> key, int i) {
        String temp = String.valueOf(line.charAt(i) + line.charAt(i + 1));

        String firstValue = elementSearch(String.valueOf(line.charAt(i)), key);
        String secondValue = elementSearch(String.valueOf(line.charAt(i + 1)), key);

        if (temp.equals("") || firstValue.equals("") || secondValue.equals("")) {
            return "Вы ввели не корректный ключ или строку";
        }

        xFirst = Integer.parseInt(firstValue.substring(0, firstValue.indexOf(":")));
        yFirst = Integer.parseInt(firstValue.substring(firstValue.indexOf(":") + 1));

        xSecond = Integer.parseInt(secondValue.substring(0, secondValue.indexOf(":")));
        ySecond = Integer.parseInt(secondValue.substring(secondValue.indexOf(":") + 1));
        return "";
    }

    private static String elementSearch(String element, List<List<String>> key) {
        String temp = "";

        loop:
        for (int j = 0; j < key.size(); j++) {
            for (int k = 0; k < key.get(j).size(); k++) {
                if (element.equals(key.get(j).get(k))) {
                    temp = j + ":" + k;
                    break loop;
                }
            }
        }

        return temp;
    }
}
