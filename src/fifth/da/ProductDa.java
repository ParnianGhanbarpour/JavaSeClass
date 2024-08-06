package fifth.da;

import fifth.entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDa {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "javase",
                "java123"
        );
    }

    public void save(Product product) throws Exception{
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getCount());
        int sum = (product.getCount()*product.getCount());
        preparedStatement.setInt(4,sum);

        preparedStatement.execute();
        close();
    }

    public void edit(Product product)throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "UPDATE Product_TBL SET NAME = ?, Price = ?,Count =?,sum =? WHERE ID = ?"
        );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getCount());
        int sum = (product.getCount()*product.getCount());
        preparedStatement.setInt(4,sum);
        preparedStatement.setInt(5, product.getId());

        preparedStatement.execute();
        close();
    }


    public void remove(int id)throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM Product_TBL WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        close();
    }

    public List<Product> findAll()throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM Product_TBL ORDER BY ID"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Product> productList = new ArrayList<Product>();

        while(resultSet.next()) {
            Product product = Product
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("perice"))
                    .count(resultSet.getInt("count"))
                    .build();
            productList.add(product);
        }
        close();
        return productList;
    }

    public Product findById(int id)throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Product product = null;

        if(resultSet.next()) {
            product = Product
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .count(resultSet.getInt("count"))
                    .build();
        }
        close();
        return product;
    }


    public Product findByName(String name)throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME = ?"
        );
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        Product product = null;

        if(resultSet.next()) {
            product = Product
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .count(resultSet.getInt("count"))
                    .build();
        }
        close();
        return product;
    }


    public Product findByUsernameAndPrice(String name, int price)throws Exception {
        connect();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME = ? AND PASSWORD = ?"
        );
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, price);
        ResultSet resultSet = preparedStatement.executeQuery();

        Product product = null;

        if(resultSet.next()) {
            product = Product
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .count(resultSet.getInt("count"))
                    .build();
        }
        close();
        return product;
    }

    public void close()throws Exception{
        preparedStatement.close();
        connection.close();
    }
}
