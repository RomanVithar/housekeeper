package com.key_manager;

import com.key_manager.dto.Note;
import com.key_manager.dto.OneWrite;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner scn = new Scanner(System.in);
    private static Note note;

    static {
        try {
            note = new Note();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        note.deserialize();
        printPreview();
        manager();
    }

    public static void printPreview() {
        printGreen("Добрый день Роман Бориславович!");
    }

    private static void printListQuestions() {
        printGreen("Пожалуйста скажите, что я могу для вас сделать:");
        System.out.println("1. Записать пароль.");
        System.out.println("2. Найти пароль.");
        System.out.println("3. Вывести список всех паролей.");
        System.out.println("4. Удалить пароль.");
        System.out.println("5. Завершить работу.");
    }

    public static void manager() throws IOException {
        while (true) {
            printListQuestions();
            switch (scn.nextInt()) {
                case 1:
                    writePassword();
                    break;
                case 2:
                    findPassword();
                    break;
                case 3:
                    for (OneWrite item:note.getListWrites()){
                        System.out.println(item);
                    }
                    break;
                case 4:
                    deletePassword();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void deletePassword() {
        printGreen("Введите информацию о пароле, который хотите удалить.");
        System.out.print("Введите пожалуйста логин: ");
        String login = scn.next();
        System.out.print("Введите пожалуйста пароль: ");
        String password = scn.next();
        System.out.print("Пожалуйста добавте название объекта к которому " +
                "прилагается пароль(желательно одним словом на английском): ");
        String object = scn.next();
        System.out.println("Желаете ли добавить описание для пароля?" +
                "Вам достаточно просто отевтить yes или no: ");
        String answer = scn.next();
        String description = null;
        if (answer.toLowerCase().equals("yes")) {
            System.out.println("Прошу вас описать свой пароль: ");
            description = scn.next();
        }
        note.deletePassword(new OneWrite(password, login, object, description));
        System.out.println("Пароль удалён!");
    }

    private static void findPassword() {
        printGreen("По какому признаку искать пароль?");
        System.out.println("1. Логин.");
        System.out.println("2. Откуда пароль");
        int answ = scn.nextInt();
        OneWrite oneWrite = null;
        if (answ == 1) {
            System.out.println("Введите логин: ");
            oneWrite = note.searchForLogin(scn.next());
        }
        if (answ == 2) {
            System.out.println("Введите откуда пароль: ");
            oneWrite = note.searchForFrom(scn.next());
        }
        if (oneWrite != null) {
            System.out.println(oneWrite);
        } else {
            System.out.println("Пароль не найден!");
            System.out.println("================");
        }
    }

    private static void writePassword() throws IOException {
        printGreen("Прошу вас задать параметры пароля, Роман Бориславович.");
        System.out.print("Введите пожалуйста логин: ");
        String login = scn.next();
        System.out.print("Введите пожалуйста пароль: ");
        String password = scn.next();
        System.out.print("Пожалуйста добавте название объекта к которому " +
                "прилагается пароль(желательно одним словом на английском): ");
        String object = scn.next();
        System.out.println("Желаете ли добавить описание для пароля?" +
                "Вам достаточно просто отевтить yes или no: ");
        String answer = scn.next();
        String description = null;
        if (answer.toLowerCase().equals("yes")) {
            System.out.println("Прошу вас описать свой пароль: ");
            description = scn.next();
        }
        writePassword(login, password, object, description);
        System.out.println("Пароль успешно записан!");
    }

    private static void writePassword(String login, String password, String from, String description) throws IOException {
        note.add(password, login, from, description);
        note.serialize();
    }

    private static void printGreen(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }
}
