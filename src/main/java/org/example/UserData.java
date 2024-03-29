package org.example;

public class UserData {
    String lastName;
    String firstName;
    String surname;
    String dateOfBirth;
    long phoneNumber;
    char gender;

    public UserData(String lastName, String firstName, String surname, String dateOfBirth, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return lastName + ' ' + firstName + ' ' +
                surname + ' ' + dateOfBirth + ' ' +
                phoneNumber + ' ' + gender + "\n";
    }
}
