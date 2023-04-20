package org.maktab.phonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactRepository {

    private Connection connection;

    public ContactRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String contactsTable = " CREATE TABLE IF NOT EXISTS contact (" +
                " id serial primary key, " +
                " first_name varchar(255), " +
                " last_name varchar(255), " +
                " phone_number varchar(255), " +
                " address varchar(255)" +
                ")";
        connection.prepareStatement(contactsTable).execute();
    }

    public void save(Contact contact) throws SQLException {
        String saveContactSql = "INSERT INTO contact (first_name, last_name, phone_number, address)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveContactSql);
        preparedStatement.setString(1, contact.getFirstName());
        preparedStatement.setString(2, contact.getLastName());
        preparedStatement.setString(3, contact.getPhoneNumber());
        preparedStatement.setString(4, contact.getAddress());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
