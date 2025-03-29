package com.ei.eisoccermanagment.soccer.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.ei.eisoccermanagment.shared.MySQL_Connect.getConnection;

public class CoachDAO {
    public static void main(String[] args) {

    }

    public static List<Coach> getAll() {
        List<Coach> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_coaches()}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int coachId = rs.getInt("coach_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String pronoun = rs.getString("pronoun");
                String biography = rs.getString("biography");
                String specialty = rs.getString("specialty");
                Coach coach = new Coach(coachId, firstName, lastName, email, age, pronoun, biography, specialty);
                list.add(coach);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static Coach get(int coachId) {
        Coach coach = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_coach(?)}");
            cstmt.setInt(1, coachId);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String pronoun = rs.getString("pronoun");
                String biography = rs.getString("biography");
                String specialty = rs.getString("specialty");
                coach = new Coach(coachId, firstName, lastName, email, age, pronoun, biography, specialty);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return coach;
    }

    public static boolean update(Coach coachOriginal, Coach coachNew) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_coach(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, coachOriginal.getCoachId());
            statement.setString(2, coachOriginal.getFirstName());
            statement.setString(3, coachOriginal.getLastName());
            statement.setString(4, coachOriginal.getEmail());
            statement.setInt(5, coachOriginal.getAge());
            statement.setString(6, coachOriginal.getPronoun());
            statement.setString(7, coachOriginal.getBiography());
            statement.setString(8, coachOriginal.getSpecialty());
            statement.setString(9, coachNew.getFirstName());
            statement.setString(10, coachNew.getLastName());
            statement.setString(11, coachNew.getEmail());
            statement.setInt(12, coachNew.getAge());
            statement.setString(13, coachNew.getPronoun());
            statement.setString(14, coachNew.getBiography());
            statement.setString(15, coachNew.getSpecialty());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean add(Coach coach) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_coach(?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, coach.getFirstName());
            statement.setString(2, coach.getLastName());
            statement.setString(3, coach.getEmail());
            statement.setInt(4, coach.getAge());
            statement.setString(5, coach.getPronoun());
            statement.setString(6, coach.getBiography());
            statement.setString(7, coach.getSpecialty());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
//            System.out.println(e.getMessage());
            return false;
        }
    }
}
