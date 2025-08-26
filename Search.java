package EngData;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String input = request.getParameter("text");
//    	String inputNum = request.getParameter("number");
    	
    	String quantityStr = request.getParameter("quantity");
    	int quantity = Integer.parseInt(quantityStr); // 入力値を数値に変換
        String url = "jdbc:postgresql://localhost:5432/dictionary";
        String user = "postgres";
        String password = "sql";

        Scanner scanner = new Scanner(System.in);
        System.out.print("検索したい英単語（前方一致）を入力してください: ");


        String sql = "SELECT word, description  FROM en_jp WHERE word ILIKE ? LIMIT ?";
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        
        //データベースからデータを取る処理
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, input + "%"); // 前方一致
            stmt.setInt(2, quantity); // 検索件数の上限
            
            ResultSet rs = stmt.executeQuery();

//            System.out.println("🔎 検索結果:");
            List<String[]> results = new ArrayList<>();
            while (rs.next()) {
                String word = rs.getString("word");
                String description = rs.getString("description");
                results.add(new String[] { word, description });
                System.out.println(" - " + word + " : " + description);
            }
            
         // JSPに渡す
            request.setAttribute("results", results);

            // JSPに処理を渡して表示
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EngDictionary/index.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            System.out.println("❌ エラー: " + e.getMessage());
        }
    }
}
