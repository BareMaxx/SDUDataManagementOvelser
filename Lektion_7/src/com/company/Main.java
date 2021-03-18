package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Scanner scanner;
    public static void main(String[] args) {
        File file = new File("secureInfo");
        String password = null;
        try {
            scanner = new Scanner(file);
            password = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // write your code here
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/DM07", "postgres", password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
