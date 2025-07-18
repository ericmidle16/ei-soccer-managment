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
        //getAll(5, 0, "1,2,3", "1").forEach(System.out::println);
        //getAllCategories().forEach(System.out::println);
        //getAllColors().forEach(System.out::println);
        //System.out.println(getProductCount("","1"));
        System.out.println(getCart(7));
    }

    public static List<Product> getAll(int limit, int offset, String categories, String colors) {
        List<Product> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_products(?,?,?,?)}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            cstmt.setInt(1, limit);
            cstmt.setInt(2, offset);
            cstmt.setString(3, categories);
            cstmt.setString(4, colors);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                int colorId = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                Product product = new Product(productId, name, price, description, categoryId, categoryName, colorId, colorName);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<Product> getAllAdmin() {
        List<Product> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_products_admin()}");
            // If you are selecting items you will get a result set with executeQuery
            // Every other is .executeUpdate
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                int colorId = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                Product product = new Product(productId, name, price, description, categoryId, categoryName, colorId, colorName);
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
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                int colorId = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                product = new Product(productId, name, price, description, categoryId, categoryName, colorId, colorName);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public static Product getAdmin(int productId) {
        Product product = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_product_admin(?)}");
            cstmt.setInt(1, productId);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                int colorId = rs.getInt("color_id");
                product = new Product(productId, name, price, description, categoryId, colorId);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public static Product getCart(int product_id) {
        Product product = null;
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{call sp_get_product_cart(?)}");
            statement.setInt(1, product_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                product = new Product(productId, name, price, description);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public static boolean update(Product productOriginal, Product productNew) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_update_product(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setInt(1, productOriginal.getProductId());
            statement.setString(2, productOriginal.getName());
            statement.setDouble(3, productOriginal.getPrice());
            statement.setString(4, productOriginal.getDescription());
            statement.setInt(5, productOriginal.getCategoryId());
            statement.setInt(6, productOriginal.getColorId());
            statement.setString(7, productNew.getName());
            statement.setDouble(8, productNew.getPrice());
            statement.setString(9, productNew.getDescription());
            statement.setInt(10, productNew.getCategoryId());
            statement.setInt(11, productNew.getColorId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean add(Product product) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_product(?, ?, ?, ?, ?)}");
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getCategoryId());
            statement.setInt(5, product.getColorId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
//            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean delete(String id) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_product(?)}");
            statement.setInt(1, Integer.parseInt(id));
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ProductCategory> getAllCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_product_categories()}");
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int numProducts = resultSet.getInt("num_products");
                categories.add(new ProductCategory(id, name, numProducts));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public static List<ProductColor> getAllColors() {
        List<ProductColor> colors = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_product_colors()}");
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int colorId = resultSet.getInt("id");
                String colorName = resultSet.getString("name");
                int numColorProducts = resultSet.getInt("num_products");
                colors.add(new ProductColor(colorId, colorName, numColorProducts));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return colors;
    }

    public static int getProductCount(String categories, String colors) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_total_products(?,?)}");
        ) {
            statement.setString(1, categories);
            statement.setString(2, colors);
            try(ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_products");
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
