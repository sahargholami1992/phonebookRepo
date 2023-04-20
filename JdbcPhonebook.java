package org.maktab.phonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcPhonebook {

    // Connect to db
    // Create Contact Table
    // Insert, Delete, update, search -> CRUD -> Create, Read, Update, Delete
    private Connection connection;

    public JdbcPhonebook(Connection connection) throws SQLException {
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

    public void saveContact(String firstName, String lastName, String phoneNumber, String address) throws SQLException {
        String saveContactSql = "INSERT INTO contact (first_name, last_name, phone_number, address)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveContactSql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, phoneNumber);
        preparedStatement.setString(4, address);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateContact(Integer id, String firstName, String lastName, String phoneNumber, String address) throws SQLException {
        String updateContactSql = "UPDATE contact " +
                " SET first_name = ?, " +
                " last_name = ?, " +
                " phone_number = ?, " +
                " address = ? " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateContactSql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, phoneNumber);
        preparedStatement.setString(4, address);
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteContact(Integer id) throws SQLException {
        String deleteSql = "DELETE FROM contact WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public String search(String firstName, String lastName) throws SQLException {
        String searchSql = "SELECT * FROM contact " +
                " WHERE first_name = ? AND last_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(searchSql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        ResultSet result = preparedStatement.executeQuery();
        result.next(); // because result is a cursor. cursor is a database concept. read it for yourself.
        return result.getString("phone_number");
    }
}
