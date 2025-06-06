package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    String url = "jdbc:mysql://localhost:3306/homerentalapp";
    String user = "root";
    String pass = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    //registerUser(User user)
    public boolean registerUser(User user) throws SQLException {

        String query = "INSERT INTO user(name, email, password, role, phone) VALUES(?,?,?,?,?)";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.setLong(5, user.getPhone());

        int row = ps.executeUpdate();
        return row > 0;
    }

    //getUserByEmail
    public User getUserByEmail(String email) throws SQLException {
        String query = "select * from user where email = ?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("password"), rs.getString("role"),
                    rs.getLong("phone"));
        } else {
            return null;
        }
    }

    //login(String email, String password)
    public User userLogin(String email, String password) throws SQLException {

        String query = "select * from user where email=? and password = ?";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email.trim());
        ps.setString(2, password.trim());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("password"), rs.getString("role"),
                    rs.getLong("phone"));
        } else {
            return null;
        }
    }


    //getUserById(int userId)
    public User getUserById(int userId) throws SQLException {

        User user = null;

        String query = "select * from user where user_id=?";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            user = new User(rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("password"), rs.getString("role"),
                    rs.getLong("phone"));
        }
        return user;
    }

    //getAllUser
    public List<User> getAllUsers() throws SQLException {
        String query = "select * from user";
        List<User> userList = new ArrayList<>();
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            User user = user = new User(rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("password"), rs.getString("role"),
                    rs.getLong("phone"));
            userList.add(user);
        }
        return userList;
    }

    //updateUser
    public void updateUser(User user) throws SQLException {

        String query = "update user SET name = ?, email = ?, password = ?, role = ?, phone = ? WHERE user_id = ?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.setLong(5, user.getPhone());
        ps.setInt(6, user.getUser_id());
        int row = ps.executeUpdate();

        if (row > 0) {
            System.out.println("Update Successfully!");
        } else {
            System.out.println("Update Failed");
        }
    }

    //deleteUser(int userId)
    public void deleteUser(int userId) throws SQLException {

        String query = "delete from user where  user_id = ?";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        int row = ps.executeUpdate();

        if (row > 0) {
            System.out.println("Deleted Successfully!");
        } else {
            System.out.println("Delete Failed");
        }

    }
}

