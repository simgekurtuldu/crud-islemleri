package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Veritabanı bağlantı bilgileri
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "test123";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Veritabanına bağlan
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Örnek tablo oluştur
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50))";
            stmt = conn.prepareStatement(createTableSQL);
            stmt.executeUpdate();

            // PreparedStatement kullanarak veri ekleyin
            String insertSQL = "INSERT INTO users (name) VALUES (?)";
            stmt = conn.prepareStatement(insertSQL);
            stmt.setString(1, "John Doe");
            stmt.executeUpdate();

            stmt.setString(1, "Jane Smith");
            stmt.executeUpdate();

            System.out.println("Records inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Bağlantıyı ve kaynakları kapat
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}