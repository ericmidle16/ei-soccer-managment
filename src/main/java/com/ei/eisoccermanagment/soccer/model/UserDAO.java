package com.ei.eisoccermanagment.soccer.model;

import com.ei.eisoccermanagment.shared.EmailThread;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ei.eisoccermanagment.shared.MySQL_Connect.getConnection;

public class UserDAO {

    public static void main(String[] args) {
        getAll().forEach(System.out::println);
        System.out.println(get("test@example.com"));

    }

    public static String passwordReset(String email, HttpServletRequest req) {
        User user = get(email);
        if (user == null) {
            return "No user found that matches the email";
        } else {
            try (Connection connection = getConnection()) {
                if (connection != null) {
                    String uuid = String.valueOf(UUID.randomUUID());
                    try (CallableStatement statement = connection.prepareCall("{CALL sp_add_password_reset(?, ?)}")) {
                        statement.setString(1, email);
                        statement.setString(2, uuid);
                        statement.executeUpdate();
                    }
                    String subject = "Reset Password";
                    String message = "<h2Reset Password</h2>";
                    message += "<p>Please use this link to securely reset your password. This link will remain active for 30 minutes.</p>";
                    String appUrl = "";
                    if(req.isSecure()) {
                        appUrl = req.getServletContext().getInitParameter("appURLAzure");
                    } else {
                        appUrl = req.getServletContext().getInitParameter("appURLLocal");
                    }
                    String fullURL = String.format("%s/new-password?token=%s", appUrl, uuid);
                    message += String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p>", fullURL, fullURL);
                    message += "<p>If you did not request to reset your password, you can ignore this message and your password will not be changed.</p>";
                    EmailThread emailThread1 = new EmailThread(email, subject, message);
                    emailThread1.start();
                    try {
                        emailThread1.join();
                    } catch (InterruptedException e) {

                    }
                    String errorMessage1 = emailThread1.getErrorMessage();
                    return "If there's an account associated with the email entered, we will send a password reset link.";
                }
            } catch (SQLException e) {
                return "Error resetting password";
            }
        }
        return "Error - Could not send password reset email";
    }

    public static String getPasswordReset(String token) {
        String email = "";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_password_reset(?)}")) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Instant now = Instant.now();
                Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                Duration duration = Duration.between(created_at, now);
                long minutesElapsed = duration.toMinutes();
                if(minutesElapsed < 30) {
                    email = resultSet.getString("email");
                }
                int id = resultSet.getInt("id");
                CallableStatement statement2 = connection.prepareCall("{CALL sp_delete_password_reset(?)}");
                statement2.setInt(1, id);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    public static boolean updatePassword(String email, String password) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_update_user_password(?, ?)}")) {
                    statement.setString(1, email);
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    statement.setString(2, encryptedPassword);
                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

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

    public static boolean delete(User user) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(?)}");
            statement.setInt(1, user.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
