/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.*;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    // để lấy ra toàn bộ dữ liệu lên trên trang web
    public List<Product> findAll() {
        List<Product> listFound = new ArrayList<>();
        // connect with DB
        connection = getConnection();
        // chuẩn bị câu lệnh SQL
        String sql = "SELECT * FROM [CRUD_W_DB].[dbo].Product"; // SQL error?

        try {
            // Tạo đối tượng prepareStatement
            statement = connection.prepareStatement(sql);

            // Set parameter (optional)
            // thực thi câu lệnh
            resultSet = statement.executeQuery();
            // trả về kết quả
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, quantity, price);

                listFound.add(product);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listFound;
    }

    public List<Product> findByName(String keyword) {
        List<Product> listFound = new ArrayList<>();
        // connect with DB
        connection = getConnection();
        // chuẩn bị câu lệnh SQL
        String sql = "SELECT * FROM [CRUD_W_DB].[dbo].Product where name like ?"; // SQL error?

        try {
            // Tạo đối tượng prepareStatement
            statement = connection.prepareStatement(sql);

            // Set parameter (optional)
            statement.setString(1, "%" + keyword + "%");
            // thực thi câu lệnh
            resultSet = statement.executeQuery();
            // trả về kết quả
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, quantity, price);

                listFound.add(product);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listFound;
    }

    public void insert(Product product) {
        // kêt nối với DB
        connection = getConnection();
        // tạo câu lệnh SQL
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            // tạo đối tượng prepared statement (them generrated ket vào tham so thu 2)
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            // set parameter
            statement.setObject(1, product.getName());
            statement.setObject(2, product.getQuantity());
            statement.setObject(3, product.getPrice());
            // thuc thi cau lenh
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // tra ve ket qua(optional)
    }

    public void update(Product product) {
        // kêt nối với DB
        connection = getConnection();
        // tạo câu lệnh SQL
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + " WHERE id = ?";
        try {
            // tạo đối tượng prepared statement (them generrated ket vào tham so thu 2)
            statement = connection.prepareStatement(sql);
            //set parameter
            statement.setObject(1, product.getName());
            statement.setObject(2, product.getQuantity());
            statement.setObject(3, product.getPrice());
            statement.setObject(4, product.getId());
            // thuc thi cau lenh 
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Product product) {
        // kêt nối với DB
        connection = getConnection();
        // tạo câu lệnh SQL
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id = ?";
        try {
            // tạo đối tượng prepared statement (them generrated ket vào tham so thu 2)
            statement = connection.prepareStatement(sql);
            //set parameter
            statement.setObject(1, product.getId());
            // thuc thi cau lenh 
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
