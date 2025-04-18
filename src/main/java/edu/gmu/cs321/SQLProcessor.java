// modified version of "TestDatabase" from previous classes.


package edu.gmu.cs321;

import java.sql.*;

public class SQLProcessor {
    private static final String URL = "jdbc:mysql://localhost:3306/cs321";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890"; // replace with your MySQL password

    private static Connection connection;

    // Get database connection
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert a petitioner using Petitioner object
    public static void createPetitioner(Petitioner petitioner) {
        String insertQuery = "INSERT INTO Petitioner (first, last, dob, aNum) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, petitioner.getFirstName());
            stmt.setString(2, petitioner.getLastName());
            stmt.setInt(3, petitioner.getDOB());
            stmt.setInt(4, petitioner.getANum());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Petitioner added successfully.");
            } else {
                System.out.println("Error adding petitioner.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a relative using Relative object
    public static void createRelative(Relative relative) {
        String insertQuery = "INSERT INTO Relative (first, last, dob, aNumRel) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, relative.getFirstName());
            stmt.setString(2, relative.getLastName());
            stmt.setInt(3, relative.getDOB());
            stmt.setInt(4, relative.getANumRel());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relative added successfully.");
            } else {
                System.out.println("Error adding relative.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a form using Form object
    public static void createForm(Form form) {
        String insertQuery = "INSERT INTO Form (date, address, city, state, zip, aNumPet, aNumRel, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
    
            stmt.setInt(1, form.getApplicationDate());
            stmt.setString(2, form.getAddress());
            stmt.setString(3, form.getCity());
            stmt.setString(4, form.getState());
            stmt.setInt(5, form.getZipCode());
            stmt.setInt(6, form.getPetitionerANum());
            stmt.setInt(7, form.getRelativeANum());
            stmt.setString(8, form.getStatus());
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Form added successfully.");
            } else {
                System.out.println("Error adding form.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 
    public static void main(String[] args) {
        // Example objects from PetitionerFill
        Relative rel = new Relative("John", "Doe", 20010909, 111);
        Petitioner pet = new Petitioner("Frank", "Furt", 20021010, 222);
        Form form = new Form(20250222, "Somewhere St.", "Fairfax", "VA", 22030);

        // Insert into database
        createPetitioner(pet);
        createRelative(rel);
        createForm(form);
    }

    */
}
