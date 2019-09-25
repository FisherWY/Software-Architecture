package Experiment4.dao;

import Experiment4.model.User;
import com.sun.istack.internal.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author Fisher
 * @Date 2019/9/24 11:04
 **/


public class UserTable {

    private String serverURL = "jdbc:mysql://192.168.199.241:3306/";
    private String database = "easymall";
    private String serverTimeZone = "?serverTimezone=UTC";

    private String username = "fisher";
    private String password = "12345678";

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public boolean init() {
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立连接
            connection = DriverManager.getConnection(serverURL+database+serverTimeZone, username, password);
            // 返回连接情况
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void release() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
            System.gc();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean addUser(@NotNull User user) {
        try {
            String sql = "insert into user(username,weight,height,bmi) values(?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getWeight());
            statement.setString(3,user.getHeight());
            statement.setString(4,user.getBmi());

            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
