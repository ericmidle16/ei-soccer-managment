package com.ei.eisoccermanagment.soccer.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.ei.eisoccermanagment.shared.MySQL_Connect.getConnection;

public class ProductDAO {
    public static void main(String[] args) {

    }

    public static List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_products()}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String color = rs.getString("color");
                Product product = new Product(productId, name, price, description, color);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static Product get(int productId) {
        Product product = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_product(?)}");
            cstmt.setInt(1, productId);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String color = rs.getString("color");
                product = new Product(productId, name, price, description, color);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public static boolean update(Product productOriginal, Product productNew) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_product(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, productOriginal.getProductId());
            statement.setString(2, productOriginal.getName());
            statement.setDouble(3, productOriginal.getPrice());
            statement.setString(4, productOriginal.getDescription());
            statement.setString(5, productOriginal.getColor());
            statement.setString(6, productNew.getName());
            statement.setDouble(7, productNew.getPrice());
            statement.setString(8, productNew.getDescription());
            statement.setString(9, productNew.getColor());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean add(Product product) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_product(?, ?, ?, ?)}");
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setString(4, product.getColor());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
//            System.out.println(e.getMessage());
            return false;
        }
    }
}
