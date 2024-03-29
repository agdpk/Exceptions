package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные о пользователе в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine();

        try {
            UserData userData = DataValidator.parseInput(input);
            FileWriter.writeToTxt(userData);
            System.out.println("Данные успешно записаны в файл.");
        } catch (InvalidDataException | InvalidDataFormatException | IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите название файла (без расширения .txt):");
            String fileName = inputReader.readLine() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}