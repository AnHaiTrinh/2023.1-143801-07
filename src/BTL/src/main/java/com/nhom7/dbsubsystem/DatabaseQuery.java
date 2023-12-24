package com.nhom7.dbsubsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {
    @SafeVarargs
    public static <T> ResultSet executeQuery(String query, T... parameters) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Lấy kết nối từ DatabaseHRSubsystemConnection (điều này phải được triển khai)
            connection = DBSubsystemConnection.getConnection();

            // Chuẩn bị câu truy vấn
            statement = connection.prepareStatement(query);

            // Đặt giá trị cho các tham số
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi theo ý bạn

        }
        // Trả về kết quả ResultSet (có thể là null nếu có lỗi)
        return resultSet;
    }
}
