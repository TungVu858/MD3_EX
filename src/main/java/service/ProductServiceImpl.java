package service;

import model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public void add(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("insert into product (id,name,price,quantity) value (?,?,?,?)");) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product ")) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id,name,price,quantity));
            }
        } catch (SQLException e) {
        }
        return products;
    }
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");) {
            preparedStatement.setString(1, "%" + name + "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                int price  = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id,nameFind,price,quantity));
            }
        } catch (SQLException e) {
        }
        return products;
    }
    public List<Product> findByPrice(int p1,int p2) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where price between ? and ?");) {
            preparedStatement.setInt(1,p1);
            preparedStatement.setInt(2,p2);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                int price  = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id,nameFind,price,quantity));
            }
        } catch (SQLException e) {
        }
        return products;
    }
}
