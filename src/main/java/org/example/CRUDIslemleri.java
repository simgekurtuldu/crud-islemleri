package org.example;

import java.sql.*;

public class CRUDIslemleri {
    // Veritabanı bağlantı bilgileri
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "test123";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Veritabanına bağlan
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // Tablo oluştur
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ad_soyad (id SERIAL PRIMARY KEY, name VARCHAR(50))";
            stmt.executeUpdate(createTableSQL);

            // Veri ekle
            String insertSQL = "INSERT INTO ad_soyad (name) VALUES ('Simge Kurtuldu'), ('Jane Smith'), ('Alice Johnson')";
            stmt.executeUpdate(insertSQL);

            System.out.println("Table created and data inserted successfully");

            // Bir kayıt sil
            String deleteSQL = "DELETE FROM ad_soyad WHERE name = 'Simge Kurtuldu'";
            stmt.executeUpdate(deleteSQL);

            System.out.println("Record deleted successfully");

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
