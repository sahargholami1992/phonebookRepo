package org.maktab.phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PhonebookTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); // Registering postgresql jdbc driver
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5437/postgres", "postgres", "postgres");
        JdbcPhonebook phonebook = new JdbcPhonebook(connection);

        phonebook.saveContact("Milad", "Amery", "09300000000", "Addr");
        phonebook.saveContact("Milad2", "Amery2", "09300000001", "Addr");
        phonebook.saveContact("Milad3", "Amery3", "09300000002", "Addr");
        phonebook.saveContact("Milad4", "Amery4", "09300000003", "Addr");

        String phonenumber = phonebook.search("Milad", "Amery");

        // Call
        // Pool ->
        System.out.println(phonenumber);
    }
}
