package org.example;

import java.io.*;
import java.util.Scanner;

public class HomeWork3 {
    public static final int PASSWORD_LENGTH = 8;

    public static void main(String[] args) throws IOException {
//        Task1();

        FileManager mg = new FileManager();
        mg.read("D:\\Рабочий стол\\Виды исключений.txt");
        mg.write("D:\\Рабочий стол\\Виды исключений.txt", "Привет");
        mg.copy("D:\\Рабочий стол\\Виды исключений.txt", "D:\\Рабочий стол\\2.txt");
    }

    /*
    Задача 1: Проверка пароля (Основы языка Java, операторы, ветвления)
     */
    public static void Task1() {
        Scanner input = new Scanner(System.in);
        System.out.print(
                """
                        1. Пароль должен быть не менее 8 символов.
                        2. Пароль должен содержать хотя бы одну цифру.
                        3. Пароль должен содержать хотя бы одну заглавную букву.\s
                        Input a password (You are agreeing to the above Terms and Conditions.):\s""");
        String s = input.nextLine();
        try {
            is_Valid_Password(s);
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void is_Valid_Password(String password) throws InvalidPasswordException {
        if (password.length() < PASSWORD_LENGTH) {
            throw new InvalidPasswordException("Пароль менее 8 символов!");
        } else {
            int numCount = 0;
            int charCount = 0;
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (is_Numeric(ch)) numCount++;
                else if (is_Letter(ch)) charCount++;
            }
            if (numCount >= 1 && charCount >= 1) {
                System.out.println("Пароль принят");
            } else {
                throw new InvalidPasswordException("В пароле менее 1 цифры или заглавной буквы!");
            }
        }
    }

    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static boolean is_Letter(char ch) {
        return (ch >= 'A' && ch <= 'Z');
    }

    static class InvalidPasswordException extends Exception {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    /*
    Задача 2: Файловый менеджер (ООП, исключения)
    Создайте класс FileManager. Этот класс должен иметь методы для
    выполнения основных операций с файлами: чтение, запись и копирование.
    Каждый из этих методов должен выбрасывать соответствующее исключение, если в процессе
    выполнения операции произошла ошибка (например, FileNotFoundException, если файл не найден).
     */


    public static class FileManager {
        public void read(String filePath) throws IOException {
            FileReader fileReader = new FileReader(filePath);
            StringBuilder content = new StringBuilder();
            try (fileReader; BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("n");
                }
            }
        }
        // Метод для записи данных в файл
        public void write(String filePath, String content) throws IOException {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(content);
            }
        }
        // Метод для копирования файла
        public void copy(String sourceFilePath, String destinationFilePath) throws IOException {
            byte[] buffer = new byte[1024];
            int bytesRead;
            try (FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
                 FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath)) {
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }


}
