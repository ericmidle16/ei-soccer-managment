package com.ei.eisoccermanagment.soccer.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.ei.eisoccermanagment.shared.MySQL_Connect.getConnection;

public class ReviewDAO {
    public static void main(String[] args) {

    }

    public static List<Review> getAll() {
        List<Review> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_reviews()}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int reviewId = rs.getInt("review_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String description = rs.getString("description");
                Review review = new Review(reviewId, firstName, lastName, description);
                list.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
