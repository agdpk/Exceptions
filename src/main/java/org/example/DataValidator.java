package org.example;

import java.util.Calendar;

public class DataValidator {
    public static UserData parseInput(String input) throws InvalidDataException, InvalidDataFormatException {
        String[] data = input.split(" ");

        if (data.length != 6) {
            throw new InvalidDataException("Введено неверное количество данных. Пожалуйста, введите данные в правильном формате.");
        }

        String lastName = data[0];
        String firstName = data[1];
        String surname = data[2];
        String dateOfBirth = data[3];
        long phoneNumber;
        char gender;

        if (!lastName.matches("[a-zA-Zа-яА-Я\\-]+") || !firstName.matches("[a-zA-Zа-яА-Я\\-]+") || !surname.matches("[a-zA-Zа-яА-Я\\-]+")) {
            throw new InvalidDataFormatException("Некорректное имя, фамилия или отчество. Введите только буквы и/или тире.");
        }

        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            throw new InvalidDataFormatException("Некорректный формат номера телефона. Введите целое беззнаковое число.");
        }

        if (!dateOfBirth.matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new InvalidDataFormatException("Некорректный формат даты рождения. Введите дату в формате dd.mm.yyyy.");
        }

        String[] dateParts = dateOfBirth.split("\\.");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        if (month > 12 || month < 1) {
            throw new InvalidDataFormatException("Некорректный месяц. Введите число от 1 до 12.");
        }

        if (day < 1 || day > 31) {
            throw new InvalidDataFormatException("Некорректный день. Введите число от 1 до 31.");
        }

        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            throw new InvalidDataFormatException("Некорректный день. В месяце " + month + " должно быть не более 30 дней.");
        }

        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                if (day > 29) {
                    throw new InvalidDataFormatException("Некорректный день. В феврале в високосный год должно быть не более 29 дней.");
                }
            } else {
                if (day > 28) {
                    throw new InvalidDataFormatException("Некорректный день. В феврале должно быть не более 28 дней.");
                }
            }
        }

        Calendar currentCal = Calendar.getInstance();
        int currentYear = currentCal.get(Calendar.YEAR);
        int currentMonth = currentCal.get(Calendar.MONTH) + 1;
        int currentDay = currentCal.get(Calendar.DAY_OF_MONTH);

        if (year > currentYear ||
                (year == currentYear && month > currentMonth) ||
                (year == currentYear && month == currentMonth && day > currentDay)) {
            throw new InvalidDataFormatException("Некорректная дата рождения. Дата не должна превышать текущую дату.");
        }

        if (!data[5].equalsIgnoreCase("m") && !data[5].equalsIgnoreCase("f")) {
            throw new InvalidDataFormatException("Некорректный формат пола. Введите 'm' для мужского и 'f' для женского.");
        }

        gender = data[5].charAt(0);

        return new UserData(lastName, firstName, surname, dateOfBirth, phoneNumber, gender);
    }
}