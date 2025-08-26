package EngData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TxtToPostgres {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/dictionary";
        String user = "postgres";
        String password = "sql";

        String filePath = "src/main/java/EngData/ejdic-hand-utf8.txt";

        try (
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO en_jp (word, description) VALUES (?, ?)")
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", 2); // タブで分割（最大2つ）
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String description = parts[1].trim();

                    stmt.setString(1, word);
                    stmt.setString(2, description);
                    stmt.executeUpdate();
                    
                }
            }
            System.out.println("データの挿入が完了しました！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失敗しました！");
            System.out.println("エラー発生箇所: " + e.getClass().getName());
            System.out.println("メッセージ: " + e.getMessage());
        }
    }
}
