package com.ei.eisoccermanagment.soccer.model;

import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.ei.eisoccermanagment.shared.MySQL_Connect.getConnection;

public class UserDAO {

    public static void main(String[] args) {
        getAll().forEach(System.out::println);
    }

//    public static String passwordReset(String email, HttpServletRequest req) {
//        User user = get(email);
//        if(user == null) {
//            return "No user found that matches that email";
//        } else {
//            try(Connection connection = getConnection()) {
//                CallableStatement statement = connection.prepareCall("{call sp_add_password_reset(?,?)}");
//            } catch(SQLException e) {
//
//            }
//        }
//        return "";
//    }

    public static List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_users()}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                String pronoun = rs.getString("pronoun");
                String biography = rs.getString("biography");
                User user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone, pronoun, biography);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static User get(String email) {
        User user = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_user(?)}");
            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                String pronoun = rs.getString("pronoun");
                String biography = rs.getString("biography");
                user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone, pronoun, biography);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean add(User user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_add_user(?,?,?,?)}");
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12)));
            statement.setString(3, user.getStatus());
            statement.setString(4, user.getPrivileges());
            int rowsAdded = statement.executeUpdate();
            return rowsAdded == 1;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
