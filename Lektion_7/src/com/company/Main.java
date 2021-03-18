package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Scanner scanner;
    public static void main(String[] args) {
        File file = new File("C:\\Users\\maxx0\\OneDrive - Syddansk Universitet\\2.Semester\\DM\\Ovelser\\SDUDataManagementOvelser\\Lektion_7\\src\\com\\company\\secureInfo");
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
            if (password != null) {
                connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/DM07", "postgres", password);
            } else {
                System.out.println("Password is null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Insert Børge with CPR 1234567891 to the users table
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, cpr) VALUES (?, ?)");
            statement.setString(1, "Børge");
            statement.setString(2, "1234567891");
            statement.execute();
        } catch (SQLException e) {
             e.printStackTrace();
        }

        try {
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM users WHERE cpr = ?");
            statement2.setString(1, "1234567891");
            ResultSet set = statement2.executeQuery();

            while (set.next()) {
                System.out.println(set.getString("name") + " har CPR " + set.getString("cpr"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
