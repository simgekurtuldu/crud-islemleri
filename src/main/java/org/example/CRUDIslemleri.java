package org.example;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class CRUDIslemleri {
    // Veritabanı bağlantı bilgileri
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "test123";

    public static void main(String[] args) {
        // JDBI bağlantısı oluştur
        Jdbi jdbi = Jdbi.create(DB_URL, USER, PASS);

        // CRUD işlemleri
        try (Handle handle = jdbi.open()) {
            // Tablo oluştur
            handle.execute("CREATE TABLE IF NOT EXISTS ad_soyad (id SERIAL PRIMARY KEY, name VARCHAR(50))");

            // Veri ekle
            handle.execute("INSERT INTO ad_soyad (name) VALUES ('John Doe'), ('Jane Smith'), ('Alice Johnson')");
            System.out.println("Data inserted successfully");

            // Bir kayıt sil
            handle.execute("DELETE FROM ad_soyad WHERE name = 'John Doe'");
            System.out.println("Record deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
