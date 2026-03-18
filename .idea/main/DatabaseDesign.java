package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDesign {
    private static final String URL = "jdbc:mysql://localhost:3306/airbnb_booking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

            // USERS TABLE
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                            "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "first_name VARCHAR(100)," +
                            "last_name VARCHAR(100)," +
                            "email VARCHAR(150) UNIQUE NOT NULL," +
                            "password_hash VARCHAR(255) NOT NULL," +
                            "phone_number VARCHAR(20)," +
                            "role ENUM('guest','admin') NOT NULL," +
                            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)"
            );

            // PROPERTIES TABLE
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Properties (" +
                            "property_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "admin_id INT," +
                            "property_name VARCHAR(150)," +
                            "description TEXT," +
                            "address VARCHAR(255)," +
                            "price_per_night DECIMAL(10,2)," +
                            "max_guests INT," +
                            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                            "FOREIGN KEY (admin_id) REFERENCES Users(user_id))"
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
