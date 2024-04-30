package database;

import java.sql.*;

public class DBcon {
    private static final String url = "JDBC:mysql://localhost:3306/scoremanagement?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "123456";

    public static Connection connection;

    /**
     * 连接数据库
     * @return 数据库连接
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @param connection
     */
    public static void closeConn(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
