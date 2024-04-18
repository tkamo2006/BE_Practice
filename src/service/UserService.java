package src.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    public static void createUser(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("사용자 ID를 입력하세요: ");
        String userId = scanner.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        String sql = "INSERT INTO User (UserId, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("사용자가 생성되었습니다.");
        }catch(SQLException se) {
            System.out.println("이미 존재합니다.");
        } finally {
            try {
                if (conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
